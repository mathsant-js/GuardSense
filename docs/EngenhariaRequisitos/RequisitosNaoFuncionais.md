## Requisitos Não Funcionais do Sistema

### Segurança

**RNF01 - Autenticação Segura:** Todas as autenticações devem ser feitas usando Firebase Authentication com senha criptografadas.

**RNF02 - Autorização baseada em usuário:** Cada usuário só pode acessar os dados vinculados à sua conta.

### Disponibilidade

**RNF03 - Disponibilidade do sistema:** O app e banco de dados devem estar disponíveis 24/7

### Usabilidade

**RNF04 - Interface Intuitiva:** O app deve seguir boas práticas de Material Design e Jetpack Compose, garantindo navegação simples

**RNF05 - Multiplataforma:** O app deve ser compatível com Android 8 ou superior

### Manutenção e Evolução

**RNF06 - Arquitetura modular:** O app deve ser desenvolvido em MVVM, separando as camadas de UI, lógica e dados.

**RNF07 - Documentação:** O código deve ser documentado e possuir REDME explicando como o projeto foi desenvolvido, e mostrar como compilar e configurar.

**RNF08 - Integração com Firebase:** Deve funcionar em diferentes versões do Firebase (mantendo a retrocompatibilidade)
