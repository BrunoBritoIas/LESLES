package les12015.dominio;

public class Unidade extends EntidadeDominio {
	private Suplementos sup;
	private int quantidade;
	private double preco;
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Suplementos getLivro() {
		return sup;
	}

	public void setLivro(Suplementos sup) {
		this.sup = sup;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
