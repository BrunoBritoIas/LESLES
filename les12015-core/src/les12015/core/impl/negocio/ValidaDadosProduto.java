package les12015.core.impl.negocio;

import les12015.core.IStrategy;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;

public class ValidaDadosProduto implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		if (entidade instanceof Suplementos) {
			Suplementos sup = (Suplementos) entidade;

			String nome = sup.getNome();
			String marca = sup.getMarca();
			String validade = sup.getValidade();
			String descricao = sup.getDescricao();
			Double peso = sup.getPeso();
			String categoria = sup.getCategoria();
			Double proteina = sup.getProteina();
			Double carboidrato = sup.getCarboidratos();
			Double gordura = sup.getGordura();
			Double caloria = sup.getCalorias();
			Double preco = sup.getPreco();
			Integer quantidade = sup.getQuantidade();

			if (nome == null || marca == null || validade == null || descricao == null || peso == 0 || categoria == null ||quantidade == null
					|| proteina == 0 || carboidrato == 0 || gordura == 0 || caloria == 0 || preco == 0 ||quantidade == 0) {
				return "Dados Cadastrais de Supplementos são obrigatorios";

			}

			if (nome.trim().equals("") || marca.trim().equals("") || validade.trim().equals("")
					|| descricao.trim().equals("") || categoria.trim().equals("")) {
				return "Dados Cadastrais de Supplementos são obrigatorios";
			}

			if (proteina <= 0 || caloria <= 0 || proteina <= 0 || carboidrato <= 0 || gordura <= 0 || peso <= 0 ||quantidade <= 0
					|| preco <= 0) {
				return "Este valor tem que ser maior que zero";
			}

		} else {
			return "Registro de Suplementos";
		}

		return null;
	}
}
