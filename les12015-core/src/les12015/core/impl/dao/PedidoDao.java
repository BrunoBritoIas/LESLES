package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import les12015.dominio.Pedido;
import les12015.dominio.EntidadeDominio;

public class PedidoDao extends AbstractJdbcDAO {

	public PedidoDao() {
		super("Nome", " ID");

	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Pedido ped = (Pedido) entidade;
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO Pedido(tipo_Residencia, tipo_Logradouro, logradouro, numero, bairro, CEP,cidade,estado, pais, obs, pref, stats, fk_cliente) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, ped.getDtPedido());
			pst.setString(2, ped.getStatus());
			pst.setString(3, ped.getEndereco().getCidade() +", "+ ped.getEndereco().getLogradouro() +", " + ped.getEndereco().getNumero());
			pst.setInt(4, ped.getIDusuario());
			pst.setDouble(5, ped.getPrecoFinal());
			pst.setDouble(6, ped.getPrecoFrete());
			pst.setDouble(7, ped.getPrecoTotal());
			pst.setDouble(8, ped.getQtdItens());		
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
