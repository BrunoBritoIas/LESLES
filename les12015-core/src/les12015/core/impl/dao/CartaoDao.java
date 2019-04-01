package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Cartao;
import les12015.dominio.EntidadeDominio;

public class CartaoDao extends AbstractJdbcDAO {

	public CartaoDao() {
		super("Nome", " ID");

	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Cartao cartao = (Cartao) entidade;
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO Cartao(numero, titular, bandeira, dtVal, cod_seg, stats,pref, fk_cliente) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cartao.getNumero());
			pst.setString(2, cartao.getTitular());
			pst.setString(3, cartao.getBandeira());
			pst.setString(4, cartao.getValidade());
			pst.setString(5, cartao.getCodigo());
			pst.setString(6, cartao.getStatus());
			pst.setString(7, cartao.getPreferencial());
			pst.setInt(8, cartao.getID_Cliente());
			pst.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Cartao cartao = (Cartao) entidade;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE Cartao SET  stats=?" + " WHERE ID_Cartao=?");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cartao.getStatus());
			pst.setInt(2, cartao.getId());
			pst.executeUpdate();
			connection.commit();
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
		Cartao car = (Cartao) entidade;
		String sql = null;

		sql = "SELECT * FROM Cartao WHERE fk_cliente =" + car.getId() + " and stats = 'ATIVO'";

		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> cartao = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Cartao c = new Cartao();
				c.setId(rs.getInt("ID_Cartao"));
				//c.setCodigo(rs.getString("codigo"));
				c.setNumero(rs.getString("numero"));
				c.setTitular(rs.getString("titular"));
				c.setValidade(rs.getString("dtVal"));
				c.setBandeira(rs.getString("bandeira"));
				c.setPreferencial(rs.getString("pref"));
				cartao.add(c);
			}
			return cartao;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}