package les12015.core.impl.negocio;

import java.sql.SQLException;

import les12015.core.IStrategy;
import les12015.core.impl.dao.SuplementoDAO;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Pedido;
import les12015.dominio.Suplementos;

public class CancelaPedido implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Pedido) {
			Pedido ped = (Pedido) entidade;
			Integer quantidade = 0;		
			Suplementos s = new Suplementos();
			SuplementoDAO supimpa = new SuplementoDAO();
			
			if (ped.getStatus().equals("CANCELADO") || ped.getStatus().equals("ADMCANCELADO")) {
				for (int i = 0; i < ped.getUnidade().size(); i++) {	
					s = new Suplementos();
					supimpa = new SuplementoDAO();
					s.setId(ped.getUnidade().get(i).getSup().getId());
					s.setSupPedido(true);
					s = (Suplementos) supimpa.consultar(s).get(0);
					quantidade = ped.getUnidade().get(i).getQuantidade();
					s.setQuantidade(s.getQuantidade() + quantidade);
					try {
						supimpa.alterar(s);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			

		}
		return null;

	}

}