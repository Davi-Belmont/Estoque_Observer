package view;

import model.Produto;
import repository.ProdutoRepository;
import service.MovimentacaoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoService movimentacaoService;

    public Menu(
            Scanner scanner,
            ProdutoRepository produtoRepository,
            MovimentacaoService movimentacaoService
    ) {
        this.scanner = scanner;
        this.produtoRepository = produtoRepository;
        this.movimentacaoService = movimentacaoService;
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrarProduto();
                    case 2 -> consultarProduto();
                    case 3 -> listarProdutos();
                    case 4 -> registrarEntrada();
                    case 5 -> registrarSaida();
                    case 0 -> System.out.println("\nEncerrando o sistema...");
                    default -> System.out.println("\nOpção inválida!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("       SISTEMA DE GESTÃO DE ESTOQUE");
        System.out.println("[1] Cadastrar produto");
        System.out.println("[2] Consultar produto");
        System.out.println("[3] Listar produtos");
        System.out.println("[4] Registrar entrada");
        System.out.println("[5] Registrar saída");
        System.out.println("[0] Sair");
    }

    private void cadastrarProduto() {
        System.out.println("\n--- CADASTRAR PRODUTO ---");

        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        BigDecimal preco = lerPreco("Preço unitário: ");

        int quantidade = lerInteiro("Quantidade inicial: ");

        int estoqueMinimo = lerInteiro("Estoque mínimo: ");

        Produto produto = new Produto(
                id,
                nome,
                descricao,
                preco,
                quantidade,
                estoqueMinimo
        );

        produtoRepository.cadastrar(produto);

        System.out.println("\nProduto cadastrado com sucesso!");
    }

    private void consultarProduto() {
        System.out.println("\n--- CONSULTAR PRODUTO ---");

        System.out.print("Informe o ID: ");
        String id = scanner.nextLine();

        Produto produto = produtoRepository.buscarPorId(id);

        if (produto == null) {
            System.out.println("\nProduto não encontrado.");
            return;
        }

        exibirProduto(produto);
    }

    private void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");

        List<Produto> produtos = produtoRepository.listarTodos();

        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado.");
            return;
        }

        for (Produto produto : produtos) {
            exibirProduto(produto);
        }
    }

    private void registrarEntrada() {
        System.out.println("\n--- REGISTRAR ENTRADA ---");

        Produto produto = localizarProduto();

        if (produto == null) {
            return;
        }

        int quantidade = lerInteiro("Quantidade de entrada: ");

        movimentacaoService.registrarEntrada(produto, quantidade);
    }

    private void registrarSaida() {
        System.out.println("\n--- REGISTRAR SAÍDA ---");

        Produto produto = localizarProduto();

        if (produto == null) {
            return;
        }

        int quantidade = lerInteiro("Quantidade de saída: ");

        movimentacaoService.registrarSaida(produto, quantidade);
    }

    private Produto localizarProduto() {
        System.out.print("Informe o ID do produto: ");
        String id = scanner.nextLine();

        Produto produto = produtoRepository.buscarPorId(id);

        if (produto == null) {
            System.out.println("\nProduto não encontrado.");
        }

        return produto;
    }

    private void exibirProduto(Produto produto) {
        System.out.println("\n------------------------------------");
        System.out.println("ID: " + produto.getId());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Preço: R$ " + produto.getPreco());
        System.out.println("Quantidade: " + produto.getQuantidade());
        System.out.println("Estoque mínimo: " + produto.getEstoqueMinimo());
        System.out.println("------------------------------------");
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private BigDecimal lerPreco(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                String valor = scanner.nextLine().trim().replace(",", ".");
                return new BigDecimal(valor);
            } catch (NumberFormatException e) {
                System.out.println("Digite um preço válido.");
            }
        }
    }
}
