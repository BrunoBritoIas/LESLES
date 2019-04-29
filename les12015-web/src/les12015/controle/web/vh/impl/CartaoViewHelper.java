package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Cartao;
import les12015.dominio.Cliente;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;

public class CartaoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Cliente cu = (Cliente) request.getSession().getAttribute("usuario");
		String operacao = request.getParameter("operacao");
		Cartao cartao = new Cartao();

		if (operacao.equals("SAVECARD")) {
			String titular = request.getParameter("txtTitular");
			String codigo = request.getParameter("txtCodigo");
			String numero = request.getParameter("txtNumero");
			String bandeira = request.getParameter("txtBandeira");
			String validade = request.getParameter("txtValidade");
			cartao = new Cartao();

			int id = cu.getIdCliente();
			cartao.setID_Cliente(id);
			cartao.setTitular(titular);
			cartao.setCodigo(codigo);
			cartao.setNumero(numero);
			cartao.setBandeira(bandeira);
			cartao.setValidade(validade);
			cartao.setStatus("ATIVO");
			cartao.setPreferencial("N");

		}

		if (operacao.equals("CONSULTACARD")||operacao.equals("CARDHOME")) {
			int id = cu.getIdCliente();
			cartao.setId(id);

		}

		if (operacao.equals("CARDEL")) {
			int CardId = Integer.parseInt(request.getParameter("txtIdnovo"));
			cartao.setId(CardId);
			cartao.setStatus("I");
		}

		return cartao;

	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		HttpSession sessao = request.getSession();
		String operacao = request.getParameter("operacao");
		Cliente cu = (Cliente) request.getSession().getAttribute("usuario");
		Cliente cli = cu;
		if (resultado.getMsg() == null) {
			if (operacao.equals("SAVECARD")) {
				resultado.setMsg("Endereço cadastrado com sucesso!");
				request.getSession().setAttribute("resultado", resultado);

				d = request.getRequestDispatcher("CliAdmin.jsp");
			}

		}

		if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {

			d = request.getRequestDispatcher("ConsultarEndereco.jsp");
		}

		if (resultado.getMsg() == null && operacao.equals("CONSULTACARD")) {
			sessao.setAttribute("listaCartoes", resultado.getEntidades());
			d = request.getRequestDispatcher("CliAdmin.jsp");
		}
		if (resultado.getMsg() == null && operacao.equals("CARDHOME")) {
			sessao.setAttribute("listaCartoes", resultado.getEntidades());
			ArrayList<Cartao> card = (ArrayList<Cartao>) sessao.getAttribute("listaCartoes");
			cli.setCartao(card);
			request.getSession().setAttribute("usuario",cli);
			d = request.getRequestDispatcher("Home.jsp");
		}

		if (resultado.getMsg() == null && operacao.equals("CARDEL")) {
			

			d = request.getRequestDispatcher("CliAdmin.jsp");
			response.setHeader("Refresh","0; URL=" + request.getContextPath() + "/SaveCards?operacao=CONSULTACARD");
		}

		if (resultado.getMsg() != null) {
			if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
				request.getSession().setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("ConsultarEndereco.jsp");
			}
		}
		d.forward(request, response);

	}
}
