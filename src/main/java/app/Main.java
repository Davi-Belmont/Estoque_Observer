package app;
import event.EventBus;

import observer.NotificacaoObserver;
import observer.SolicitacaoReposicaoObserver;
import repository.ProdutoRepository;
import service.MovimentacaoService;
import view.Menu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Inicialização do EventBus
        EventBus eventBus = new EventBus();

        // Registro dos observadores
        eventBus.inscrever(new NotificacaoObserver());
        eventBus.inscrever(new SolicitacaoReposicaoObserver());

        // Inicialização do repositório
        ProdutoRepository produtoRepository = new ProdutoRepository();

        // Inicialização do serviço
        MovimentacaoService movimentacaoService =
                new MovimentacaoService(eventBus);

        // Scanner compartilhado
        Scanner scanner = new Scanner(System.in);

        // Menu interativo
        Menu menu = new Menu(
                scanner,
                produtoRepository,
                movimentacaoService
        );

        menu.iniciar();

        scanner.close();
    }
}
