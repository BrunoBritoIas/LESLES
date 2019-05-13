package les12015.core.impl.negocio;

import java.sql.SQLException;

import les12015.core.IStrategy;
import les12015.core.impl.dao.ClienteDAO;
import les12015.core.impl.dao.SuplementoDAO;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Troca;

public class ValidaTroca implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Troca) {
			Cliente c = new Cliente();
			SuplementoDAO pDAO = new SuplementoDAO();
			ClienteDAO cDAO = new ClienteDAO();
			Troca t = new Troca();
			Troca troca = (Troca) entidade;
			if (troca.getStatus().equals("APROVADO")) {
				c.setId(t.getIdUser());
				c = (Cliente) cDAO.consultar(c).get(0);
				c.setSaldo(troca.getQtdCredito() + c.getSaldo());
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
