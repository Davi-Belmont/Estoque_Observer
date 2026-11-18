
package service;

import model.Produto;
import event.EventPublisher;
import event.events.EstoqueAbaixoDoMinimo;

public class MovimentacaoService {

    private final EventPublisher eventPublisher;

    public MovimentacaoService(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registrarEntrada(Produto produto, int quantidade) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }

        produto.adicionarEstoque(quantidade);

        System.out.println("Entrada registrada com sucesso!");
    }

    public void registrarSaida(Produto produto, int quantidade) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }

        boolean estavaAbaixoDoMinimo = produto.estoqueAbaixoDoMinimo();

        produto.removerEstoque(quantidade);

        System.out.println("Produto removido com sucesso!");

        boolean estaAbaixoDoMinimo = produto.estoqueAbaixoDoMinimo();

        if (!estavaAbaixoDoMinimo && estaAbaixoDoMinimo) {

            EstoqueAbaixoDoMinimo evento = new EstoqueAbaixoDoMinimo(
                    produto.getId(),
                    produto.getNome(),
                    produto.getQuantidade(),
                    produto.getEstoqueMinimo()
            );

            eventPublisher.publicar(evento);
        }
    }
}
