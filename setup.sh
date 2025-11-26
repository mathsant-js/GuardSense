#!/bin/bash

echo "=============================="
echo " GuardSense – Setup de Keystore"
echo "=============================="

# Diretório onde a keystore ficará armazenada
KEYSTORE_DIR="$HOME/.local/share/guardsense/keystore"

# Nome padrão da keystore
KEYSTORE_NAME="guardsense-release.keystore"

# Caminho completo
KEYSTORE_PATH="$KEYSTORE_DIR/$KEYSTORE_NAME"

# Caminho para o app
PROPERTIES_FILE="./app/keystore.properties"

echo "-> Criando diretório seguro..."
mkdir -p "$KEYSTORE_DIR"

echo "-> Copiando keystore..."
cp "./$KEYSTORE_NAME" "$KEYSTORE_PATH"

echo "-> Ajustando permissões..."
chmod 600 "$KEYSTORE_PATH"

echo "-> Gerando keystore.properties..."

# Solicitar senha ao usuário
read -sp "Digite a storePassword: " STORE_PASSWORD
echo
read -sp "Digite a keyPassword: " KEY_PASSWORD
echo
read -p "Digite o keyAlias (padrão: release): " KEY_ALIAS

KEY_ALIAS=${KEY_ALIAS:-guardsense}

cat > "$PROPERTIES_FILE" <<EOF
storeFile=$KEYSTORE_PATH
storePassword=$STORE_PASSWORD
keyAlias=$KEY_ALIAS
keyPassword=$KEY_PASSWORD
EOF

echo
echo "=============================="
echo " Setup concluído com sucesso!"
echo " Keystore instalada em:"
echo "   $KEYSTORE_PATH"
echo " Arquivo keystore.properties criado!"
echo "=============================="

