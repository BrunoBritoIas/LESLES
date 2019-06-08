package les12015.dominio;

import java.util.List;

public class Unidade extends EntidadeDominio {
	private String dtPedido;
	private List<Troca> troca;
	private Suplementos sup;
	private String categoria;
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

	public String getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
}
