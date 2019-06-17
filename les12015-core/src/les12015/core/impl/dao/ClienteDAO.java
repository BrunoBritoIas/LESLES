
package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;

public class ClienteDAO extends AbstractJdbcDAO {
	public ClienteDAO() {
		super("Cliente", "Id_cliente");
	}

	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO Cliente(nome, genero, senha, cpf, email, ");
			sql.append(
					"dt_cadastro, dt_Nasc,telefone,tipo_User,cli_status,saldo) VALUES (?,?,?,?,?,sysdate(),?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getGenero());
			pst.setString(3, cliente.getSenha());
			pst.setString(4, cliente.getCpf());
			pst.setString(5, cliente.getEmail());
			pst.setString(6, cliente.getDtNasc());
			pst.setString(7, cliente.getTelefone());
			pst.setString(8, cliente.getTipoUser());
			pst.setString(9, cliente.getStatus());
			pst.setDouble(10, cliente.getSaldo());
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

	/**
	 * TODO Descrição do Método
	 * 
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE Cliente SET nome=?, genero=?, senha=?, email=?, CPF=?, dt_Nasc=?, telefone=?, tipo_User=?, cli_status=?, saldo=?");
			sql.append(" WHERE Id_cliente=?");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getGenero());
			pst.setString(3, cliente.getSenha());
			pst.setString(4, cliente.getEmail());
			pst.setString(5, cliente.getCpf());
			pst.setString(6, cliente.getDtNasc());
			pst.setString(7, cliente.getTelefone());
			pst.setString(8, cliente.getTipoUser());
			pst.setString(9, cliente.getStatus());
			pst.setDouble(10, cliente.getSaldo());
			pst.setInt(11, cliente.getIdCliente());
			pst.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
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

	/**
	 * TODO Descrição do Método
	 * 
	 * @param entidade
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		PreparedStatement pst = null;

		Cliente cliente = (Cliente) entidade;
		String sql = null;

		sql = "SELECT * FROM Cliente WHERE 1=1";

		if (cliente.getId() != null && cliente.getId() > 0) {
			sql += " and Id_cliente = " + cliente.getId();

		}
		if (cliente.getNome() != null && cliente.getNome().trim() != "") {
			sql += " and nome = '" + cliente.getNome() + "'";
		}

		if (cliente.getCpf() != null && cliente.getCpf().trim() != "") {
			sql += " and cpf = '" + cliente.getCpf() + "'";
		}

		if (cliente.getEmail() != null && cliente.getEmail().trim() != "") {
			sql += " and email = '" + cliente.getEmail() + "'";
		}

		if (cliente.getGenero() != null && cliente.getGenero().trim() != "") {
			sql += " and genero = '" + cliente.getGenero() + "'";
		}

		if (cliente.getDtNasc() != null && cliente.getDtNasc().trim() != "") {
			sql += " and dt_Nasc = '" + cliente.getDtNasc() + "'";
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setIdCliente(rs.getInt("Id_cliente"));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("CPF"));
				c.setEmail(rs.getString("email"));
				c.setGenero(rs.getString("genero"));
				c.setDtNasc(rs.getString("dt_Nasc"));
				Calendar dtCad = Calendar.getInstance();
				dtCad.setTime(rs.getDate("dt_Cadastro"));
				c.setDtCadastro(dtCad);
				c.setSenha(rs.getString("senha"));
				c.setTelefone(rs.getString("telefone"));
				c.setSaldo(rs.getDouble("saldo"));
				clientes.add(c);
			}
			return clientes;
		} catch (Exception e) {
			// TODO: handle exception
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
