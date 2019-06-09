package les12015.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Troca;

public class TrocaViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {

		String operacao = request.getParameter("operacao");
		Troca troca = new Troca();

		if (operacao.equals("APROVATROCA")) {
			Integer idSup = Integer.parseInt(request.getParameter("idSup"));
			Integer idUser = Integer.parseInt(request.getParameter("idUser"));
			Integer idTroca = Integer.parseInt(request.getParameter("idTroca"));
			Double credito = Double.parseDouble(request.getParameter("credito"));
			troca.setStatus("APROVADO");
			troca.setIdSup(idSup);
			troca.setIdUser(idUser);
			troca.setId(idTroca);
			troca.setQtdCredito(credito);

		}
		
		if (operacao.equals("AVALIAÇÃO"))  {
			Integer idSup = Integer.parseInt(request.getParameter("idSup"));
			Integer idUnidade = Integer.parseInt(request.getParameter("idUnidade"));
			Integer nota = Integer.parseInt(request.getParameter("nota"));
			troca.setStat("AVALIAÇÃO");
			troca.setIdUnidade(idUnidade);
			troca.setIdSup(idSup);
			troca.setNota(nota);
		}
		return troca;
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
		String operacao = request.getParameter("operacao");
		Troca t = (Troca) resultado.getEntidades().get(0);

		if (operacao.equals("APROVATROCA")) {
			cliente.setSaldo(t.getQtdCredito() + cliente.getSaldo());
			request.getSession().setAttribute("usuario", cliente);
			d = request.getRequestDispatcher("Admin.jsp");
		}
		
		if (operacao.equals("AVALIAÇÃO")) {
			d = request.getRequestDispatcher("CliAdmin.jsp");
		}
		d.forward(request, response);
	}

}
