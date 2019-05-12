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

		
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		Map<Integer, Pedido> carrinho = (Map<Integer, Pedido>) request.getSession().getAttribute("carrinho");
		String operacao = request.getParameter("operacao");
		Double ajustePreco = 0.0;
		Double vFinal = 0.0;
		Double qtdFinal = 0.0;
		Double pesoFinal = 0.0;
		if (operacao.equals("addCarrinho")) {

			Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
			String txtId = Integer.toString(cliente.getIdCliente());
			Integer id = Integer.parseInt(txtId);
			Pedido p = (Pedido) request.getSession().getAttribute("pedido");
			if (p == null) {
				p = new Pedido();
			}
			if (carrinho == null) {
				carrinho = new HashMap<Integer, Pedido>();
			}


			if (carrinho.containsKey(id)) {
				List<EntidadeDominio> e = resultado.getEntidades();

				Unidade unidade = (Unidade) e.get(0);
				
				List<Integer> listaIds = new ArrayList<Integer>();
				int indice = 0;
				if (p.getUnidade().size() == 0) {
					p.setUnidade(new ArrayList<Unidade>());
					p.getUnidade().add(unidade);
				} else {
					for (int i = 0; i < p.getUnidade().size(); i++) {
						if (unidade.getSup().getId() == p.getUnidade().get(i).getSup().getId())
							indice = i;
						vFinal = p.getUnidade().get(i).getPreco() + vFinal;
						listaIds.add(p.getUnidade().get(i).getSup().getId());
					}
					p.setPrecoTotal(vFinal);
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
				p.setPrecoTotal(unidade.getSup().getPreco());
				p.setPrecoFinal(unidade.getSup().getPreco());
				p.setPrecoFrete(unidade.getSup().getPeso() / 5);

				if (carrinho.size() == 0 || !carrinho.containsKey(id)) {
					carrinho.put(id, p);
				} else {
					carrinho.replace(id, p);
				}
			}
			request.getSession().setAttribute("carrinho", carrinho);
			request.getSession().setAttribute("itens", p.getUnidade());
			request.getSession().setAttribute("resultadoSuplementos", resultado);
			request.getSession().setAttribute("pedido", p);
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
					ajustePreco = p.getPrecoTotal() - p.getUnidade().get(i).getPreco();
					p.setPrecoTotal(ajustePreco);
					p.getUnidade().remove(i);

					break;
				}
			}
			request.getSession().setAttribute("carrinho", carrinho);
			request.getSession().setAttribute("itens", p.getUnidade());
			request.getSession().setAttribute("pedido", p);
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
			for (int i = 0; i < p.getUnidade().size(); i++) {
				Suplementos s = p.getUnidade().get(i).getSup();
				if (s.getId() == IdCarrinho) {

					if (quantidade < 0) {
						p.getUnidade().get(i).setQuantidade(0);
						p.setPrecoFrete(0.0);
						p.setPrecoTotal(0.0);
						p.setQtdItens(0.0);

					} else {
						p.getUnidade().get(i).setQuantidade(quantidade);

						p.getUnidade().get(i).setPreco(quantidade * p.getUnidade().get(i).getSup().getPreco());
					}

				}
				vFinal = p.getUnidade().get(i).getPreco() + vFinal;
				qtdFinal = p.getUnidade().get(i).getQuantidade() + qtdFinal;
				pesoFinal = p.getUnidade().get(i).getSup().getPeso() + pesoFinal;

			}
			if (qtdFinal <= 0) {
				p.setPrecoFrete(0.0);
				p.setPrecoTotal(0.0);
				p.setQtdItens(0.0);
			} else {
				p.setPrecoFrete((pesoFinal + qtdFinal) / 10);
				p.setPrecoTotal(vFinal);
				p.setQtdItens(qtdFinal);
			}
			
			if (cupom != null) {
				p.setPrecoFinal(p.getPrecoTotal() + p.getPrecoFrete() - cupom.getDesconto());

			} else {
				p.setPrecoFinal(p.getPrecoTotal() + p.getPrecoFrete());
			}

			request.getSession().setAttribute("itens", p.getUnidade());
			request.getSession().setAttribute("pedido", p);
			carrinho.replace(txtId, p);

			d = request.getRequestDispatcher("Carrinho.jsp");

		}

		d.forward(request, response);

	}

}