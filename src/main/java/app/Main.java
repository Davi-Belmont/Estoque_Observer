package app;

import java.math.BigDecimal;

import event.EventBus;
import observer.NotificacaoObserver;
import service.MovimentacaoService;
import model.Produto;
import observer.SolicitacaoReposicaoObserver;

public class Main {

	public static void main(String [] args) {
		
		// criar evento 
		EventBus evento = new EventBus();
		
		// registrando observadores
		NotificacaoObserver notificacao = new NotificacaoObserver();
		evento.inscrever(notificacao);
		
		SolicitacaoReposicaoObserver reposicao =new SolicitacaoReposicaoObserver();
		evento.inscrever(reposicao);
		
		// criar movimentacao
		MovimentacaoService movimentacaoService = new MovimentacaoService(evento);
		
		
		// Criando um produto
		Produto produto = new Produto(
		    "001",
		    "Teclado",
		    "Teclado mecânico",
		    new BigDecimal("150.00"),
		    10,
		    5
		);

		// Registrando uma saída
		movimentacaoService.registrarSaida(produto, 6);
	}
}
