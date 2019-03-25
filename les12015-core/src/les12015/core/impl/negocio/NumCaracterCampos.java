package les12015.core.impl.negocio;

import les12015.core.IStrategy;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;

public class NumCaracterCampos implements  IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Suplementos) {
			
			Suplementos sup = (Suplementos) entidade;

			String nome = sup.getNome();
			String marca = sup.getMarca();		
			String descricao = sup.getDescricao();
			Double peso = sup.getPeso();
			String categoria = sup.getCategoria();

			
			if(nome.length()>50|| marca.trim().length()>50 ||  descricao.length()>100 || categoria.length()>50) {
				return "Nome com numeros de caracteres maior que o permitido";
			}
			if( marca.trim().length()>50 ) {
				return "Marca com numeros de caracteres maior que o permitido";
			}
			if(descricao.length()>100) {
				return "Descrição com numeros de caracteres maior que o permitido";
			}
			if(peso>1000) {
				return "Peso do Produto excede o limite permitido por unidade";
			}

		} else {
			return "Registro de Suplementos";
		}

		return null;
	}
}