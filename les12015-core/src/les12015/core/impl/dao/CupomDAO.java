package les12015.core.impl.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Cupom;
import les12015.dominio.EntidadeDominio;

public class CupomDAO extends AbstractJdbcDAO {


	public CupomDAO() {
		super("desconto", "id_cupom");
	}
	// TODO Auto-generated constructor stub

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

		PreparedStatement pst = null;
		openConnection();
		Cupom cupom = (Cupom) entidade;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Cupom (num_cupom, valor, dtVal, tpCupom)" + "    VALUES (?, ?, ?, ?)");
		try {
			pst = connection.prepareStatement(sql.toString());
			connection.setAutoCommit(false);			
			pst.setString(1, cupom.getSerial());
			pst.setDouble(2, cupom.getDesconto());
			pst.setString(3, cupom.getDtVal());
			pst.setString(4, cupom.getTpCupom());
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
		Cupom cupom = (Cupom) entidade;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * from Cupom");
		sql.append(" WHERE 1=1 ");
		if (cupom.getId() != null && cupom.getId() > 0) {
			sql.append(" AND id_cupom = '" + cupom.getId() + "'");
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> cupons = new ArrayList<EntidadeDominio>();

			while (rs.next()) {
				Cupom c = new Cupom();
				c.setSerial(rs.getString("num_cupom"));
				c.setDesconto(rs.getDouble("valor"));
				c.setDtVal(rs.getString("dtVal"));
				c.setTpCupom(rs.getString("tpCupom"));
				c.setId(rs.getInt("id_cupom"));
				cupons.add(c);
			}
			return cupons;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
