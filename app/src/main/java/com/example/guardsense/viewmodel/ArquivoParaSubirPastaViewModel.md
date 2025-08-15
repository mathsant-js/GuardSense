### Pasta do viewModel

*O viewmodel serve como um intermediário entre a view e a model.*

Ele tem a lógica para acessar os dados do model e trabalha os dados para um formato adequado para a view.

#### Como funciona

1. A View solicita dados ao ViewModel.
2. O ViewModel acessa os dados necessários do Model.
3. O ViewModel transforma os dados do Model em um formato que a View possa exibir.
4. A View exibe os dados fornecidos pelo ViewModel.

#### Exemplo

1. Quando o usuário interage com a View (por exemplo, clica em um botão), a View envia um comando ou evento para o ViewModel.
2. O ViewModel processa a interação e, se necessário, atualiza o Model.
3. A View é atualizada automaticamente pelo ViewModel, refletindo as mudanças no Model. 
