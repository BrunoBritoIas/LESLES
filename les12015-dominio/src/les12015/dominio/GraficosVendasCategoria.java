package les12015.dominio;

import java.util.ArrayList;

public class GraficosVendasCategoria extends EntidadeDominio {
	private String categoria;
	private ArrayList<Integer> qtdMes = new ArrayList<Integer>();
	
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
	
	
	
	

	

}
