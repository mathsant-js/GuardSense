#include <SoftwareSerial.h>
#include <DHT.h>
#include <ArduinoJson.h>

// definição os pinos
#define PIN_PIR 2
#define PIN_DHT 3
#define PIN_GAS A0
#define PIN_AGUA A1

#define DHTTYPE DHT11

// serial para comunicação com o esp32cam. pino 10 - ligar no tx / pino 11 - ligar no rx
SoftwareSerial espSerial(10, 11); 

// iniciando o dht
DHT dht(PIN_DHT, DHTTYPE);

// variáveis de estado (ativado/desativado) para os sensores
bool enGas = false;
bool enPres = false;
bool enTemp = false;
bool enAgua = false;

void setup() {
  Serial.begin(9600);
  espSerial.begin(115200);
  
  pinMode(PIN_PIR, INPUT);
  dht.begin();
  
  Serial.println("Aguardando comandos do ESP32...");
}

void loop() {
  // busca/verifica se o ESP32 enviou comandos
  if (espSerial.available() > 0) {
    String comando = espSerial.readStringUntil('\n');
    comando.trim();
    
    // verificação do formato
    if (comando.startsWith("<") && comando.endsWith(">")) {
      // remoção os sinais < e >
      comando = comando.substring(1, comando.length() - 1);
      parseComandos(comando);
      
      // realizar as leituras
      realizarLeiturasEEnviar();
    }
    // código para ver os dados do serial do esp32 no monitor do arduino
    if (espSerial.available()) {
      Serial.write(espSerial.read()); 
    }
  }
}

void parseComandos(String dados) {
  // formato: gas,presenca,dht,agua (ex: 1,0,1,1); separa por vírgula
  int index1 = dados.indexOf(',');
  int index2 = dados.indexOf(',', index1 + 1);
  int index3 = dados.indexOf(',', index2 + 1);

  // pega os dados encontrados e converte pra int
  enGas = dados.substring(0, index1).toInt();
  enPres = dados.substring(index1 + 1, index2).toInt();
  enTemp = dados.substring(index2 + 1, index3).toInt();
  enAgua = dados.substring(index3 + 1).toInt();
  
  Serial.print("Config recebida - Gas:"); Serial.print(enGas);
  Serial.print(" Pres:"); Serial.print(enPres);
  Serial.print(" Temp:"); Serial.print(enTemp);
  Serial.print(" Agua:"); Serial.println(enAgua);
}

void realizarLeiturasEEnviar() {
  // cria documento JSON para enviar de volta
  JsonDocument doc;

  // dependendo se está ativado ou não, salva a leitura no json doc
  if (enGas) {
    doc["gasReading"] = analogRead(PIN_GAS);
  } else {
    doc["gasReading"] = 0;
  }

  if (enPres) {
    doc["presenceReading"] = digitalRead(PIN_PIR) == HIGH ? true : false;
  } else {
    doc["presenceReading"] = false;
  }

  if (enTemp) {
    float h = dht.readHumidity();
    float t = dht.readTemperature();
    if (isnan(h) || isnan(t)) {
      doc["airHumidityReading"] = 0;
      doc["temperatureReading"] = 0;
    } else {
      doc["airHumidityReading"] = h;
      doc["temperatureReading"] = t;
    }
  } else {
    doc["airHumidityReading"] = 0;
    doc["temperatureReading"] = 0;
  }

  if (enAgua) {
    doc["waterLevelReading"] = analogRead(PIN_AGUA);
  } else {
    doc["waterLevelReading"] = 0;
  }

  // serializa o json e envia para o ESP32
  serializeJson(doc, espSerial);
  espSerial.println();
  
  // debug no PC
  serializeJson(doc, Serial);
  Serial.println();
}