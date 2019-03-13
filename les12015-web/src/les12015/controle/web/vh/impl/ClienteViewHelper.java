
package les12015.controle.web.vh.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;



public class ClienteViewHelper implements IViewHelper {

	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 * @see les12015.controle.web.vh.IViewHelper#getEntidade(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String nome = request.getParameter("txtNome");
		String cpf = request.getParameter("txtCpf");
		//String id = request.getParameter("txtId");
		String email = request.getParameter("txtEmail");
		String genero = request.getParameter("txtGender");
		String nascimento = request.getParameter("bday"); 
		
		
		Cliente c = new Cliente();
		/*if(id != null && !id.trim().equals("")){
		c.setId(Integer.parseInt(id));
		}*/
		c.setNome(nome);
		c.setCpf(cpf);
		c.setEmail(email);
		c.setGenero(genero);
		c.setDtNasc(nascimento);
		return c;
	}
	
	

	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 * @see les12015.controle.web.vh.IViewHelper#setView(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void setView(Resultado resultado, HttpServletRequest request, 
			HttpServletResponse response)  throws IOException, ServletException {
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
		HttpSession sessao = request.getSession();
		
		if(resultado.getMsg() == null){
			if(operacao.equals("SALVAR")){
				resultado.setMsg("Produto cadastrado com sucesso!");
			}
			
			if(operacao.equals("CONSULTAR")){
				sessao.setAttribute("listaCliente", resultado.getEntidades());
			}
			sessao.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("Admin.jsp"); 
			
		}
		d.forward(request,response); 
	}

}
