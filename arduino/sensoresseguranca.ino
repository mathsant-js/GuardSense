// TCC Etec ZL - 3º Novotec Desenvolvimento de Sistemas Manhã - Henrique Macedo, Lucas Rosa, Matheus Santana

// definição dos pinos para os sensor - sensor de vazamento de gás, detector de presença/movimento e sensor de alagamento
int sensorgas = 14;
int presenca = 2;
int sensoragua = 15;

void setup() {
  // inicializando o monitor serial e definindo pinos como input
  Serial.begin(9600);
  pinMode(presenca, INPUT);
  pinMode(sensoragua, INPUT);
}

void loop() {
  // leitura e armazenamento do nível de gás
  int nivelgas = analogRead(sensorgas);
  // informando o nível de gás e se é normal ou não
  Serial.println((String) "Nível de gás:" + nivelgas);
  if (nivelgas > 200) {
    Serial.println("Nível anormal de gás.");
  } else {
    Serial.println("Nível normal de gás.");
  }

  // detecta e informa presença
  if (digitalRead(presenca) == HIGH) {
    Serial.println("movimento detectado");
  } else {
    Serial.println("movimento não detectado");
  };

  // leitura do sensor de água e condicional para ver se é a umidade normal ou se há alagamento
  int nivelagua = analogRead(sensoragua);
  Serial.println(nivelagua);
  if (nivelagua > 700) {
    Serial.println("Alagamento detectado");
  } else {
    Serial.println("Sem alagamento");
  };

  delay(2000);
}
