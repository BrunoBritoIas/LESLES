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

		if (operacao.equals("CONSULTAREND")) {
			int id = cu.getIdCliente();
			endereco.setCli_id(id);
			endereco.setId(id);
			;

		}

		if (operacao.equals("DELEND")) {
			int EndId =  Integer.parseInt(request.getParameter("txtIdnovo"));
			endereco.setId(EndId);
			endereco.setStatus("I");
		}
		return endereco;
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		HttpSession sessao = request.getSession();

		String operacao = request.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAREND")) {
				resultado.setMsg("Endereço cadastrado com sucesso!");
				request.getSession().setAttribute("resultado", resultado);

				d = request.getRequestDispatcher("CliAdmin.jsp");
			}

		}

		if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {

			d = request.getRequestDispatcher("ConsultarEndereco.jsp");
		}

		if (resultado.getMsg() == null && operacao.equals("CONSULTAREND")) {
			sessao.setAttribute("listaEnderecos", resultado.getEntidades());
			d = request.getRequestDispatcher("CliAdmin.jsp");
		}

		if (resultado.getMsg() == null && operacao.equals("DELEND")) {

			d = request.getRequestDispatcher("CliAdmin.jsp");
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
