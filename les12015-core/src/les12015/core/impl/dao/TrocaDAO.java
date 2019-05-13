package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;
import les12015.dominio.Troca;
import les12015.dominio.Unidade;

public class TrocaDAO extends AbstractJdbcDAO {

	public TrocaDAO() {
		super("Nome", " ID");

	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {

		PreparedStatement pst = null;
		openConnection();
		Troca troca = (Troca) entidade;
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO Troca (stats, ciqtdItens, credito, idUnidade, idUsuario )" + "    VALUES (?, ?, ?, ?, ?)");
		try {
			pst = connection.prepareStatement(sql.toString());
			connection.setAutoCommit(false);
			pst.setString(1, "Troca Unitaria");
			pst.setInt(2, troca.getQtdItens());
			pst.setDouble(3, troca.getQtdCredito());
			pst.setInt(4, troca.getIdUnidade());
			pst.setInt(5, troca.getIdUser());
			pst.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			connection.rollback();
			ex.printStackTrace();
		} finally {
			connection.close();
		}

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {

		PreparedStatement pst = null;
		openConnection();
		Troca troca = (Troca) entidade;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Troca SET stats=?  WHERE ID_Troca=" + troca.getId());
		try {
			pst = connection.prepareStatement(sql.toString());
			connection.setAutoCommit(false);
			pst.setString(1, troca.getStatus());
			pst.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			connection.rollback();
			ex.printStackTrace();
		} finally {
			connection.close();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

		PreparedStatement pst = null;
		if (entidade instanceof Troca) {

			String sql = null;

			sql = "SELECT * FROM Troca";
			try {
				openConnection();
				pst = connection.prepareStatement(sql.toString());
				ResultSet rs = pst.executeQuery();
				List<EntidadeDominio> trocas = new ArrayList<EntidadeDominio>();

				while (rs.next()) {
					Suplementos s = new Suplementos();
					Cliente c = new Cliente();
					SuplementoDAO pDAO = new SuplementoDAO();
					ClienteDAO cDAO = new ClienteDAO();
					Troca t = new Troca();
					t.setStatus(rs.getString("stats"));
					t.setQtdCredito(rs.getDouble("credito"));
					t.setQtdItens(rs.getInt("ciqtdItens"));
					t.setId(rs.getInt("ID_Troca"));
					t.setIdUser(rs.getInt("idUsuario"));
					t.setIdUnidade(rs.getInt("idUnidade"));
					c.setId(t.getIdUser());
					s.setId(t.getIdUnidade());
					c = (Cliente) cDAO.consultar(c).get(0);
					s = (Suplementos) pDAO.consultar(s).get(0);
					t.setProduto(s);
					t.setUser(c);
					trocas.add(t);
				}
				return trocas;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			Unidade unidade = (Unidade) entidade;

			String sql = null;

			sql = "SELECT * FROM Troca WHERE idUnidade =" + unidade.getId();
			try {
				openConnection();
				pst = connection.prepareStatement(sql.toString());
				ResultSet rs = pst.executeQuery();
				List<EntidadeDominio> trocas = new ArrayList<EntidadeDominio>();

				while (rs.next()) {
					Troca t = new Troca();
					t.setStatus(rs.getString("stats"));
					t.setQtdCredito(rs.getDouble("credito"));
					t.setQtdItens(rs.getInt("ciqtdItens"));
					t.setId(rs.getInt("ID_Troca"));
					t.setId(rs.getInt("idUsuario"));
					trocas.add(t);
				}
				return trocas;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
