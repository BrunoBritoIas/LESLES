
package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;

public class ClienteViewHelper implements IViewHelper {

	/**
	 * TODO Descrição do Método
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @see les12015.controle.web.vh.IViewHelper#getEntidade(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cliente c = new Cliente();
		if (operacao.equals("LOGAR")) {
			String email = request.getParameter("txtEmail");
			String senha = request.getParameter("txtSenha");
			c.setEmail(email);
			c.setSenha(senha);
			return c;

		}

		if (operacao.equals("SALVAR")) {
			String nome = request.getParameter("txtNome");
			String cpf = request.getParameter("txtCpf");
			// String id = request.getParameter("txtId");
			String email = request.getParameter("txtEmail");
			String genero = request.getParameter("txtGender");
			String nascimento = request.getParameter("bday");
			String senha = request.getParameter("txtSenha");
			String numTel = request.getParameter("phoneNumber");

			/*
			 * if(id != null && !id.trim().equals("")){ c.setId(Integer.parseInt(id)); }
			 */
			c.setNome(nome);
			c.setCpf(cpf);
			c.setEmail(email);
			c.setGenero(genero);
			c.setDtNasc(nascimento);
			c.setSenha(senha);
			c.setTelefone(numTel);
			c.setTipoUser("COMUM");
			c.setStatus("ATIVO");
		}

		if (operacao.equals("ALTERAR")) {
			Cliente client = (Cliente) request.getSession().getAttribute("usuario");

			Integer id = Integer.parseInt(request.getParameter("txtIdnovo"));
			String nome = "".equals(request.getParameter("txtNome")) ? client.getNome()
					: request.getParameter("txtNome");
			String cpf = "".equals(request.getParameter("txtCpf")) ? client.getCpf() : request.getParameter("txtCpf");
			// String id = request.getParameter("txtId");
			String email = "".equals(request.getParameter("txtEmail")) ? client.getEmail()
					: request.getParameter("txtEmail");
			String genero = "".equals(request.getParameter("txtGender")) ? client.getGenero()
					: request.getParameter("txtGender");
			String nascimento = "".equals(request.getParameter("bday")) ? client.getDtNasc()
					: request.getParameter("bday");
			String senha = "".equals(request.getParameter("txtSenha")) ? client.getSenha()
					: request.getParameter("txtSenha");
			String numTel = "".equals(request.getParameter("phoneNumber")) ? client.getTelefone()
					: request.getParameter("phoneNumber");

			/*
			 * if(id != null && !id.trim().equals("")){ c.setId(Integer.parseInt(id)); }
			 */
			c.setNome(nome);
			c.setCpf(cpf);
			c.setEmail(email);
			c.setGenero(genero);
			c.setDtNasc(nascimento);
			c.setSenha(senha);
			c.setTelefone(numTel);
			c.setIdCliente(id);
			HttpSession se = request.getSession();
			se.setAttribute("user", c);

		}
		return c;
	}

	/**
	 * TODO Descrição do Método
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @see les12015.controle.web.vh.IViewHelper#setView(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;

		String operacao = request.getParameter("operacao");
		HttpSession sessao = request.getSession();
		HttpSession sess = request.getSession();
		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("CadastroCliente.jsp");
				resultado.setMsg("Você foi cadastrado com sucesso!");
			}
			if (operacao.equals("LOGAR")) {
				List<EntidadeDominio> entidades = resultado.getEntidades();
				for (int i = 0; i < entidades.size(); i++) {
					Cliente cli = (Cliente) entidades.get(i);
					if (request.getParameter("txtEmail").trim().equals(cli.getEmail())
							&& (request.getParameter("txtSenha").trim().equals(cli.getSenha())))

					{

	
						sess.setAttribute("usuario", cli);
						d = request.getRequestDispatcher("CliAdmin.jsp");
						break;

					} else {
						request.getSession().setAttribute("resultadoLogin", resultado);
						resultado.setMsg("Email ou senha Incorretos");
						d = request.getRequestDispatcher("Login.jsp");
						resultado.equals(null);
					}

				}
			}

			if (operacao.equals("CONSULTAR")) {
				sessao.setAttribute("listaCliente", resultado.getEntidades());
				d = request.getRequestDispatcher("CliAdmin.jsp");
			}
			
			if ( resultado.getMsg() == null && operacao.equals("ALTERAR")) {
				resultado.setMsg("Produto Alterado com sucesso!");
				Cliente clie = (Cliente) request.getSession().getAttribute("user");
				sess.setAttribute("usuario", clie);
				d = request.getRequestDispatcher("CliAdmin.jsp");
				resultado.equals(null);
				
			}
			sessao.setAttribute("resultado", resultado);


		}
		d.forward(request, response);
	}

}
