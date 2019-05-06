package les12015.core.impl.negocio;

import les12015.core.IStrategy;
import les12015.core.impl.dao.SuplementoDAO;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Pedido;
import les12015.dominio.Suplementos;

public class ValidaPedido implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Pedido) {
			Pedido ped = (Pedido) entidade;

			Double precoTotal = ped.getPrecoTotal();
			Double precoFrete = ped.getPrecoFrete();
			Double precoFinal = ped.getPrecoFinal();
			Integer quantidade = 0;

			for (int i = 0; i < ped.getUnidade().size(); i++) {
				Suplementos s = new Suplementos();
				SuplementoDAO supimpa = new SuplementoDAO();
				s.setId(ped.getUnidade().get(i).getSup().getId());
				s.setSupPedido(true);
				s = (Suplementos) supimpa.consultar(s).get(0);
				quantidade = ped.getUnidade().get(i).getQuantidade();
				
				if( quantidade > s.getQuantidade()) {
					return "Tem muito Suprimento ai Parça";
				}

			}

			/*
			 * if (nome == null || marca == null || validade == null || descricao == null ||
			 * peso == 0 || categoria == null ||quantidade == null || proteina == 0 ||
			 * carboidrato == 0 || gordura == 0 || caloria == 0 || preco == 0 ||quantidade
			 * == 0) { return "Dados Cadastrais de Supplementos são obrigatorios";
			 * 
			 * }
			 * 
			 * if (nome.trim().equals("") || marca.trim().equals("") ||
			 * validade.trim().equals("") || descricao.trim().equals("") ||
			 * categoria.trim().equals("")) { return
			 * "Dados Cadastrais de Supplementos são obrigatorios"; }
			 * 
			 * if (proteina <= 0 || caloria <= 0 || proteina <= 0 || carboidrato <= 0 ||
			 * gordura <= 0 || peso <= 0 ||quantidade <= 0 || preco <= 0) { return
			 * "Este valor tem que ser maior que zero"; }
			 */

		} else {
			return "Registro de Suplementos";
		}
		return null;
	}

}
