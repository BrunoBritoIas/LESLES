package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Pedido;
import les12015.dominio.Suplementos;
import les12015.dominio.Unidade;

public class CarrinhoViewHelper implements IViewHelper {
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
		String stringId = Integer.toString(cliente.getIdCliente());
		Suplementos sup = (Suplementos) request.getSession().getAttribute("suplemento");
		Map<Integer, Pedido> carrinho = (Map<Integer, Pedido>) request.getSession().getAttribute("carrinho");

		if (stringId == null) {
			request.getSession().setAttribute("userid", "0");
			stringId = "0";

		}

		if (operacao.equals("addCarrinho")) {
			Unidade u = new Unidade();
			carrinho = new HashMap<Integer, Pedido>();
			Pedido p = new Pedido();
			p.setUnidade(new ArrayList<Unidade>());
			u.setSup(sup);
			u.setQuantidade(1);
			u.setPreco(sup.getPreco());
			p.getUnidade().add(u);
			int idUsuario = Integer.parseInt(stringId);
			carrinho.put(idUsuario, p);

			return u;
		}

		/*
		 * if (operacao.equals("MUDAR")) { System.out.println("Entrei no Mudar"); String
		 * txtIdUsuario = (String) request.getSession().getAttribute("userid"); int
		 * idUsuario = Integer.parseInt(txtIdUsuario); String txtIdSuplementos =
		 * request.getParameter("id"); int idSuplementos =
		 * Integer.parseInt(txtIdSuplementos); Pedido p = mapaUsuarios.get(idUsuario);
		 * Suplementos book; Unidade u = new Unidade(); for (int i = 0; i <
		 * p.getUnidade().size(); i++) { if (p.getUnidade().get(i).getSup().getId() ==
		 * idSuplementos) { book = p.getUnidade().get(i).getSup(); u =
		 * p.getUnidade().get(i);
		 * 
		 * } }
		 * 
		 * return u; }
		 * 
		 * if (mapaUsuarios != null) { Unidade u = new Unidade(); u.setQuantidade(1);
		 * u.setSup(l); return u; }
		 */

		return new Unidade();
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;

		String operacao = request.getParameter("operacao");

		if (operacao.equals("addCarrinho")) {
			Map<Integer, Pedido> carrinho = new HashMap<Integer, Pedido>();
			Unidade u = new Unidade();
			Suplementos sup = (Suplementos) resultado.getEntidades().get(0);
			Pedido p = new Pedido();
			Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
			String stringId = Integer.toString(cliente.getIdCliente());
			u.setSup(sup);
			u.setQuantidade(1);
			u.setPreco(sup.getPreco());
			p.setUnidade(new ArrayList<Unidade>());
			p.getUnidade().add(u);
			int idUsuario = Integer.parseInt(stringId);
			carrinho.put(idUsuario, p);
			request.getSession().setAttribute("carrinho", carrinho);
			request.getSession().setAttribute("itenscarrinho", p.getUnidade());
			d = request.getRequestDispatcher("Carrinho.jsp");
		}

		d.forward(request, response);

	}

}
