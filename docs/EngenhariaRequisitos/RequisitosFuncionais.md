## Requisitos Funcionais

### Autenticação e Usuários

- **RF01 - Manter Conta:** O usuário poderá criar a sua conta usando email/senha (via Firebase Auth), visualizar os dados da sua conta, atualizá-los e caso deseje cancelar a assinatura, poder entrar em contato com o suporte para o cancelamento.

- **RF02 – Fazer Login:** O sistema deve permitir que o usuário faça login para acessar as funcionalidades.

- **RF03 – Recuperação de senha:** O sistema deve permitir recuperação de senha via e-mail.

### Coleta e Monitoramento de Dados

- **RF04 – Receber dados do ESP:** O sistema deve receber informações dos sensores enviadas pelo dispositivo ESP para o Firebase que o App acessa.

- **RF05 – Verificar Leitura dos Sensores:** O app deve mostrar na tela principal os valores mais recentes dos sensores (ex: movimento, temperatura, gás, etc.).

- **RF06 - Exibir imagem da câmera de monitoramento:** O sistema deve exibir a imagem da câmera de monitoramento do sistema em tempo real

- **RF07 - Gerenciar porta:** O usuário deve ser capaz de abrir e fechar a porta pelo app ou pela maneira que preferir (digital, senha ou escaneamento facial)

- **RF08 - Gerenciar Sensores:** O usuário deve ser capaz de ligar e desligar qual sensores do sistema ele preferir

- **RF09 – Consultar Histórico dos Sensores:** O usuário deve poder visualizar relatórios ou listas de leituras passadas.

### Alertas e Segurança

- **RF10 – Notificações de eventos:** O sistema deve enviar notificação ao usuário caso algum sensor identifique um evento crítico (ex: movimento detectado, fumaça, temperatura anormal).

### Navegação e Interface

- **RF11 – Tela inicial (Dashboard):** Deve exibir o status dos sensores em tempo real.

- **RF12 – Tela de histórico:** Deve permitir consultar dados salvos no Firebase.

- **RF13 – Tela de configuração:** Deve permitir ajustar preferências, como limites de alerta, notificações e logout.

- **RF14 – Navegação entre telas:** O usuário deve poder navegar entre Login → Dashboard → Histórico → Configurações.

### Integração com Firebase

- **RF15 – Enviar Dados para o Firebase:** O app deve enviar os dados recebidos do ESP para o banco de dados em nuvem.
