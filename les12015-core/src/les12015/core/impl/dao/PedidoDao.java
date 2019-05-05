package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import les12015.dominio.Pedido;
import les12015.dominio.CartaoPedido;
import les12015.dominio.Endereco;
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
					"INSERT INTO Pedido(dt_pedido, stats, endereco, idUsuario, precoFinal, frete , precoTotal, ciqtdItens) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, ped.getDtPedido());
			pst.setString(2, ped.getStatus());
			pst.setString(3, ped.getEndereco().getCidade() + ", " + ped.getEndereco().getLogradouro() + ", "
					+ ped.getEndereco().getNumero());
			pst.setInt(4, ped.getIDusuario());
			pst.setDouble(5, ped.getPrecoFinal());
			pst.setDouble(6, ped.getPrecoFrete());
			pst.setDouble(7, ped.getPrecoTotal());
			pst.setDouble(8, ped.getQtdItens());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;

			if (rs.next()) {
				id = rs.getInt(1);
			}
			ped.setId(id);
			connection.commit();

			sql = new StringBuilder();

			sql.append(
					"INSERT INTO CartaoPedido(id_pedido ,numCartao, validade, bandeira, numParcela, vlrParcela, totalParcela) ");
			sql.append("VALUES (?,?,?,?,?,?,?)");
			for (int i = 0; i < ped.getCardPed().size(); i++) {
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, ped.getId());
				pst.setString(2, ped.getCardPed().get(i).getNumCartao());
				pst.setString(3, ped.getCardPed().get(i).getValidade());
				pst.setString(4, ped.getCardPed().get(i).getBandeira());
				pst.setDouble(5, ped.getCardPed().get(i).getNumParcela());
				pst.setDouble(6, ped.getCardPed().get(i).getVlrParcela());
				pst.setDouble(7, ped.getCardPed().get(i).getTotalParcela());
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				connection.commit();
			}
		}

		catch (SQLException e) {
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
		PreparedStatement pst = null;
		Pedido ped = (Pedido) entidade;
		String sql = null;
		String sq = null;
		

		sql = "SELECT * FROM Pedido WHERE idUsuario =" + ped.getId();
		List<EntidadeDominio> pedido = new ArrayList<EntidadeDominio>();
		ArrayList<CartaoPedido> cartaoPedido = new ArrayList<CartaoPedido>();
		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Pedido pi = new Pedido();
				cartaoPedido = new ArrayList<CartaoPedido>();
				pi.setId(rs.getInt("ID_Pedido"));
				pi.setDtPedido(rs.getString("dt_pedido"));
				pi.setPrecoFinal(rs.getDouble("precoFinal"));
				pi.setPrecoFrete(rs.getDouble("frete"));
				pi.setPrecoTotal(rs.getDouble("precoTotal"));
				pi.setQtdItens(rs.getDouble("ciqtdItens"));
				
				sq = "SELECT * FROM CartaoPedido WHERE id_pedido =" + pi.getId();
				pst = null;
				pst = connection.prepareStatement(sq);
				ResultSet rss = pst.executeQuery();
				
				while (rss.next()) {
					CartaoPedido pe = new CartaoPedido();
					pe.setBandeira(rss.getString("bandeira"));
					pe.setNumCartao(rss.getString("numCartao"));
					pe.setNumParcela(rss.getDouble("numParcela"));
					pe.setTotalParcela(rss.getDouble("totalParcela"));
					pe.setVlrParcela(rss.getDouble("vlrParcela"));
					cartaoPedido.add(pe);
				}
				pi.setCardPed(cartaoPedido);
				pedido.add(pi);
			}
			return pedido;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
