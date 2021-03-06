package les12015.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.GraficosVendasCategoria;

public class GraficoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		GraficosVendasCategoria grafico = new GraficosVendasCategoria();
		Integer ano = Integer.parseInt(request.getParameter("ano"));
		Integer mes1 = Integer.parseInt(request.getParameter("mes1"));
		Integer mes2 = Integer.parseInt(request.getParameter("mes2"));
		grafico.setAno(ano);
		grafico.setMes1(mes1);
		grafico.setMes2(mes2);
		return grafico;
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("dados", resultado.getEntidades());
		d = request.getRequestDispatcher("Admin.jsp");
		d.forward(request, response);
	}

}
