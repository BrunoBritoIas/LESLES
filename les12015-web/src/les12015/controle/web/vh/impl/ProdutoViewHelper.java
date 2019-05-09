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
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;

public class ProdutoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Suplementos sup = new Suplementos();
		if (operacao.equals("SALVARSUP")) {
			String nome = request.getParameter("txtNome");
			String marca = request.getParameter("txtMarca");
			String validade = request.getParameter("txtValidade").equals("") ? "vazio"
					: request.getParameter("txtValidade");
			String descricao = request.getParameter("txtDesc");
			Double peso = request.getParameter("txtPeso").equals("") ? 0.0
					: Double.parseDouble(request.getParameter("txtPeso"));
			String categoria = request.getParameter("txtCategoria");
			Double proteina = "".equals(request.getParameter("txtProt")) ? 0.0
					: Double.parseDouble(request.getParameter("txtProt"));
			Double carboidrato = request.getParameter("txtCarb").equals("") ? 0.0
					: Double.parseDouble(request.getParameter("txtCarb"));
			Double gordura = request.getParameter("txtFat").equals("") ? 0.0
					: Double.parseDouble(request.getParameter("txtFat"));
			Double caloria = request.getParameter("txtCal").equals("") ? 0.0
					: Double.parseDouble(request.getParameter("txtCal"));
			Double preco = request.getParameter("txtPreco").equals("") ? 0.0
					: Double.parseDouble(request.getParameter("txtPreco"));
			
			Integer quantidade = request.getParameter("txtQuantidade").equals("") ? 0 : Integer.parseInt(request.getParameter("txtQuantidade"));
			/*
			 * if(id != null && !id.trim().equals("")){ c.setId(Integer.parseInt(id)); }
			 */

			sup.setNome(nome);
			sup.setMarca(marca);
			sup.setDescricao(descricao);
			sup.setPeso(peso);
			sup.setCategoria(categoria);
			sup.setProteina(proteina);
			sup.setCarboidratos(carboidrato);
			sup.setGordura(gordura);
			sup.setCalorias(caloria);
			sup.setRating(5.0);
			sup.setPreco(preco);
			sup.setStatus("ATIVO");
			sup.setValidade(validade);
			sup.setQuantidade(quantidade);
		}

		if (operacao.equals("CONSULTARSUP") ||operacao.equals("CONSULTARHOME"))  {
			String nome = request.getParameter("txtNome");
			String marca = request.getParameter("txtMarca");
			String categoria = request.getParameter("txtCategoria");
			Double preco = 5.5;
			sup.setNome(nome);
			sup.setMarca(marca);
			sup.setCategoria(categoria);
			sup.setRating(5.0);
			sup.setPreco(preco);

		}
		if (operacao.equals("ALTERARSUP")) {
			HttpSession sessao = request.getSession();

			Integer id = Integer.parseInt(request.getParameter("txtIdnovo"));
			ArrayList<EntidadeDominio> suplementos = (ArrayList<EntidadeDominio>) sessao.getAttribute("listaSuplementos");
			
			for (EntidadeDominio s : suplementos) {
				if (s.getId() == id) {
					sup = (Suplementos) s;
				}
			}
			String nome = request.getParameter("txtNome").equals("") ? sup.getNome() : request.getParameter("txtNome");
			String status = request.getParameter("txtStatus").equals("") ? sup.getNome()
					: request.getParameter("txtStatus");
			String marca = request.getParameter("txtMarca").equals("") ? sup.getMarca()
					: request.getParameter("txtMarca");
			String validade = request.getParameter("txtValidade").equals("") ? sup.getValidade()
					: request.getParameter("txtValidade");
			String descricao = request.getParameter("txtDesc").equals("") ? sup.getDescricao()
					: request.getParameter("txtDesc");
			Double peso = request.getParameter("txtPeso").equals("") ? sup.getPeso()
					: Double.parseDouble(request.getParameter("txtPeso"));
			String categoria = request.getParameter("txtCategoria").equals("") ? sup.getCategoria()
					: request.getParameter("txtCategoria");
			Double proteina = "".equals(request.getParameter("txtProt")) ? sup.getProteina()
					: Double.parseDouble(request.getParameter("txtProt"));
			Double carboidrato = request.getParameter("txtCarb").equals("") ? sup.getCarboidratos()
					: Double.parseDouble(request.getParameter("txtCarb"));
			Double gordura = request.getParameter("txtFat").equals("") ? sup.getGordura()
					: Double.parseDouble(request.getParameter("txtFat"));
			Double caloria = request.getParameter("txtCal").equals("") ? sup.getCalorias()
					: Double.parseDouble(request.getParameter("txtCal"));
			Double preco = request.getParameter("txtPreco").equals("") ? sup.getPreco()
					: Double.parseDouble(request.getParameter("txtCal"));

			sup.setNome(nome);
			sup.setMarca(marca);
			sup.setValidade(validade);
			sup.setDescricao(descricao);
			sup.setPeso(peso);
			sup.setCategoria(categoria);
			sup.setProteina(proteina);
			sup.setCarboidratos(carboidrato);
			sup.setGordura(gordura);
			sup.setCalorias(caloria);
			sup.setPreco(preco);
			sup.setStatus(status);
			sup.setId(id);

		}
		
		if(operacao.equals("VERPRODUTO")) {
			Integer ProdID = Integer.parseInt( request.getParameter("id"));
			sup.setId(ProdID);
		}
		
		return sup;

	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		HttpSession sessao = request.getSession();

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVARSUP")) {
				resultado.setMsg("Produto cadastrado com sucesso!");
			}
			sessao.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("Admin.jsp");

		}
		else {
			sessao.setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("Admin.jsp");
		}
		
		if (resultado.getMsg() == null && operacao.equals("CONSULTARSUP")) {
			sessao.setAttribute("listaSuplementos", resultado.getEntidades());
			d = request.getRequestDispatcher("Admin.jsp");
		}
		else if((resultado.getMsg() == null && operacao.equals("CONSULTARHOME"))) {
			sessao.setAttribute("listaSuplementos", resultado.getEntidades());
			d = request.getRequestDispatcher("Home.jsp");
		}

		if ( resultado.getMsg() == null && operacao.equals("ALTERARSUP")) {
			d = request.getRequestDispatcher("Admin.jsp");
		}

		if(operacao.equals("VERPRODUTO")) {
			sessao.setAttribute("supDetail", resultado.getEntidades());
			sessao.setAttribute("suplemento", resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("DetalheProduto.jsp");
		}

		d.forward(request, response);
	}
}
