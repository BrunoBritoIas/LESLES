package les12015.dominio;

public class Troca extends EntidadeDominio {
	private Cliente user;
	private Suplementos produto;
	private Integer idUnidade;
	private Double  qtdCredito;
	private Integer  nota;
	private Integer  qtdItens;
	private Integer  idUser;
	private Integer  idSup;
	private String status;
	
	public Integer getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}
	public Double getQtdCredito() {
		return qtdCredito;
	}
	public void setQtdCredito(Double qtdCredito) {
		this.qtdCredito = qtdCredito;
	}
	public Integer getIdSup() {
		return idSup;
	}
	public void setIdSup(Integer idSup) {
		this.idSup = idSup;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public Cliente getUser() {
		return user;
	}
	public void setUser(Cliente user) {
		this.user = user;
	}
	public Suplementos getProduto() {
		return produto;
	}
	public void setProduto(Suplementos produto) {
		this.produto = produto;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	
	
	
	
	
	

}
