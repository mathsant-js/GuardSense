// TCC Etec ZL - 3º Novotec Desenvolvimento de Sistemas Manhã - Henrique Macedo, Lucas Rosa, Matheus Santana

#include <DHT.h>
#include <Adafruit_Sensor.h>

// definição do pino e tipo para o DHT
#define DHTPIN 3
#define DHTTYPE DHT11

// declarando o DHT
DHT dht = DHT(DHTPIN, DHTTYPE);

// definição dos pinos para os sensor - sensor de vazamento de gás, detector de presença/movimento e sensor de alagamento
int sensorgas = 14;
int presenca = 2;
int sensoragua = 15;

void setup() {
  // inicializando o monitor serial e definindo pinos como input
  Serial.begin(9600);
  pinMode(presenca, INPUT);
  pinMode(sensoragua, INPUT);
  // iniciando o DHT
  dht.begin();
}

void loop() {
  // lendo e exibindo a temperatura
  Serial.print("Temperatura: ");
  Serial.print(dht.readTemperature(), 0);
  Serial.println(" °C");
  // lendo e exibindo a umidade
  Serial.print("Umidade: ");
  Serial.println((String) dht.readHumidity() +"%");

  // leitura e armazenamento do nível de gás
  int nivelgas = analogRead(sensorgas);
  // informando o nível de gás e se é normal ou não
  Serial.println((String) "Nível de gás: " + nivelgas);
  if (nivelgas > 300) {
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
  Serial.println((String) "Nível de água: " +nivelagua);
  if (nivelagua > 600) {
    Serial.println("Alagamento detectado");
  } else {
    Serial.println("Sem alagamento");
  };

  Serial.println(" ");
  delay(5000);
}
