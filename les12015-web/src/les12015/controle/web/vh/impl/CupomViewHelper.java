package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Cupom;
import les12015.dominio.EntidadeDominio;

public class CupomViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cupom cupom = new Cupom();
		if (operacao.equals("SAVECUPOM")) {
					String id = request.getParameter("txtId");

			String serial = request.getParameter("txtSerial");
			String desconto = request.getParameter("txtDesconto");
			String dtVal = request.getParameter("dtValidade");
			String tpCupom = request.getParameter("tpCupom");
			System.out.println(serial);
			System.out.println(desconto);

			cupom.setDesconto(Double.parseDouble(desconto));
			cupom.setSerial(serial);
			cupom.setDtVal(dtVal);
			cupom.setTpCupom(tpCupom);
			
			return cupom;
			
			
		}
		
		if(operacao.equals("BUSCAR")) {
			Cupom c = new Cupom();
			return c;
		}
		
		if(operacao.equals("CUPOM")) {
			String serial = request.getParameter("txtCupom");
			Cupom c = new Cupom();
			return c;
		}
		return null;
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher d = null;

		String operacao = request.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SAVECUPOM")) {
				resultado.setMsg("Cupom cadastrado com sucesso!");
			}

			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("Admin.jsp");
		}

			if(operacao.equals("CUPOM")) {
				List<EntidadeDominio> entidades = resultado.getEntidades();
				for (int i = 0; i < entidades.size(); i++) {
					Cupom cupom = (Cupom) entidades.get(i);
					if(request.getParameter("txtCupom").trim().equals(cupom.getSerial()))
					{
						HttpSession sessao = request.getSession();
						sessao.setAttribute("cupom", cupom);
						d = request.getRequestDispatcher("Carrinho.jsp");
						break;
					}else {
						d = request.getRequestDispatcher("Carrinho.jsp");


					}
					
					
				}
				
			}
			
			if (operacao.equals("BUSCAR")) {
				List<EntidadeDominio> entidades = resultado.getEntidades();
				for (int i = 0; i < entidades.size(); i++) {
					
					Cupom cupom = (Cupom) entidades.get(i);
					request.getSession().setAttribute("resultado", resultado);
					d = request.getRequestDispatcher("ConCupom.jsp");
					System.out.println("cvccc");

				}
			}
			request.getSession().getAttribute("carrinho");
			request.getSession().getAttribute("itens");
			d.forward(request, response);
		}

	}