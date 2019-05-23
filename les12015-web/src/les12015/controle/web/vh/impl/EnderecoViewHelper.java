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
import les12015.dominio.Cliente;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;

public class EnderecoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {

		Cliente cu = (Cliente) request.getSession().getAttribute("usuario");
		String operacao = request.getParameter("operacao");
		Endereco endereco = new Endereco();

		if (operacao.equals("SALVAREND")) {

			String cep = request.getParameter("txtCep");
			String estado = request.getParameter("txtEstado");
			String cidade = request.getParameter("txtCidade");
			String obs = request.getParameter("txtObservacao");
			String tipo_res = request.getParameter("txtTipoResidencia");
			String tipo_log = request.getParameter("txtLogradouro");
			String logradouro = request.getParameter("txtLogradouro");
			String numero = request.getParameter("txtNumero");
			String bairro = request.getParameter("txtBairro");
			endereco = new Endereco();

			int id = cu.getIdCliente();
			endereco.setCli_id(id);
			endereco.setId(id);
			endereco.setTipo_res(tipo_res);
			endereco.setTipo_log(tipo_log);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setEstado(estado);
			endereco.setCidade(cidade);
			endereco.setPais("Brasil");
			endereco.setObs(obs);
			endereco.setPref("FALSE");
			endereco.setStatus("ATIVO");
			endereco.setTipo_res("bacana");
		}

		if (operacao.equals("CONSULTAREND") || operacao.equals("ENDHOME")) {
			int id = cu.getIdCliente();
			endereco.setCli_id(id);
			endereco.setId(id);

		}

		if (operacao.equals("DELEND")) {
			HttpSession sessao = request.getSession();

			int EndId = Integer.parseInt(request.getParameter("txtIdnovo"));
			ArrayList<EntidadeDominio> enderecos = (ArrayList<EntidadeDominio>) sessao.getAttribute("listaEnderecos");
			for (EntidadeDominio e : enderecos) {
				if (e.getId() == EndId) {
					endereco = (Endereco) e;
				}
			}

			String cep = request.getParameter("txtCep").equals("") ? endereco.getCep() : request.getParameter("txtCep");
			String estado = request.getParameter("txtEstado").equals("") ? endereco.getEstado()
					: request.getParameter("txtEstado");
			String cidade = request.getParameter("txtCidade").equals("") ? endereco.getCidade()
					: request.getParameter("txtCidade");
			String obs = request.getParameter("txtObservacao").equals("") ? endereco.getObs()
					: request.getParameter("txtObservacao");
			String tipo_log = request.getParameter("txtLogradouro").equals("") ? endereco.getTipo_log()
					: request.getParameter("txtLogradouro");
			String logradouro = request.getParameter("txtLogradouro").equals("") ? endereco.getLogradouro()
					: request.getParameter("txtLogradouro");
			String numero = request.getParameter("txtNumero").equals("") ? endereco.getNumero()
					: request.getParameter("txtNumero");
			String bairro = request.getParameter("txtBairro").equals("") ? endereco.getBairro()
					: request.getParameter("txtBairro");

			endereco.setId(EndId);
			endereco.setStatus("A");
			endereco.setTipo_log(tipo_log);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setEstado(estado);
			endereco.setCidade(cidade);
			endereco.setObs(obs);

		}
		return endereco;
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		HttpSession sessao = request.getSession();
		Cliente cu = (Cliente) request.getSession().getAttribute("usuario");
		Cliente cli = cu;
		String operacao = request.getParameter("operacao");

		if (operacao.equals("SALVAREND")) {
			request.getSession().setAttribute("cadastroEnd", resultado);

			d = request.getRequestDispatcher("CliAdmin.jsp");
		}

		if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {

			d = request.getRequestDispatcher("ConsultarEndereco.jsp");
		}

		if (resultado.getMsg() == null && operacao.equals("CONSULTAREND")) {
			sessao.setAttribute("listaEnderecos", resultado.getEntidades());
			d = request.getRequestDispatcher("CliAdmin.jsp");
		}

		if (resultado.getMsg() == null && operacao.equals("ENDHOME")) {
			sessao.setAttribute("listaEnderecos", resultado.getEntidades());
			ArrayList<Endereco> end = (ArrayList<Endereco>) sessao.getAttribute("listaEnderecos");
			cli.setEndereco(end);
			request.getSession().setAttribute("usuario", cli);
			d = request.getRequestDispatcher("Home.jsp");
		}

		if (operacao.equals("DELEND")) {

			d = request.getRequestDispatcher("CliAdmin.jsp");

		}

		if (resultado.getMsg() != null) {
			if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
				request.getSession().setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("ConsultarEndereco.jsp");
			}
		}
		ArrayList<Endereco> end = (ArrayList<Endereco>) sessao.getAttribute("listaEnderecos");
		cli.setEndereco(end);
		d.forward(request, response);

	}

}
