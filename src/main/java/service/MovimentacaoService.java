package service;

import model.Produto;
import event.EventPublisher;
import event.events.EstoqueAbaixoDoMinimo;

public class MovimentacaoService {

	private final EventPublisher eventPublisher;
	
    public MovimentacaoService(EventPublisher eventPublisher) {
    	this.eventPublisher = eventPublisher;
	}
    
    // registrar entrada 
    public void registrarEntrada(Produto produto, int quantidade) {
    	produto.adicionarEstoque(quantidade);
    	System.out.println("Entrada Registrada com sucesso!");
    }
    
    // registra saida
    public void registrarSaida(Produto produto, int quantidade) {
    	boolean estavaAbaixoDoMinimo = produto.estoqueAbaixoDoMinimo();
    	
    	produto.removerEstoque(quantidade);
    	
    	System.out.println("Produto removido com sucesso!");

    	boolean estaAbaixoDoMinimo = produto.estoqueAbaixoDoMinimo();
    	
    	// ver se o estoque passou a fica abaixo do minimo
    	if(!estavaAbaixoDoMinimo && estaAbaixoDoMinimo) {
    		
    		EstoqueAbaixoDoMinimo evento = 
    				new EstoqueAbaixoDoMinimo(
    						produto.getId(),
    						produto.getNome(),
    						produto.getQuantidade(),
    						produto.getEstoqueMinimo()
    						);
    		
    		eventPublisher.publicar(evento);
    	}
    }
}
