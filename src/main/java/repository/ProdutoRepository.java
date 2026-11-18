package repository;

import model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoRepository {

    private final Map<String, Produto> produtos = new HashMap<>();

    public void cadastrar(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }

        String id = produto.getId();

        if (produtos.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Já existe um produto com esse ID."
            );
        }

        produtos.put(id, produto);
    }

    public Produto buscarPorId(String id) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido.");
        }

        return produtos.get(id);
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());
    }
}
