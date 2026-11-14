package observer;

import event.Observer;
import event.events.EstoqueAbaixoDoMinimo;

public class SolicitacaoReposicaoObserver implements Observer {

    @Override
    public void atualizar(Object evento) {

        if (evento instanceof EstoqueAbaixoDoMinimo estoque) {

            System.out.println("\nSOLICITAÇÃO DE REPOSIÇÃO");
            System.out.println("Produto: " + estoque.nomeProduto());
            System.out.println("Quantidade atual: " + estoque.quantidadeAtual());
            System.out.println("Estoque mínimo: " + estoque.estoqueMinimo());
            System.out.println("Ação: Necessário solicitar reposição!");
        }
    }
}