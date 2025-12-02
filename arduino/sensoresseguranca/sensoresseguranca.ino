// TCC Etec ZL - 3º Novotec Desenvolvimento de Sistemas Manhã - Henrique Macedo, Lucas Rosa, Matheus Santana, Pedro da Silva
#include <DHT.h>

#define pino_dht 3
#define DHTTYPE DHT11
DHT dht(pino_dht, DHTTYPE);

#define sensorgas A0
#define sensoragua A1
#define sensorpresenca 2

void setup() {
  Serial.begin(9600);
  dht.begin();
  pinMode(sensorpresenca, INPUT);
  pinMode(sensorgas, INPUT);
  pinMode(sensoragua, INPUT);
}

void loop() {
  // Espera uma linha de comando do formato: 1,0,1,1
  if (Serial.available()) {
    String line = Serial.readStringUntil('\n');
    line.trim();
    if (line.length() == 0) return;

    // Parse flags
    int flags[4] = {0,0,0,0};
    int idx = 0;
    int start = 0;
    for (int i = 0; i <= line.length(); i++) {
      if (i == line.length() || line.charAt(i) == ',') {
        String token = line.substring(start, i);
        token.trim();
        if (token.length()) flags[idx] = token.toInt();
        idx++;
        start = i + 1;
        if (idx >= 4) break;
      }
    }

    // variáveis para ver se os sensores devem ou não ser lidos.
    bool gasEnabled = flags[0];
    bool pirEnabled = flags[1];
    bool waterEnabled = flags[2];
    bool tempHumEnabled = flags[3];

    // Leitura dos sensores (somente os habilitados); se retornar -1, provavelmente é um erro
    int gas = -1;
    int pir = -1;
    int water = -1;
    int temp = -1;
    int hum = -1;

    if (gasEnabled) {
      gas = analogRead(sensorgas);
    }

    if (pirEnabled) {
      pir = digitalRead(sensorpresenca);
    }

    if (waterEnabled) {
      water = analogRead(sensoragua);
    }

    if (tempHumEnabled) {
      float t = dht.readTemperature();
      float h = dht.readHumidity();
      if (!isnan(t)) temp = (int)round(t);
      if (!isnan(h)) hum = (int)round(h);
    }

    // Monta um JSON e envia
    // Exemplo: {"gas":320,"pir":0,"water":580,"temp":25,"hum":61}
    Serial.print("{");
    bool first = true;
    if (gasEnabled) {
      Serial.print("\"gas\":"); Serial.print(gas); first = false;
    }
    if (pirEnabled) {
      if (!first) Serial.print(",");
      Serial.print("\"pir\":"); Serial.print(pir); first = false;
    }
    if (waterEnabled) {
      if (!first) Serial.print(",");
      Serial.print("\"water\":"); Serial.print(water); first = false;
    }
    if (tempHumEnabled) {
      if (!first) Serial.print(",");
      Serial.print("\"temp\":"); Serial.print(temp);
      Serial.print(",\"hum\":"); Serial.print(hum);
    }
    Serial.println("}");
  }

  delay(10);
}
