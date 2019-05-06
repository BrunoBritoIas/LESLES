
package les12015.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;
import les12015.dominio.Unidade;

public class SuplementoDAO extends AbstractJdbcDAO {
	public SuplementoDAO() {
		super("Suplemento", "Id_suplemento");
	}

	public SuplementoDAO(Connection connection, String table, String idTable) {
		super(connection, table, idTable);

	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Suplementos suplemento = (Suplementos) entidade;

		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO Suplemento(nome, marca, categoria, descricao, peso, preco, proteina, carboidratos, gordura, calorias, rating, stats, validade,quantidade)");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, suplemento.getNome());
			pst.setString(2, suplemento.getMarca());
			pst.setString(3, suplemento.getCategoria());
			pst.setString(4, suplemento.getDescricao());
			pst.setDouble(5, suplemento.getPeso());
			pst.setDouble(6, suplemento.getPreco());
			pst.setDouble(7, suplemento.getProteina());
			pst.setDouble(8, suplemento.getCarboidratos());
			pst.setDouble(9, suplemento.getGordura());
			pst.setDouble(10, suplemento.getCalorias());
			pst.setDouble(11, suplemento.getRating());
			pst.setString(12, suplemento.getStatus());
			pst.setString(13, suplemento.getValidade());
			pst.setInt(14, suplemento.getQuantidade());
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
		openConnection();
		PreparedStatement pst = null;
		Suplementos suplemento = (Suplementos) entidade;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE Suplemento SET nome=?, marca=?, categoria=?, descricao=?, peso=?, preco=?, proteina=?, carboidratos=?, gordura=?, calorias=?, stats=?,validade=?"
							+ " WHERE Id_suplemento=?");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, suplemento.getNome());
			pst.setString(2, suplemento.getMarca());
			pst.setString(3, suplemento.getCategoria());
			pst.setString(4, suplemento.getDescricao());
			pst.setDouble(5, suplemento.getPeso());
			pst.setDouble(6, suplemento.getPreco());
			pst.setDouble(7, suplemento.getProteina());
			pst.setDouble(8, suplemento.getCarboidratos());
			pst.setDouble(9, suplemento.getGordura());
			pst.setDouble(10, suplemento.getCalorias());
			pst.setString(11, suplemento.getStatus());
			pst.setString(12, suplemento.getValidade());
			pst.setDouble(13, suplemento.getId());
			pst.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		Suplementos sup;
		if (Unidade.class.getName().equals(entidade.getClass().getName())) {
			sup = ((Unidade) entidade).getSup();
		} else {
			sup = (Suplementos) entidade;
		}
		String sql = null;
		
		if(sup.isSupPedido()) {
			sql = "SELECT * FROM Suplemento WHERE Id_suplemento="+ sup.getId();
		}
		else
		{
			sql = "SELECT * FROM Suplemento WHERE 1=1";
		}
		
		if (sup.getId() != null && sup.getId() > 0) {
			sql += " and Id_suplemento = " + sup.getId();

		}

		if (sup.getNome() != null && sup.getNome().trim() != "") {
			sql += " and nome = '" + sup.getNome() + "'";
		}

		if (sup.getMarca() != null && sup.getMarca().trim() != "") {
			sql += " and marca = '" + sup.getMarca() + "'";
		}

		if (sup.getPeso() != null && sup.getPeso() > 0) {
			sql += " and peso = " + sup.getPeso();

		}

		if (sup.getCategoria() != null && sup.getCategoria().trim() != "") {
			sql += " and categoria = '" + sup.getCategoria() + "'";
		}

		if (sup.getRating() != null && sup.getRating() > 0) {
			sql += " and rating = " + sup.getRating();

		}
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> suplementos = new ArrayList<EntidadeDominio>();
			if (Unidade.class.getName().equals(entidade.getClass().getName())) {
				Unidade uni = (Unidade) entidade;
				while (rs.next()) {
					Unidade u = new Unidade();
					Suplementos s = new Suplementos();

					s.setId(rs.getInt("Id_suplemento"));
					s.setNome(rs.getString("nome"));
					s.setMarca(rs.getString("marca"));
					s.setCategoria(rs.getString("categoria"));
					s.setDescricao(rs.getString("descricao"));
					s.setRating(rs.getDouble("rating"));
					s.setPeso(rs.getDouble("peso"));
					s.setGordura(rs.getDouble("gordura"));
					s.setPreco(rs.getDouble("preco"));
					s.setCarboidratos(rs.getDouble("carboidratos"));
					s.setProteina(rs.getDouble("proteina"));
					s.setCalorias(rs.getDouble("calorias"));
					s.setStatus(rs.getString("stats"));
					s.setValidade(rs.getString("validade"));
					u.setSup(s);
					u.setQuantidade(uni.getQuantidade());
					suplementos.add(u);
				}
			} else {
				while (rs.next()) {
					Suplementos s = new Suplementos();
					s.setId(rs.getInt("Id_suplemento"));
					s.setNome(rs.getString("nome"));
					s.setMarca(rs.getString("marca"));
					s.setCategoria(rs.getString("categoria"));
					s.setDescricao(rs.getString("descricao"));
					s.setRating(rs.getDouble("rating"));
					s.setPeso(rs.getDouble("peso"));
					s.setGordura(rs.getDouble("gordura"));
					s.setPreco(rs.getDouble("preco"));
					s.setCarboidratos(rs.getDouble("carboidratos"));
					s.setProteina(rs.getDouble("proteina"));
					s.setCalorias(rs.getDouble("calorias"));
					s.setStatus(rs.getString("stats"));
					s.setValidade(rs.getString("validade"));
					s.setQuantidade(rs.getInt("quantidade"));
					suplementos.add(s);
				}
			}
			return suplementos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
