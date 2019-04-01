package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;

public class EnderecoDAO extends AbstractJdbcDAO {

	public EnderecoDAO() {
		super("Nome", " ID");

	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO Endereco(tipo_Residencia, tipo_Logradouro, logradouro, numero, bairro, CEP,cidade,estado, pais, obs, pref, stats, fk_cliente) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, endereco.getTipo_res());
			pst.setString(2, endereco.getTipo_log());
			pst.setString(3, endereco.getLogradouro());
			pst.setString(4, endereco.getNumero());
			pst.setString(5, endereco.getBairro());
			pst.setString(6, endereco.getCep());
			pst.setString(7, endereco.getCidade());
			pst.setString(8, endereco.getEstado());
			pst.setString(9, endereco.getPais());
			pst.setString(10, endereco.getObs());
			pst.setString(11, endereco.getPref());
			pst.setString(12, endereco.getStatus());
			pst.setInt(13, endereco.getCli_id());
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
		Endereco endereco = (Endereco) entidade;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE Endereco SET  tipo_Residencia=?, tipo_Logradouro=?, logradouro=?, numero=?, bairro=?, CEP=?, cidade=?, estado=?, obs=?"
							+ " WHERE ID_Endereco=?");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, endereco.getTipo_res());
			pst.setString(2, endereco.getTipo_log());
			pst.setString(3, endereco.getLogradouro());
			pst.setString(4, endereco.getNumero());
			pst.setString(5, endereco.getBairro());
			pst.setString(6, endereco.getCep());
			pst.setString(7, endereco.getCidade());
			pst.setString(8, endereco.getEstado());
			pst.setString(9, endereco.getObs());
			pst.setInt(10, endereco.getId());
			pst.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
		Endereco end = (Endereco) entidade;
		String sql = null;

		sql = "SELECT * FROM Endereco WHERE fk_cliente =" + end.getId() + " and stats = 'ATIVO'";

		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> endereco = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Endereco s = new Endereco();
				s.setId(rs.getInt("ID_Endereco"));
				s.setBairro(rs.getString("bairro"));
				s.setCep(rs.getString("CEP"));
				s.setCidade(rs.getString("cidade"));
				s.setEstado(rs.getString("estado"));
				s.setLogradouro(rs.getString("logradouro"));
				s.setNumero(rs.getString("numero"));
				s.setObs(rs.getString("obs"));
				s.setPais(rs.getString("pais"));
				s.setTipo_res(rs.getString("tipo_Residencia"));
				s.setTipo_log(rs.getString("tipo_Logradouro"));
				endereco.add(s);
			}
			return endereco;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
