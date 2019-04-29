package les12015.dominio;



public class Cupom extends EntidadeDominio {
	private String serial;
	private String dtVal;
	private double desconto;
	private String tpCupom;

	

	public String getTpCupom() {
		return tpCupom;
	}

	public void setTpCupom(String tpCupom) {
		this.tpCupom = tpCupom;
	}

	public String getSerial() {
		return serial;
	}

	public String getDtVal() {
		return dtVal;
	}

	public void setDtVal(String dtVal) {
		this.dtVal = dtVal;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

}
