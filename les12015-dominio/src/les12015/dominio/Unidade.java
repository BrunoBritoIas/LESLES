package les12015.dominio;

import java.util.List;

public class Unidade extends EntidadeDominio {
	private List<Troca> troca;
	private Suplementos sup;
	private int quantidade;
	private int idSup;
	private double preco;
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Suplementos getSup() {
		return sup;
	}

	public void setSup(Suplementos sup) {
		this.sup = sup;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getIdSup() {
		return idSup;
	}

	public void setIdSup(int idSup) {
		this.idSup = idSup;
	}

	public List<Troca> getTroca() {
		return troca;
	}

	public void setTroca(List<Troca> troca) {
		this.troca = troca;
	}
	
	
	
}
