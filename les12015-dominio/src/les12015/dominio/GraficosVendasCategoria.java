package les12015.dominio;

import java.util.ArrayList;

public class GraficosVendasCategoria extends EntidadeDominio {
	private String categoria;
	private ArrayList<Integer> qtdMes = new ArrayList<Integer>();
	private Integer ano;
	private Integer mes1;
	private Integer mes2;
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ArrayList<Integer> getQtdMes() {
		return qtdMes;
	}

	public void setQtdMes(ArrayList<Integer> qtdMes) {
		this.qtdMes = qtdMes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes1() {
		return mes1;
	}

	public void setMes1(Integer mes1) {
		this.mes1 = mes1;
	}

	public Integer getMes2() {
		return mes2;
	}

	public void setMes2(Integer mes2) {
		this.mes2 = mes2;
	}
	
	
	
	
	

	

}
