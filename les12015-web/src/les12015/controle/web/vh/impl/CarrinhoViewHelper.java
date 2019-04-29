package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Cliente;
import les12015.dominio.Cupom;
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
			request.getSession().setAttribute("usuario", "0");
			stringId = "0";

		}

		if (carrinho == null) {
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

		{
			Unidade u = new Unidade();
			u.setQuantidade(1);
			u.setSup(sup);
			return u;
		}

		/*
		 * if (operacao.equals("MUDAR")) { System.out.println("Entrei no Mudar"); String
		 * txtIdUsuario = (String) request.getSession().getAttribute("userid"); int
		 * idUsuario = Integer.parseInt(txtIdUsuario); String txtIdSuplementos =
		 * request.getParameter("id"); int idSuplementos =
		 * Integer.parseInt(txtIdSuplementos); Pedido p = carrinho.get(idUsuario);
		 * Suplementos book; Unidade u = new Unidade(); for (int i = 0; i <
		 * p.getUnidade().size(); i++) { if (p.getUnidade().get(i).getSup().getId() ==
		 * idSuplementos) { book = p.getUnidade().get(i).getSup(); u =
		 * p.getUnidade().get(i);
		 * 
		 * } }
		 * 
		 * return u; }
		 * 
		 * if (carrinho != null) { Unidade u = new Unidade(); u.setQuantidade(1);
		 * u.setSup(l); return u; }
		 */
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		Map<Integer, Pedido> carrinho = (Map<Integer, Pedido>) request.getSession().getAttribute("carrinho");
		String operacao = request.getParameter("operacao");

		/*
		 * if (operacao.equals("addCarrinho")) { Map<Integer, Pedido> carrinho = new
		 * HashMap<Integer, Pedido>(); Unidade u = new Unidade(); Suplementos sup =
		 * (Suplementos) resultado.getEntidades().get(0); Pedido p = new Pedido();
		 * Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
		 * String stringId = Integer.toString(cliente.getIdCliente()); u.setSup(sup);
		 * u.setQuantidade(1); u.setPreco(sup.getPreco()); p.setUnidade(new
		 * ArrayList<Unidade>()); p.getUnidade().add(u); int idUsuario =
		 * Integer.parseInt(stringId); carrinho.put(idUsuario, p);
		 * 
		 * String nome = carrinho.get(1).getUnidade().get(0).getSup().getNome();
		 * 
		 * request.getSession().setAttribute("carrinho", carrinho);
		 * request.getSession().setAttribute("itenscarrinho", p.getUnidade()); d =
		 * request.getRequestDispatcher("Carrinho.jsp"); }
		 */

		if (operacao.equals("addCarrinho")) {

			Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
			String txtId = Integer.toString(cliente.getIdCliente());
			Integer id = Integer.parseInt(txtId);
			Pedido p = new Pedido();
			if (carrinho == null) {
				carrinho = new HashMap<Integer, Pedido>();
			}

			String msg1 = "Nao ha mais suplementos restantes no estoque";
			msg1.trim();

			if (carrinho.containsKey(id)) {
				List<EntidadeDominio> e = resultado.getEntidades();

				Unidade unidade = (Unidade) e.get(0);
				p = carrinho.get(id);
				List<Integer> listaIds = new ArrayList<Integer>();
				int indice = 0;
				if (p.getUnidade().size() == 0) {
					p.setUnidade(new ArrayList<Unidade>());
					p.getUnidade().add(unidade);
				} else {
					for (int i = 0; i < p.getUnidade().size(); i++) {
						if (unidade.getSup().getId() == p.getUnidade().get(i).getSup().getId())
							indice = i;

						listaIds.add(p.getUnidade().get(i).getSup().getId());
					}

					if (!listaIds.contains(unidade.getSup().getId()))
						p.getUnidade().add(unidade);
					else
						p.getUnidade().get(indice).setQuantidade(p.getUnidade().get(indice).getQuantidade() + 1);

					carrinho.replace(id, p);
				}
			} // if contains key

			if (!carrinho.containsKey(id)) {
				List<EntidadeDominio> e = resultado.getEntidades();

				Unidade unidade = (Unidade) e.get(0);
				p = carrinho.get(id);
				if (p == null) {
					p = new Pedido();
				}

				p.setUnidade(new ArrayList<Unidade>());
				p.getUnidade().add(unidade);

				if (carrinho.size() == 0 || !carrinho.containsKey(id)) {
					carrinho.put(id, p);
				} else {
					carrinho.replace(id, p);
				}
			} // if !containsKey
			request.getSession().setAttribute("carrinho", carrinho);
			request.setAttribute("itens", p.getUnidade());
			request.getSession().setAttribute("resultadoSuplementos", resultado);
			request.setAttribute("pedido", p);
			d = request.getRequestDispatcher("Carrinho.jsp");
		}

		if (operacao.equals("REMOVER")) {

			String txtIdCarrinho = (String) request.getParameter("id");
			Integer idSup = Integer.parseInt(txtIdCarrinho);
			Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
			Integer txtId = cliente.getIdCliente();
			Pedido p = carrinho.get(txtId);

			for (int i = 0; i < p.getUnidade().size(); i++) {
				Suplementos s = p.getUnidade().get(i).getSup();
				if (s.getId() == idSup) {
					p.getUnidade().remove(i);
					break;
				}
			}
			request.getSession().setAttribute("carrinho", carrinho);
			request.setAttribute("itens", p.getUnidade());
			carrinho.replace(txtId, p);

			d = request.getRequestDispatcher("Carrinho.jsp");

		}

		if (operacao.equals("QUANTIDADE")) {

			Integer IdCarrinho = Integer.parseInt(request.getParameter("id"));
			Integer quantidade = Integer.parseInt(request.getParameter("txtqtd"));
			Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
			Cupom cupom = (Cupom) request.getSession().getAttribute("cupom");
			Integer txtId = cliente.getIdCliente();
			Pedido p = carrinho.get(txtId);
			Double vFinal = 0.0;
			Double qtdFinal = 0.0;
			Double pesoFinal = 0.0;
			for (int i = 0; i < p.getUnidade().size(); i++) {
				Suplementos s = p.getUnidade().get(i).getSup();
				if (s.getId() == IdCarrinho) {
					p.getUnidade().get(i).setQuantidade(quantidade);

					p.getUnidade().get(i).setPreco(quantidade * p.getUnidade().get(i).getSup().getPreco());

				}
				vFinal = p.getUnidade().get(i).getPreco() + vFinal;
				qtdFinal = p.getUnidade().get(i).getQuantidade() + qtdFinal;
				pesoFinal = p.getUnidade().get(i).getSup().getPeso() + pesoFinal;

			}
			p.setPrecoFrete((pesoFinal + qtdFinal) / 10);
			p.setPrecoTotal(vFinal);
			p.setQtdItens(qtdFinal);
			if(cupom != null) {
				p.setPrecoFinal(p.getPrecoTotal() + p.getPrecoFrete() - cupom.getDesconto());

			}
			else {
				p.setPrecoFinal(p.getPrecoTotal() + p.getPrecoFrete());
			}
			
			
			request.setAttribute("itens", p.getUnidade());
			request.getSession().setAttribute("pedido", p);
			carrinho.replace(txtId, p);

			d = request.getRequestDispatcher("Carrinho.jsp");

		}

		d.forward(request, response);

	}

}
