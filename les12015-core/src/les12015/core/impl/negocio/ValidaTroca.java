package les12015.core.impl.negocio;

import java.sql.SQLException;

import les12015.core.IStrategy;
import les12015.core.impl.dao.ClienteDAO;
import les12015.core.impl.dao.SuplementoDAO;
import les12015.core.impl.dao.UnidadePedidoDao;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;
import les12015.dominio.Troca;
import les12015.dominio.Unidade;

public class ValidaTroca implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Troca) {
		
			Suplementos s = new Suplementos();
			SuplementoDAO supimpa = new SuplementoDAO();
			Troca troca = (Troca) entidade;
			if (troca.getStat() != null) {

				s = new Suplementos();
				supimpa = new SuplementoDAO();
				s.setId(troca.getIdSup());
				s.setSupPedido(true);
				s = (Suplementos) supimpa.consultar(s).get(0);
				s.setRating((s.getRating() + troca.getNota())/2);
				try {
					supimpa.alterar(s);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Unidade u = new Unidade();
				UnidadePedidoDao uniDao = new UnidadePedidoDao();
				u.setId(troca.getIdUnidade());
				u.setStat("AVALIADO");				
				try {
					uniDao.alterar(u);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			else {
				Cliente c = new Cliente();
				ClienteDAO cDAO = new ClienteDAO();
				Troca t = new Troca();
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
		}
		return null;
	}

}
