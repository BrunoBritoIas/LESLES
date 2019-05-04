package les12015.dominio;

public class CartaoPedido extends EntidadeDominio {

	private String numCartao;
	private String validade;
	private String bandeira;
	private Double numParcela;
	private Double vlrParcela;
	private Double totalParcela;
	
	
	public Double getNumParcela() {
		return numParcela;
	}
	public void setNumParcela(Double numParcela) {
		this.numParcela = numParcela;
	}
	public Double getVlrParcela() {
		return vlrParcela;
	}
	public void setVlrParcela(Double vlrParcela) {
		this.vlrParcela = vlrParcela;
	}
	public Double getTotalParcela() {
		return totalParcela;
	}
	public void setTotalParcela(Double totalParcela) {
		this.totalParcela = totalParcela;
	}
	public String getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	

}
