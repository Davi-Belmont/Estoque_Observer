# Sistema de Gestão de Estoque — Observer

## 1. Sobre o projeto

O **Sistema de Gestão de Estoque** é uma aplicação desenvolvida em Java com o objetivo de demonstrar a implementação do padrão de projeto comportamental **Observer**.

O sistema permite realizar operações básicas de gerenciamento de produtos, como cadastro, consulta, listagem e movimentação de estoque. Além disso, monitora a quantidade disponível dos produtos e identifica quando o estoque fica abaixo do limite mínimo estabelecido.

Quando essa condição acontece, o sistema emite um evento que notifica os observadores inscritos. Neste projeto, existem dois observadores:

- **NotificacaoObserver:** responsável por exibir um alerta informando que o estoque de determinado produto está abaixo do mínimo.
- **SolicitacaoReposicaoObserver:** simula uma solicitação de reposição do produto.

O projeto utiliza armazenamento em memória, sem necessidade de banco de dados ou serviços externos.

### Funcionalidades

- Cadastro de produtos.
- Consulta de produtos por ID.
- Listagem de produtos cadastrados.
- Registro de entradas no estoque.
- Registro de saídas do estoque.
- Monitoramento do estoque mínimo.
- Notificação automática quando o estoque fica abaixo do mínimo.
- Simulação de solicitação de reposição.

## 2. Padrão de projeto escolhido: Observer

O padrão **Observer** foi escolhido por ser adequado a situações em que diferentes objetos precisam ser notificados quando ocorre uma mudança de estado ou um evento em outro objeto.

No contexto deste sistema, o evento relevante acontece quando a quantidade de um produto fica abaixo do seu estoque mínimo.

Sem o Observer, a classe responsável pelas movimentações precisaria conhecer diretamente todas as ações que deveriam acontecer após essa condição, tornando o código mais dependente de implementações específicas.

Com o Observer, essa responsabilidade é separada: o serviço identifica a condição e publica um evento, enquanto os observadores inscritos decidem como reagir a ele.

### 2.1. Como o Observer funciona no projeto?

O funcionamento acontece da seguinte maneira:

1. O `MovimentacaoService` registra uma saída de estoque.
2. O sistema verifica se a quantidade ficou abaixo do estoque mínimo.
3. Caso o produto tenha passado de uma situação regular para uma situação abaixo do mínimo, um evento `EstoqueAbaixoDoMinimo` é criado.
4. O `EventPublisher` publica o evento.
5. O `EventBus` distribui o evento aos observadores inscritos.
6. Cada observador identifica o tipo de evento e executa sua própria ação.

### 2.2. Principais componentes

| Componente | Responsabilidade |
|---|---|
| `Observer` | Define o contrato que os observadores devem implementar. |
| `EventPublisher` | Define o contrato para publicação de eventos. |
| `EventBus` | Mantém os observadores inscritos e distribui os eventos. |
| `EstoqueAbaixoDoMinimo` | Representa o evento de estoque abaixo do limite mínimo. |
| `MovimentacaoService` | Coordena as movimentações e identifica quando deve publicar o evento. |
| `NotificacaoObserver` | Exibe um alerta de estoque baixo. |
| `SolicitacaoReposicaoObserver` | Simula uma solicitação de reposição. |

## 3. Justificativa da escolha

O Observer é o padrão mais adequado para o cenário proposto porque permite que diferentes partes do sistema reajam ao mesmo acontecimento sem que a classe responsável por identificar esse acontecimento precise conhecer cada uma delas.

Por exemplo, caso seja necessário adicionar futuramente um novo observador para registrar ocorrências em um histórico, essa funcionalidade poderá ser implementada criando uma nova classe que implemente `Observer` e realizando sua inscrição no `EventBus`.

Assim, não será necessário modificar a lógica principal de movimentação de estoque.

Entre as principais vantagens da escolha estão:

- **Baixo acoplamento:** o serviço de movimentação não depende diretamente dos observadores concretos.
- **Extensibilidade:** novos observadores podem ser adicionados sem modificar os já existentes.
- **Separação de responsabilidades:** cada observador executa uma ação específica.
- **Reutilização:** diferentes observadores podem reagir ao mesmo evento de maneiras distintas.

## 4. Outros padrões que poderiam ser utilizados

Embora o Observer seja a escolha principal para este projeto, outros padrões também poderiam contribuir para a organização do sistema.

### Strategy

O padrão Strategy poderia ser utilizado caso o sistema tivesse diferentes estratégias de reposição de estoque, como reposição por quantidade fixa, reposição baseada na demanda ou reposição até atingir uma quantidade ideal.

Cada estratégia teria sua própria implementação, permitindo alternar a forma de reposição sem modificar a lógica que utiliza essas estratégias.

### Factory Method

O Factory Method poderia ser utilizado para centralizar a criação de diferentes tipos de produtos, caso o sistema passasse a trabalhar com categorias que exigissem regras específicas de criação.

Isso ajudaria a separar a lógica de criação dos objetos da lógica de utilização deles.

### Command

O padrão Command poderia ser aplicado às operações de entrada e saída de estoque, encapsulando cada movimentação em um objeto.

Uma possível vantagem seria permitir futuramente o registro das operações e a implementação de funcionalidades como desfazer uma movimentação (undo).

### Por que não utilizar esses padrões como principal solução?

Os padrões citados resolvem problemas diferentes. Strategy organiza algoritmos intercambiáveis, Factory Method organiza a criação de objetos e Command encapsula solicitações como objetos.
O problema central deste projeto, entretanto, é permitir que diferentes componentes reajam a uma alteração relevante no estoque.
Por esse motivo, o Observer atende mais diretamente à necessidade identificada, enquanto os demais padrões poderiam ser utilizados de forma complementar caso o sistema evoluísse.

## 5. Tecnologias utilizadas

- **Java 25:** linguagem utilizada no desenvolvimento.
- **Maven:** gerenciamento e organização da construção do projeto.
- **Git e GitHub:** controle de versão e hospedagem do código-fonte.

## 6. Considerações finais

O desenvolvimento deste projeto permite compreender na prática como o padrão Observer promove o desacoplamento entre objetos e facilita a comunicação baseada em eventos.
A aplicação demonstra como um único acontecimento, neste caso, a redução do estoque abaixo do limite mínimo, pode gerar diferentes reações independentes.
Além disso, a estrutura permite futuras expansões sem exigir grandes modificações na lógica principal do sistema.