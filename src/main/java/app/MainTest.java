package app;

import event.EventBus;
import model.Produto;
import observer.NotificacaoObserver;
import observer.SolicitacaoReposicaoObserver;
import repository.ProdutoRepository;
import service.MovimentacaoService;

import java.math.BigDecimal;

public class MainTest {

    public static void main(String[] args) {

        // Inicialização
        EventBus eventBus = new EventBus();

        eventBus.inscrever(new NotificacaoObserver());
        eventBus.inscrever(new SolicitacaoReposicaoObserver());

        ProdutoRepository repository = new ProdutoRepository();

        MovimentacaoService service =
                new MovimentacaoService(eventBus);

        System.out.println("DEMONSTRAÇÃO DO SISTEMA");

        // 1. Cadastro
        System.out.println("\n=== 1. CADASTRO DE PRODUTO ===");

        Produto produto = new Produto(
                "001",
                "Teclado",
                "Teclado mecânico",
                new BigDecimal("150.00"),
                10,
                5
        );

        repository.cadastrar(produto);

        System.out.println("Produto cadastrado com sucesso!");

        // 2. Consulta
        System.out.println("\n=== 2. CONSULTA DE PRODUTO ===");

        Produto encontrado = repository.buscarPorId("001");

        System.out.println(encontrado);

        // 3. Listagem
        System.out.println("\n=== 3. LISTAGEM DE PRODUTOS ===");

        for (Produto p : repository.listarTodos()) {
            System.out.println(p);
        }

        // 4. Entrada
        System.out.println("\n=== 4. ENTRADA DE ESTOQUE ===");

        service.registrarEntrada(produto, 5);

        System.out.println("Estoque atual: " + produto.getQuantidade());

        // 5. Saída
        System.out.println("\n=== 5. SAÍDA DE ESTOQUE ===");

        service.registrarSaida(produto, 11);

        System.out.println("Estoque atual: " + produto.getQuantidade());

        // 6. Consulta após movimentações
        System.out.println("\n=== 6. ESTOQUE ATUALIZADO ===");

        System.out.println(encontrado);

        System.out.println("DEMONSTRAÇÃO FINALIZADA");
    }
}
