package les12015.core.impl.negocio;

import java.sql.SQLException;

import les12015.core.IStrategy;
import les12015.core.impl.dao.ClienteDAO;
import les12015.core.impl.dao.SuplementoDAO;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Pedido;
import les12015.dominio.Suplementos;
import les12015.dominio.Troca;

public class ValidaPedido implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Pedido) {
			Pedido ped = (Pedido) entidade;

			Double precoTotal = ped.getPrecoTotal();
			Double precoFrete = ped.getPrecoFrete();
			Double precoFinal = ped.getPrecoFinal();
			Integer idEndereco = ped.getIdEnd();
			Integer quantidade = 0;

			if (ped.getCardPed() == null || ped.getCardPed().size() <= 0) {
				return "Selecione o cartão de Credito";
			}

			Suplementos s = new Suplementos();
			SuplementoDAO supimpa = new SuplementoDAO();
			for (int i = 0; i < ped.getUnidade().size(); i++) {
				s = new Suplementos();
				supimpa = new SuplementoDAO();
				s.setId(ped.getUnidade().get(i).getSup().getId());
				s.setSupPedido(true);
				s = (Suplementos) supimpa.consultar(s).get(0);
				quantidade = ped.getUnidade().get(i).getQuantidade();

				if (quantidade > s.getQuantidade()) {
					return "Quantidade de Suplementos maior que o estoque";
				}

			}

			if (idEndereco == 0 || idEndereco == null) {
				return "Selecione o Endereço de Entrega";
			}

			if (precoTotal == null || precoFrete == null || precoFinal == null || idEndereco == null || precoTotal == 0
					|| precoFrete == 0 || precoFinal == 0 || quantidade == 0 || idEndereco == 0) {
				return "Dados Cadastrais de Supplementos são obrigatorios";

			}

			if (precoTotal == 0 || precoFrete == 0 || precoFinal == 0 | quantidade == 0) {
				return "Dados Cadastrais de Supplementos são obrigatorios";

			}

			for (int i = 0; i < ped.getUnidade().size(); i++) {
				s = new Suplementos();
				supimpa = new SuplementoDAO();
				s.setId(ped.getUnidade().get(i).getSup().getId());
				s.setSupPedido(true);
				s = (Suplementos) supimpa.consultar(s).get(0);
				quantidade = ped.getUnidade().get(i).getQuantidade();
				s.setQuantidade(s.getQuantidade() - quantidade);
				try {
					supimpa.alterar(s);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ped.isUsaCredito()) {
				Cliente c = new Cliente();
				ClienteDAO cDAO = new ClienteDAO();
				c.setId(ped.getIDusuario());
				c = (Cliente) cDAO.consultar(c).get(0);
				c.setSaldo(ped.getSaldoCliente());
				EntidadeDominio e = new EntidadeDominio();
				e = c;
				try {
					cDAO.alterar(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
		return null;

	}

}
