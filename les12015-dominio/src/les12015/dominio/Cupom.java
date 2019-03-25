package les12015.dominio;

import java.util.Date;

public class Cupom extends EntidadeDominio {
	private String serial;
	private Date dtVal;
	private double desconto;
	private boolean tpCupom;

	public boolean getTpCupom() {
		return tpCupom;
	}

	public void setTpCupom(boolean tpCupom) {
		this.tpCupom = tpCupom;
	}

	public String getSerial() {
		return serial;
	}

	public Date getDtVal() {
		return dtVal;
	}

	public void setDtVal(Date dtVal) {
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
