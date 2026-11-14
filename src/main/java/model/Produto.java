package model;
import java.math.BigDecimal;

public class Produto {
    
	private String id;
	private String nome;
	private String descricao;
	private BigDecimal precoUnitario;
	private int quantidade;
	private int estoqueMinimo;

	public Produto(String id, String nome, String descricao, BigDecimal precoUnitario, int quantidade, int estoqueMinimo) {
		
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
	}
	// gets
	public String getId() {return id;}
	public String getNome() {return nome;}
	public String getDescricao() {return descricao;}
	public BigDecimal getPreco() {return precoUnitario;}
	public int getQuantidade() {return quantidade;}
	public int getEstoqueMinimo() {return estoqueMinimo;}
	
	// set
	public void setNome(String nome) {
	this.nome = nome;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;	
	}
	public void setPreco(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	// controle de estoque
	public void adicionarEstoque(int quantidade) {
		
		this.quantidade += quantidade;
	}
	
	public void removerEstoque (int quantidade) {
		this.quantidade -= quantidade;
	}
	
	// estatus atual do estoque
	public boolean estoqueAbaixoDoMinimo() {
		return quantidade < estoqueMinimo;
	}
	
	@Override
	public String toString() {
		return "Produto {" +
	               "id='" + id + '\'' +
	                ", nome='" + nome + '\'' +
	                ", descricao='" + descricao + '\'' +
	                ", precoUnitario=" + precoUnitario +
	                ", quantidade=" + quantidade +
	                ", estoqueMinimo=" + estoqueMinimo +
	                '}';
		
	}
	
}
