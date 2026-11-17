package repository;

import model.Produto;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProdutoRepository {

    private final Map<String, Produto> produtos = new HashMap<>();

    public void cadastrar(Produto produto) {

        String id = produto.getId();

        if (produtos.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Já existe um produto com esse ID."
            );
        }

        produtos.put(id, produto);
    }

    public Produto buscarPorId(String id) {
        return produtos.get(id);
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());
    }
}
