import time
import serial
import json
import firebase_admin
from firebase_admin import credentials, firestore

# ---------- CONFIG ----------
SERIAL_PORT = "COM5"
SERIAL_BAUD = 9600

SERVICE_ACCOUNT_FILE = "serviceAccount.json"  # seu JSON de credencial do Firebase
# paths Firestore
ENABLES_DOC_PATH = ("Sensors", "testsensors", "sensorstate", "testsensor")  # coleção, doc, subcoleção, doc
READINGS_DOC = ("Sensors", "testsensors")  # collection doc -> /Sensors/testsensors
# intervalo entre ciclos (s)
INTERVAL = 3.0
# ----------------------------

# init firebase admin
cred = credentials.Certificate(SERVICE_ACCOUNT_FILE)
firebase_admin.initialize_app(cred)
db = firestore.client()

# init serial
ser = serial.Serial(SERIAL_PORT, SERIAL_BAUD, timeout=2)

def read_enables():
    """
    Retorna um dicionário booleans com os enables lidos do Firestore.
    Exemplo de retorno:
    { "gasSensorEnabled": True, "presenceSensorEnabled": False, ... }
    """
    # caminho: /Sensors/testsensors/sensorstate/testsensor
    coll = db.collection(ENABLES_DOC_PATH[0]).document(ENABLES_DOC_PATH[1]).collection(ENABLES_DOC_PATH[2]).document(ENABLES_DOC_PATH[3])
    doc = coll.get()
    if not doc.exists:
        print("Documento de enables não existe:", "/".join(ENABLES_DOC_PATH))
        return {}
    data = doc.to_dict()
    return data

def build_flags_string(enables):
    # ordem: gas,presence,water,tempHumidity
    gas = 1 if enables.get("gasSensorEnabled") else 0
    pres = 1 if enables.get("presenceSensorEnabled") else 0
    water = 1 if enables.get("waterSensorEnabled") else 0
    temp = 1 if enables.get("tempHumiditySensorEnabled") else 0
    return f"{gas},{pres},{water},{temp}\n"

def request_readings_from_arduino(flags_str):
    # envia flags e espera resposta JSON
    ser.reset_input_buffer()
    ser.write(flags_str.encode('utf-8'))
    ser.flush()
    start = time.time()
    # aguardar até timeout (2s por padrão), mas podemos estender
    while True:
        if ser.in_waiting:
            line = ser.readline().decode('utf-8', errors='ignore').strip()
            if not line:
                continue
            try:
                data = json.loads(line)
                return data
            except Exception as e:
                print("Erro ao parsear JSON do Arduino:", e, "conteudo:", line)
                return None
        if time.time() - start > 3.0:
            print("Timeout aguardando resposta do Arduino")
            return None

def write_readings_to_firestore(readings):
    """
    readings: dicionário com as chaves possíveis: gas, pir, water, temp, hum
    Gravamos no documento /Sensors/testsensors (merge).
    Mapeamos nomes para os campos solicitados:
      gas -> gasReading (int)
      pir -> presenceReading (bool)
      water -> waterLevelReading (int)
      temp -> temperatureReading (int)
      hum -> airHumidityReading (int)
    """
    if not readings:
        return False
    doc_ref = db.collection(READINGS_DOC[0]).document(READINGS_DOC[1])
    payload = {}
    if "gas" in readings:
        payload["gasReading"] = int(readings["gas"])
    if "pir" in readings:
        # tarja como booleano
        payload["presenceReading"] = bool(int(readings["pir"]))
    if "water" in readings:
        payload["waterLevelReading"] = int(readings["water"])
    if "temp" in readings:
        payload["temperatureReading"] = int(readings["temp"])
    if "hum" in readings:
        payload["airHumidityReading"] = int(readings["hum"])
    if not payload:
        return False
    doc_ref.set(payload, merge=True)
    return True

def main_loop():
    print("Iniciando loop. Press Ctrl+C para interromper.")
    while True:
        try:
            enables = read_enables()
            if not enables:
                print("Nenhum enable encontrado. Pulando ciclo.")
                time.sleep(INTERVAL)
                continue

            flags_str = build_flags_string(enables)
            print("Flags:", flags_str.strip())

            readings = request_readings_from_arduino(flags_str)
            if readings is None:
                print("Nenhuma leitura válida do Arduino neste ciclo.")
            else:
                print("Leituras recebidas:", readings)
                ok = write_readings_to_firestore(readings)
                if ok:
                    print("Leituras gravadas no Firestore.")
                else:
                    print("Não havia campos para gravar.")
        except Exception as e:
            print("Erro no loop principal:", e)
        time.sleep(INTERVAL)

if __name__ == "__main__":
    main_loop()
