package les12015.dominio;

public class Suplementos extends EntidadeDominio {

	private String nome;
	private String descricao;
	private String marca;
	private Double peso;
	private Double preco;
	private String categoria;
	private Double proteina;
	private Double carboidratos;
	private Double gordura;
	private Double calorias;
	private String sabor;
	private Double rating;
	private String status;
	private String validade;
	private boolean supPedido;
	private int quantidade;
	public String getNome() {
		return nome;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getProteina() {
		return proteina;
	}
	public void setProteina(Double proteina) {
		this.proteina = proteina;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Double getCarboidratos() {
		return carboidratos;
	}
	public void setCarboidratos(Double carboidratos) {
		this.carboidratos = carboidratos;
	}
	public Double getGordura() {
		return gordura;
	}
	public void setGordura(Double gordura) {
		this.gordura = gordura;
	}
	public Double getCalorias() {
		return calorias;
	}
	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}
	public String getSabor() {
		return sabor;
	}
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public boolean isSupPedido() {
		return supPedido;
	}

	public void setSupPedido(boolean supPedido) {
		this.supPedido = supPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
