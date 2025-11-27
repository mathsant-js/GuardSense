Write-Host "=============================="
Write-Host " GuardSense - Setup de Keystore (Windows)"
Write-Host "=============================="

$KeystoreDir = "$env:LOCALAPPDATA\guardsense\keystore"
$KeystoreName = "release.jks"
$KeystorePath = "$KeystoreDir\$KeystoreName"

$PropertiesFile = ".\app\keystore.properties"

Write-Host "-> Criando diretório..."
New-Item -ItemType Directory -Force -Path $KeystoreDir | Out-Null

Write-Host "-> Copiando keystore..."
Copy-Item ".\$KeystoreName" $KeystorePath -Force

Write-Host "-> Solicitando senhas..."
$StorePassword = Read-Host "Digite a storePassword"
$KeyPassword = Read-Host "Digite a keyPassword"
$KeyAlias = Read-Host "Digite o keyAlias (padrão: release)"

if ([string]::IsNullOrWhiteSpace($KeyAlias)) {
    $KeyAlias = "release"
}

Write-Host "-> Criando keystore.properties..."
@"
storeFile=$KeystorePath
storePassword=$StorePassword
keyAlias=$KeyAlias
keyPassword=$KeyPassword
"@ | Out-File $PropertiesFile -Encoding UTF8

Write-Host "=============================="
Write-Host " Setup concluído!"
Write-Host " Keystore instalada em:"
Write-Host "   $KeystorePath"
Write-Host "=============================="
