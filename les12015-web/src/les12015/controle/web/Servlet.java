package les12015.controle.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.command.ICommand;
import les12015.controle.web.command.impl.AlterarCommand;
import les12015.controle.web.command.impl.ConsultarCommand;
import les12015.controle.web.command.impl.SalvarCommand;
import les12015.controle.web.vh.IViewHelper;
import les12015.controle.web.vh.impl.CarrinhoViewHelper;
import les12015.controle.web.vh.impl.CartaoViewHelper;
import les12015.controle.web.vh.impl.ClienteViewHelper;
import les12015.controle.web.vh.impl.CupomViewHelper;
import les12015.controle.web.vh.impl.EnderecoViewHelper;
import les12015.controle.web.vh.impl.PedidoViewHelper;
import les12015.controle.web.vh.impl.ProdutoViewHelper;
import les12015.controle.web.vh.impl.TrocaViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.EntidadeDominio;

/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;

	/**
	 * Default constructor.
	 */
	public Servlet() {

		/*
		 * Utilizando o command para chamar a fachada e indexando cada command pela
		 * operação garantimos que esta servelt atenderá qualquer operação
		 */
		commands = new HashMap<String, ICommand>();

		// CLIENTE
		commands.put("SALVAR", new SalvarCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("LOGAR", new ConsultarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("SALVAREND", new SalvarCommand());
		commands.put("CONSULTAREND", new ConsultarCommand());
		commands.put("DELEND", new AlterarCommand());
		commands.put("SAVECARD", new SalvarCommand());
		commands.put("CONSULTACARD", new ConsultarCommand());
		commands.put("CARDEL", new AlterarCommand());
		// PRODUTO
		commands.put("SALVARSUP", new SalvarCommand());
		commands.put("CONSULTARSUP", new ConsultarCommand());
		commands.put("CONSULTARHOME", new ConsultarCommand());
		commands.put("VERPRODUTO", new ConsultarCommand());
		commands.put("ALTERARSUP", new AlterarCommand());
		// CARRINHO
		commands.put("addCarrinho", new ConsultarCommand());
		commands.put("REMOVER", new ConsultarCommand());
		commands.put("QUANTIDADE", new ConsultarCommand());
		commands.put("ENDHOME", new ConsultarCommand());
		commands.put("CARDHOME", new ConsultarCommand());

		// CUPOM
		commands.put("SAVECUPOM", new SalvarCommand());
		commands.put("CUPOM", new ConsultarCommand());

		// PEDIDO
		commands.put("FINALIZAR", new SalvarCommand());
		commands.put("CONSULTAPEDIDO", new ConsultarCommand());
		commands.put("PEDIDOADMIN", new ConsultarCommand());
		commands.put("VERPEDIDO", new ConsultarCommand());
		commands.put("SPEDIDO", new AlterarCommand());
		
		//TROCA
		commands.put("UNICHANGE", new AlterarCommand());
		commands.put("PEDIDOCANCEL", new AlterarCommand());
		commands.put("FULLTROCA", new AlterarCommand());
		commands.put("TROCADMIN", new ConsultarCommand());
		commands.put("TROCACLI", new ConsultarCommand());
		commands.put("APROVATROCA", new AlterarCommand());
		/*
		 * Utilizando o ViewHelper para tratar especificações de qualquer tela e
		 * indexando cada viewhelper pela url em que esta servlet é chamada no form
		 * garantimos que esta servelt atenderá qualquer entidade
		 */

		vhs = new HashMap<String, IViewHelper>();
		/*
		 * A chave do mapa é o mapeamento da servlet para cada form que está configurado
		 * no web.xml e sendo utilizada no action do html
		 */
		vhs.put("/les12015-web/SalvarCliente", new ClienteViewHelper());
		vhs.put("/les12015-web/SalvarEndereco", new EnderecoViewHelper());
		vhs.put("/les12015-web/ConsultarEndereco", new EnderecoViewHelper());
		vhs.put("/les12015-web/DeleteEndereco", new EnderecoViewHelper());
		vhs.put("/les12015-web/ClienteLogin", new ClienteViewHelper());
		vhs.put("/les12015-web/AlterarCliente", new ClienteViewHelper());
		vhs.put("/les12015-web/SalvarProduto", new ProdutoViewHelper());
		vhs.put("/les12015-web/ConsultarProduto", new ProdutoViewHelper());
		vhs.put("/les12015-web/SaveCards", new CartaoViewHelper());
		vhs.put("/les12015-web/adicionarCarrinho", new CarrinhoViewHelper());
		vhs.put("/les12015-web/SalvarCupom", new CupomViewHelper());
		vhs.put("/les12015-web/finalizaCompra", new PedidoViewHelper());
		vhs.put("/les12015-web/efetuaTroca", new TrocaViewHelper());

	}

	/**
	 * TODO Descrição do Método
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcessRequest(request, response);
	}

	protected void doProcessRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtêm a uri que invocou esta servlet (O que foi definido no methdo do form
		// html)
		String uri = request.getRequestURI();

		// Obtêm a operação executada
		String operacao = request.getParameter("operacao");

		// Obtêm um viewhelper indexado pela uri que invocou esta servlet
		IViewHelper vh = vhs.get(uri);

		// O viewhelper retorna a entidade especifica para a tela que chamou esta
		// servlet
		EntidadeDominio entidade = vh.getEntidade(request);

		// Obtêm o command para executar a respectiva operação
		ICommand command = commands.get(operacao);

		/*
		 * Executa o command que chamará a fachada para executar a operação requisitada
		 * o retorno é uma instância da classe resultado que pode conter mensagens derro
		 * ou entidades de retorno
		 */
		Resultado resultado = command.execute(entidade);

		/*
		 * Executa o método setView do view helper específico para definir como deverá
		 * ser apresentado o resultado para o usuário
		 */
		vh.setView(resultado, request, response);

	}
}
