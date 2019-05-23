package les12015.dominio;

import java.util.List;

public class Pedido extends EntidadeDominio {
	private List<Unidade> unidade;
	private List<CartaoPedido> cardPed;
	private Endereco endereco;
	private Cupom cupomPromocional;
	private String dtPedido;
	private String status;
	private Integer IDusuario;
	private String nomeUser;
	private String cpfUser;
	private Integer idEnd;
	private Double qtdItens;
	private double precoTotal;
	private double precoFrete;
	private double precoFinal;
	private double saldoCliente;
	private double saldoUsado;
	private boolean prodDetail;
	private boolean consultaPedidos;
	private boolean usaCredito;

	public Cupom getCupomPromocional() {
		return cupomPromocional;
	}

	public void setCupomPromocional(Cupom cupomPromocional) {
		this.cupomPromocional = cupomPromocional;
	}

	public Double getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(Double qtdItens) {
		this.qtdItens = qtdItens;
	}

	public double getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(double precoFinal) {
		this.precoFinal = precoFinal;
	}

	public double getPrecoFrete() {
		return precoFrete;
	}

	public void setPrecoFrete(double precoFrete) {
		this.precoFrete = precoFrete;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public List<Unidade> getUnidade() {
		return unidade;
	}

	public void setUnidade(List<Unidade> unidade) {
		this.unidade = unidade;
	}

	public String getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getIDusuario() {
		return IDusuario;
	}

	public void setIDusuario(Integer iDusuario) {
		IDusuario = iDusuario;
	}

	public List<CartaoPedido> getCardPed() {
		return cardPed;
	}

	public void setCardPed(List<CartaoPedido> cardPed) {
		this.cardPed = cardPed;
	}

	public boolean isProdDetail() {
		return prodDetail;
	}

	public void setProdDetail(boolean prodDetail) {
		this.prodDetail = prodDetail;
	}

	public Integer getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(Integer idEnd) {
		this.idEnd = idEnd;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getCpfUser() {
		return cpfUser;
	}

	public void setCpfUser(String cpfUser) {
		this.cpfUser = cpfUser;
	}

	public boolean isConsultaPedidos() {
		return consultaPedidos;
	}

	public void setConsultaPedidos(boolean consultaPedidos) {
		this.consultaPedidos = consultaPedidos;
	}

	public boolean isUsaCredito() {
		return usaCredito;
	}

	public void setUsaCredito(boolean usaCredito) {
		this.usaCredito = usaCredito;
	}

	public double getSaldoCliente() {
		return saldoCliente;
	}

	public void setSaldoCliente(double saldoCliente) {
		this.saldoCliente = saldoCliente;
	}

	public double getSaldoUsado() {
		return saldoUsado;
	}

	public void setSaldoUsado(double saldoUsado) {
		this.saldoUsado = saldoUsado;
	}
	

}
