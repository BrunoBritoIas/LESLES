package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;
import les12015.dominio.Unidade;

public class UnidadePedidoDao extends AbstractJdbcDAO {

	public UnidadePedidoDao() {
		super("UniPedido", "ID_UnidadePedido");
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
		PreparedStatement pst = null;
		Unidade unidade = (Unidade) entidade;

		String sql = null;
		
		if (unidade.getStat().equals("graficos")) {
			sql = "SELECT * FROM UnidadePedido WHERE categoria = '" + unidade.getCategoria() + "'";
			sql += " and DAY(dt_pedido) = " + unidade.getDtPedido();
		}
		else {
		sql = "SELECT * FROM UnidadePedido WHERE categoria = '" + unidade.getCategoria() + "'";
		}
		//select * from UnidadePedido where  DAY(dt_pedido) = 12;
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> listaUnidades = new ArrayList<EntidadeDominio>();
			Suplementos s = new Suplementos();
			while (rs.next()) {
				SuplementoDAO supimpa = new SuplementoDAO();
				Unidade uni = new Unidade();
				s = new Suplementos();
				uni.setQuantidade(rs.getInt("quantidade"));
				uni.setPreco(rs.getDouble("preço"));
				uni.setIdSup(rs.getInt("id_sup"));
				uni.setId(rs.getInt("ID_UnidadePedido"));
				uni.setDtPedido(rs.getString("dt_pedido"));
				uni.setCategoria(rs.getString("categoria"));
				s.setId(rs.getInt("id_sup"));
				s.setSupPedido(true);
				s = (Suplementos) supimpa.consultar(s).get(0);
				uni.setSup(s);
				listaUnidades.add(uni);
			}

			return listaUnidades;
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
		return null;
	}
}
