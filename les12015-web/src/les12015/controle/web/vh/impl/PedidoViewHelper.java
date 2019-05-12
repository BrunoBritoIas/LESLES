package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.CartaoPedido;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Pedido;
import les12015.dominio.Troca;

public class PedidoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		double nParcela = 0.0;
		double vParcela = 0.0;
		double totalParcela = 0.0;
		String numCartao = "";
		String bandeira = "";
		String validade = "";
		Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
		Pedido ped = (Pedido) request.getSession().getAttribute("pedido");
		Pedido pedido = new Pedido();
		LocalDateTime ldt = LocalDateTime.now().plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY", Locale.ENGLISH);
		String data = formatter.format(ldt);
		ArrayList<CartaoPedido> p = new ArrayList<CartaoPedido>();
		if (operacao.equals("FINALIZAR")) {
			Integer idEndereco = request.getParameter("idEnd").equals("") ? 0
					: Integer.parseInt(request.getParameter("idEnd"));
			Integer numCards = Integer.parseInt(request.getParameter("numCards"));
			pedido.setDtPedido(data);
			pedido.setEndereco(cliente.getEndereco().get(0));
			pedido.setNomeUser(cliente.getNome());
			pedido.setCpfUser(cliente.getCpf());
			pedido.setIDusuario(cliente.getIdCliente());
			pedido.setUnidade(ped.getUnidade());
			pedido.setStatus("Aguardando Aprovação");
			pedido.setPrecoFinal(ped.getPrecoFinal());
			pedido.setPrecoFrete(ped.getPrecoFrete());
			pedido.setPrecoTotal(ped.getPrecoTotal());
			pedido.setQtdItens(ped.getQtdItens());
			pedido.setIdEnd(idEndereco);
			pedido.setId(1);
			int y = 1;
			String numParcela = "numParcela" + y;
			String cardValue = "cardValue" + y;
			String cardParcela = "cardParcela" + y;
			String numero = "numero" + y;
			String nbandeira = "bandeira" + y;
			String nvalidade = "validade" + y;

			for (int i = 0; i < numCards; i++) {
				CartaoPedido cardPed = new CartaoPedido();
				numParcela = "numParcela" + y;
				cardValue = "cardValue" + y;
				cardParcela = "cardParcela" + y;
				numero = "numero" + y;
				nvalidade = "validade" + y;
				nbandeira = "bandeira" + y;
				while (request.getParameter(numParcela) == null || request.getParameter(cardValue) == null
						|| request.getParameter(cardParcela) == null || request.getParameter(numero) == null
						|| request.getParameter(nvalidade) == null || request.getParameter(numParcela) == ""
						|| request.getParameter(cardValue) == "" || request.getParameter(cardParcela) == ""
						|| request.getParameter(numero) == "" || request.getParameter(nvalidade) == "") {
					y++;
					numParcela = "numParcela" + y;
					cardValue = "cardValue" + y;
					cardParcela = "cardParcela" + y;
					numero = "numero" + y;
					nvalidade = "validade" + y;
					nbandeira = "bandeira" + y;
				}

				nParcela = Double.parseDouble(request.getParameter(numParcela));
				vParcela = Double.parseDouble(request.getParameter(cardValue));
				totalParcela = Double.parseDouble(request.getParameter(cardParcela));
				numCartao = request.getParameter(numero);
				bandeira = request.getParameter(nbandeira);
				validade = request.getParameter(nvalidade);
				cardPed.setNumParcela(nParcela);
				cardPed.setVlrParcela(vParcela);
				cardPed.setTotalParcela(totalParcela);
				cardPed.setNumCartao(numCartao);
				cardPed.setBandeira(bandeira);
				cardPed.setValidade(validade);
				cardPed.setId(pedido.getId());
				p.add(cardPed);
				pedido.setCardPed(p);
				y++;
			}

		}
		if (operacao.equals("CONSULTAPEDIDO")) {
			pedido.setId(cliente.getId());
			pedido.setId(cliente.getIdCliente());

		}

		if (operacao.equals("PEDIDOADMIN")) {
			pedido.setConsultaPedidos(true);

		}
		if (operacao.equals("VERPEDIDO")) {
			Integer idPedido = Integer.parseInt(request.getParameter("idPedido"));
			pedido.setProdDetail(true);
			pedido.setId(idPedido);
		}

		if (operacao.equals("PEDIDOCANCEL")) {
			Pedido pedi = (Pedido) request.getSession().getAttribute("detalhePed");
			Integer idPedido = Integer.parseInt(request.getParameter("PedidoID"));
			pedido = pedi;
			pedido.setStatus("CANCELADO");
			pedido.setProdDetail(true);
			pedido.setId(idPedido);
		}

		if (operacao.equals("SPEDIDO")) {
			String status = request.getParameter("status");
			Integer idPedido = Integer.parseInt(request.getParameter("idPedido"));
			pedido.setStatus(status);
			pedido.setId(idPedido);
		}

		if (operacao.equals("UNICHANGE")) {
			ArrayList<Troca> troca = new ArrayList<Troca>();
			Troca t = new Troca();
			Pedido pedi = (Pedido) request.getSession().getAttribute("detalhePed");
			Integer qtdProd = Integer.parseInt(request.getParameter("qtdProd"));
			Integer idSup = Integer.parseInt(request.getParameter("idProd"));
			for (int i = 0; i < pedi.getUnidade().size(); i++) {
				if (idSup == pedi.getUnidade().get(i).getIdSup()) {

					t.setIdSup(idSup);
					t.setQtdCredito(qtdProd * pedi.getUnidade().get(i).getSup().getPreco());
					t.setStatus("Troca Ativa");
					t.setIdUnidade(pedi.getUnidade().get(i).getSup().getId());
					t.setQtdItens(qtdProd);
					t.setIdUser(pedi.getIDusuario());
					troca.add(t);
					pedi.getUnidade().get(i).setQuantidade(pedi.getUnidade().get(i).getQuantidade() - qtdProd);
					pedi.getUnidade().get(i).setTroca(troca);
				}
			}
			pedi.setStatus("Troca ATIVA");
			pedido = pedi;

		}

		if (operacao.equals("FULLTROCA")) {
			ArrayList<Troca> troca = new ArrayList<Troca>();
			Troca t = new Troca();
			Pedido pedi = (Pedido) request.getSession().getAttribute("detalhePed");
			for (int i = 0; i < pedi.getUnidade().size(); i++) {
				troca = new ArrayList<Troca>();
				t = new Troca();
				Integer qtdProd = pedi.getUnidade().get(i).getQuantidade();
				Integer idSup = pedi.getUnidade().get(i).getSup().getId();
				t.setIdSup(idSup);
				t.setQtdCredito(qtdProd * pedi.getUnidade().get(i).getSup().getPreco());
				t.setStatus("Troca Ativa");
				t.setIdUnidade(pedi.getUnidade().get(i).getSup().getId());
				t.setQtdItens(qtdProd);
				t.setIdUser(pedi.getIDusuario());
				troca.add(t);
				pedi.getUnidade().get(i).setQuantidade(pedi.getUnidade().get(i).getQuantidade() - qtdProd);
				pedi.getUnidade().get(i).setTroca(troca);
			}
			pedi.setStatus("Troca ATIVA");
			pedido = pedi;

		}

		return pedido;

	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		HttpSession sessao = request.getSession();
		Cliente cu = (Cliente) request.getSession().getAttribute("usuario");
		Cliente cli = cu;

		if (operacao.equals("FINALIZAR")) {
			sessao.setAttribute("listaPedidos", resultado.getEntidades());
			sessao.setAttribute("resultadoCompra", resultado.getMsg());
			ArrayList<Pedido> pedido = (ArrayList<Pedido>) sessao.getAttribute("listaPedidos");
			cli.setPedido(pedido);
			request.getSession().setAttribute("usuario", cli);
			if (resultado.getMsg() == null) {
				sessao.setAttribute("pedido", null);
				sessao.setAttribute("carrinho", null);
				d = request.getRequestDispatcher("FinalPedido.jsp");
			} else {
				d = request.getRequestDispatcher("Carrinho.jsp");
			}
		}

		if (operacao.equals("CONSULTAPEDIDO")) {
			sessao.setAttribute("listaPedidos", resultado.getEntidades());
			ArrayList<Pedido> pedido = (ArrayList<Pedido>) sessao.getAttribute("listaPedidos");
			// pedido.get(1).getCardPed().get(1).getNumCartao();
			cli.setPedido(pedido);
			request.getSession().setAttribute("usuario", cli);
			d = request.getRequestDispatcher("CliAdmin.jsp");
		}

		if (operacao.equals("VERPEDIDO")) {
			sessao.setAttribute("pedDetail", resultado.getEntidades());
			sessao.setAttribute("detalhePed", resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("DetalhePedido.jsp");

		}

		if (operacao.equals("PEDIDOCANCEL")) {
			Pedido p = (Pedido) resultado.getEntidades().get(0);
			if (p.getStatus().equals("ADMCANCELADO")) {
				d = request.getRequestDispatcher("CliAdmin.jsp");
			} else if (p.getStatus().equals("CANCELADO")) {
				sessao.setAttribute("detalhePed", resultado.getEntidades().get(0));
				d = request.getRequestDispatcher("DetalhePedido.jsp");
			}
		}

		if (operacao.equals("UNICHANGE")) {
			sessao.setAttribute("pedDetail", resultado.getEntidades());
			sessao.setAttribute("detalhePed", resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("DetalhePedido.jsp");

		}
		if (operacao.equals("FULLTROCA")) {
			sessao.setAttribute("pedDetail", resultado.getEntidades());
			sessao.setAttribute("detalhePed", resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("DetalhePedido.jsp");

		}

		if (operacao.equals("PEDIDOADMIN")) {

			sessao.setAttribute("todosPedidos", resultado.getEntidades());
			d = request.getRequestDispatcher("Admin.jsp");

		}

		if (operacao.equals("SPEDIDO")) {
			sessao.setAttribute("todosPedidos", resultado.getEntidades());
			d = request.getRequestDispatcher("Admin.jsp");
		}

		d.forward(request, response);
	}

}
