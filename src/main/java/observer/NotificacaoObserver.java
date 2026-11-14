package observer;

import event.Observer;
import event.events.EstoqueAbaixoDoMinimo;

public class NotificacaoObserver implements Observer {
		
		@Override
		public void atualizar(Object evento) {
			
			if (evento instanceof EstoqueAbaixoDoMinimo estoque) {
            System.out.println("\nALERTA DE ESTOQUE ");
            System.out.println("Produto: " + estoque.nomeProduto());
            System.out.println("Quantidade atual: " + estoque.quantidadeAtual());
            System.out.println("Estoque mínimo: " + estoque.estoqueMinimo());
		}
		
	}
	
}
