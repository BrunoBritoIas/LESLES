package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.EntidadeDominio;
import les12015.dominio.GraficosVendasCategoria;
import les12015.dominio.Pedido;
import les12015.dominio.Suplementos;
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
		GraficosVendasCategoria graficos = new GraficosVendasCategoria();
		List<EntidadeDominio> grafico = new ArrayList<EntidadeDominio>();
		List<EntidadeDominio> l = new ArrayList<EntidadeDominio>();
		Pedido p = new Pedido();
		List<Pedido> pedido = new ArrayList<Pedido>();
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
				graficos = new GraficosVendasCategoria();

				for (int i = 0; i < pedido.size(); i++) {

					for (int n = 1; n <= 12; n++) {

						if (pedido.get(i).getDtPedido().substring(3, 5).equals(String.valueOf(n))) {
							int qtdCategoria = 0;
							for (int y = 0; y < pedido.get(i).getUnidade().size(); i++) {

								if (pedido.get(i).getUnidade().get(y).getSup().getCategoria().equals(categorias.get(c).toString())) {
									qtdCategoria = qtdCategoria + 1;
								}
								graficos.getQtdMes().add(n - 1, qtdCategoria);
							}

						}

					}

				}
				graficos.setCategoria(categorias.get(c).toString());
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
