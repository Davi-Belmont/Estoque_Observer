package event.events;

public record EstoqueAbaixoDoMinimo (
        String produtoId,
        String nomeProduto,
        int quantidadeAtual,
        int estoqueMinimo
		) {}
