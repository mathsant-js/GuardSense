// TCC Etec ZL - 3º Novotec Desenvolvimento de Sistemas Manhã - Henrique Macedo, Lucas Rosa, Matheus Santana

// incluindo as bibliotecas necessárias
#include <LiquidCrystal_I2C.h>
#include <Keypad.h>

// definição de atributos do display lcd: modelo, colunas e linhas
LiquidCrystal_I2C lcd(0x27, 16, 2);

// quantidade de linhas e colunas
const byte linhas = 4;
const byte colunas = 4;

// definição dos caracteres de cada tecla do teclado
char matriz_teclado[linhas][colunas] = {
  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'},
};

// pinos para linhas e colunas do teclado
byte pinosLinhas[linhas] = {9, 8, 7, 6};
byte pinosColunas[colunas] = {4, 5, 3, 2};

// criação do objeto Keypad correspondente ao teclado
Keypad tecladosenha = Keypad(makeKeymap(matriz_teclado), pinosLinhas, pinosColunas, linhas, colunas);

// definição do pino do sensor de vibração
int vibracao = 11;

void setup()
{
  // definindo o pino do sensor de vibração, inicializando o monitor serial, e iniciando o display lcd
  pinMode(vibracao, INPUT);
  Serial.begin(9600);
  lcd.init();
  lcd.backlight();
  lcd.clear();
  Serial.println("Digite a Senha: ");
  Serial.println();
}

void loop()
{
  // verificando e informando se há vibração
  if (digitalRead(vibracao) == HIGH) {
    Serial.println("alto");
  } else {
    Serial.println("baixo");
  };

  lcd.setCursor(0, 0);
  // string para a senha que o usuário digitar
  String digitada = "senha";
  // string para a senha correta - remover depois e inserir no banco de dados
  String senha = "123";
  int i = 0;
  digitada = "";
  // laço de repetição para adicionar cada dígito que o usuário inserir à string digitada
  while (i <= 2) {
    char tecla_pressionada = tecladosenha.getKey();
  	if (tecla_pressionada) {
      lcd.print(tecla_pressionada);
      digitada = digitada + tecla_pressionada;
      i++;
  	}
  }
  
  lcd.setCursor(0, 1);
  
  // conferir e exibir se a senha digitada estava certa ou errada
  if (digitada == senha) {
    lcd.print("Senha Correta");
  } else {
    lcd.print("Senha Incorreta");
  }
  
  delay(3000);
  // limpa o display
  lcd.clear();
}