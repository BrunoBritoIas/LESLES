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

public class PedidoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		double nParcela = 0.0;
		double vParcela = 0.0;
		double totalParcela = 0.0;
		String numCartao = "";
		String bandeira = "";
		String validade = "";
		Integer numCards = Integer.parseInt(request.getParameter("numCards"));
		Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
		Pedido ped = (Pedido) request.getSession().getAttribute("pedido");
		Pedido pedido = new Pedido();
		LocalDateTime ldt = LocalDateTime.now().plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY", Locale.ENGLISH);
		String data = formatter.format(ldt);
		ArrayList<CartaoPedido> p = new ArrayList<CartaoPedido>();
		if (operacao.equals("FINALIZAR")) {
			pedido.setDtPedido(data);
			pedido.setEndereco(cliente.getEndereco().get(0));
			pedido.setIDusuario(cliente.getIdCliente());
			pedido.setUnidade(ped.getUnidade());
			pedido.setStatus("Aguardando Aprova��o");
			pedido.setPrecoFinal(ped.getPrecoFinal());
			pedido.setPrecoFrete(ped.getPrecoFrete());
			pedido.setPrecoTotal(ped.getPrecoTotal());
			pedido.setQtdItens(ped.getQtdItens());
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
				while(request.getParameter(numParcela) == "" ||request.getParameter(cardValue) == "" ||request.getParameter(cardParcela)==
					""||request.getParameter(numero) == ""||request.getParameter(nvalidade) == "") {
					y++;
					numParcela = "numParcela" + y;
					cardValue = "cardValue" + y;
					cardParcela = "cardParcela" + y;
					numero = "numero" + y;
					nvalidade = "validade" + y;
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
				p.add(cardPed);
				pedido.setCardPed(p);
				y++;
			}

			return pedido;
		}

		return null;
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
			ArrayList<Pedido> pedido = (ArrayList<Pedido>) sessao.getAttribute("listaPedidos");
			cli.setPedido(pedido);
			request.getSession().setAttribute("usuario", cli);
			d = request.getRequestDispatcher("FinalPedido.jsp");
		}
		d.forward(request, response);
	}

}
