package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.EntidadeDominio;
import les12015.dominio.GraficosVendasCategoria;
import les12015.dominio.Pedido;
import les12015.dominio.Unidade;

public class GraficosDAO extends AbstractJdbcDAO {

	public GraficosDAO() {
		super("Nome", " ID");

	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		
		GraficosVendasCategoria graf = (GraficosVendasCategoria) entidade;
		
		GraficosVendasCategoria graficos = new GraficosVendasCategoria();
		List<EntidadeDominio> grafico = new ArrayList<EntidadeDominio>();
		List<EntidadeDominio> l = new ArrayList<EntidadeDominio>();
		Pedido p = new Pedido();
		List<Pedido> pedido = new ArrayList<Pedido>();
		List<Unidade> unis = new ArrayList<Unidade>();
		List<EntidadeDominio> unidade = new ArrayList<EntidadeDominio>();
		List<Pedido> pa = new ArrayList<Pedido>();
		PedidoDao pDAO = new PedidoDao();
		p.setConsultaPedidos(true);
		PreparedStatement pst = null;
		String sql = null;
		sql = "SELECT * FROM categoria";

		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<String> categorias = new ArrayList<String>();

			while (rs.next()) {
				String c = null;
				c = rs.getString("categoria");
				categorias.add(c);
			}

			l = (List<EntidadeDominio>) pDAO.consultar(p);
			for (int i = 0; i < l.size(); i++) {
				pedido.add((Pedido) l.get(i));
			}

			for (int c = 0; c < categorias.size(); c++) {
				categorias.get(c).toString();
				graficos = new GraficosVendasCategoria();
				
				if (graf.getMes1() > graf.getMes2()) {
					int aux =  graf.getMes1();
					graf.setMes1(graf.getMes2());
					graf.setMes2(aux);	
				}
				if(graf.getMes1() > 1) {
					for (int i = 0; i < graf.getMes1(); i++) {
						graficos.getQtdMes().add(i, 0);
					}
					
				}
				for (int i = graf.getMes1(); i <= graf.getMes2(); i++) {
					Unidade uni = new Unidade();
					UnidadePedidoDao uniDao = new UnidadePedidoDao();
					unis = new ArrayList<Unidade>();
					unidade = new ArrayList<EntidadeDominio>();
					uni.setCategoria(categorias.get(c).toString());
					uni.setStat("graficos");
					uni.setDtPedido(String.valueOf(i));
					uni.setAno(graf.getAno());
					unidade = uniDao.consultar(uni);
					if(unidade.size() == 0 || unidade == null){
						graficos.getQtdMes().add(i-1, 0);
					}
					for (int j = 0; j < unidade.size(); j++) {
						unis.add((Unidade) unidade.get(j));					
					}
					int qtd = 0;
					for (int k = 0; k < unis.size(); k++) {
						if(unis.size() > 1) {
							qtd = qtd + unis.get(k).getQuantidade();					
						}
						else {
							graficos.getQtdMes().add(i-1, unis.get(k).getQuantidade());
						}
					}
					if(unis.size() > 1) {
						graficos.getQtdMes().add(i-1, qtd);
					}
					
					
				}
				if(graf.getMes2() < 12) {
					for (int i = graficos.getQtdMes().size(); i <= 12; i++) {
						graficos.getQtdMes().add(i, 0);
					}
				}
				graficos.setCategoria(categorias.get(c).toString());
				grafico.add(graficos);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return grafico;
	}

}
