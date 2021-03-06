package les12015.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import les12015.core.IDAO;
import les12015.core.IFachada;
import les12015.core.IStrategy;
import les12015.core.aplicacao.Resultado;
import les12015.core.impl.dao.CartaoDao;
import les12015.core.impl.dao.ClienteDAO;
import les12015.core.impl.dao.CupomDAO;
import les12015.core.impl.dao.EnderecoDAO;
import les12015.core.impl.dao.GraficosDAO;
import les12015.core.impl.dao.PedidoDao;
import les12015.core.impl.dao.SuplementoDAO;
import les12015.core.impl.dao.TrocaDAO;
import les12015.core.impl.dao.UnidadePedidoDao;
import les12015.core.impl.negocio.CancelaPedido;
import les12015.core.impl.negocio.NumCaracterCampos;
import les12015.core.impl.negocio.ValidaDadosCliente;
import les12015.core.impl.negocio.ValidaDadosEndereco;
import les12015.core.impl.negocio.ValidaDadosProduto;
import les12015.core.impl.negocio.ValidaPedido;
import les12015.core.impl.negocio.ValidaTroca;
import les12015.core.impl.negocio.ValidaValidade;
import les12015.core.impl.negocio.ValidadorCpf;
import les12015.dominio.Cartao;
import les12015.dominio.Cliente;
import les12015.dominio.Cupom;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.GraficosVendasCategoria;
import les12015.dominio.Pedido;
import les12015.dominio.Suplementos;
import les12015.dominio.Troca;
import les12015.dominio.Unidade;

public class Fachada implements IFachada {

	/**
	 * Mapa de DAOS, ser� indexado pelo nome da entidade O valor � uma inst�ncia do
	 * DAO para uma dada entidade;
	 */
	private Map<String, IDAO> daos;

	/**
	 * Mapa para conter as regras de neg�cio de todas opera��es por entidade; O
	 * valor � um mapa que de regras de neg�cio indexado pela opera��o
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;

	private Resultado resultado;

	public Fachada() {
		/* Int�nciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Int�nciando o Map de Regras de Neg�cio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();

		/* Criando inst�ncias dos DAOs a serem utilizados */
		ClienteDAO cliDAO = new ClienteDAO();
		SuplementoDAO supDao = new SuplementoDAO();
		EnderecoDAO endDAO = new EnderecoDAO();
		CartaoDao carDao = new CartaoDao();
		CupomDAO cupDao = new CupomDAO();
		PedidoDao pedDao = new PedidoDao(); 
		TrocaDAO trcDAO = new TrocaDAO(); 
		GraficosDAO grfDAO = new GraficosDAO();
		//UnidadePedidoDao uniDAO = new UnidadePedidoDao();

		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		daos.put(Cliente.class.getName(), cliDAO);
		daos.put(Suplementos.class.getName(), supDao);
		daos.put(Endereco.class.getName(), endDAO);
		daos.put(Cartao.class.getName(), carDao);
		daos.put(Unidade.class.getName(), supDao);
		daos.put(Cupom.class.getName(), cupDao);
		daos.put(Pedido.class.getName(), pedDao);
		daos.put(Troca.class.getName(), trcDAO);
		daos.put(GraficosVendasCategoria.class.getName(), grfDAO);
		//daos.put(Unidade.class.getName(), uniDAO);
		/* Criando inst�ncias de regras de neg�cio a serem utilizados */

		ValidadorCpf vCpf = new ValidadorCpf();
		ValidaDadosCliente cliVal = new ValidaDadosCliente();
		ValidaDadosProduto vProd = new ValidaDadosProduto();
		ValidaValidade vval = new ValidaValidade();
		NumCaracterCampos numCal = new NumCaracterCampos();
		ValidaDadosEndereco vEnd = new ValidaDadosEndereco();
		ValidaPedido vPed = new ValidaPedido();
		CancelaPedido cPed = new CancelaPedido();
		ValidaTroca vTroca = new  ValidaTroca();
		/*
		 * Criando uma lista para conter as regras de neg�cio de cliente quando a
		 * opera��o for salvar
		 */
		// List<IStrategy> rnsLoginCliente = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarProdutos = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarEnderecos = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarPedidos = new ArrayList<IStrategy>();
		List<IStrategy> rnsCancelarPedidos = new ArrayList<IStrategy>();
		List<IStrategy> rnsAprovarTrocas = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na opera��o salvar do cliente */
		rnsSalvarCliente.add(vCpf);
		rnsSalvarCliente.add(cliVal);
		rnsSalvarProdutos.add(vProd);
		rnsSalvarProdutos.add(vval);
		rnsSalvarProdutos.add(numCal);
		rnsSalvarEnderecos.add(vEnd);
		rnsSalvarPedidos.add(vPed);
		rnsCancelarPedidos.add(cPed);
		rnsAprovarTrocas.add(vTroca);
		
		/*
		 * Cria o mapa que poder� conter todas as listas de regras de neg�cio espec�fica
		 * por opera��o do cliente
		 */
		Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsEndereco = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsPedido = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsTroca = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na opera��o salvar no mapa do cliente (lista
		 * criada na linha 93)
		 */
		rnsCliente.put("SALVAR", rnsSalvarCliente);
		// rnsCliente.put("CONSULTAR", rnsLoginCliente);
		rnsProduto.put("SALVAR", rnsSalvarProdutos);
		rnsEndereco.put("SALVAR", rnsSalvarEnderecos);
		rnsPedido.put("SALVAR", rnsSalvarPedidos);
		rnsPedido.put("ALTERAR", rnsCancelarPedidos);
		rnsTroca.put("ALTERAR", rnsAprovarTrocas);
		/*
		 * Adiciona o mapa(criado na linha 101) com as regras indexadas pelas opera��es
		 * no mapa geral indexado pelo nome da entidade. Observe que este mapa (rns) � o
		 * mesmo utilizado na linha 88.
		 */
		rns.put(Cliente.class.getName(), rnsCliente);
		rns.put(Suplementos.class.getName(), rnsProduto);
		rns.put(Endereco.class.getName(), rnsEndereco);
		rns.put(Pedido.class.getName(), rnsPedido);
		rns.put(Troca.class.getName(), rnsTroca);
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar o registro!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar o registro!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;

	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "CONSULTAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				List<EntidadeDominio> entidades = dao.consultar(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar a consulta!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;

	}

	public Resultado comprar(EntidadeDominio entidade) {

		Resultado resultado = new Resultado();
		Unidade itemCarrinho = (Unidade) entidade;
		Suplementos sup = itemCarrinho.getSup();

		if (sup != null) {
			

			SuplementoDAO dao = new SuplementoDAO();
			List<EntidadeDominio> entidadeSuplemento = dao.consultar(sup);

			Suplementos s = (Suplementos) entidadeSuplemento.get(0);

			itemCarrinho.setSup(s);

			List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
			itens.add(itemCarrinho);

			resultado.setEntidades(itens);

			String msg = executarRegras(itemCarrinho, "COMPRAR");

			resultado.setMsg(msg);
			if (resultado.getMsg() != null) {
				//itemCarrinho.setQuantidade(s.get);
			}
		}
		return resultado;
	}

	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);

		if (regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (IStrategy s : regras) {
					String m = s.processar(entidade);

					if (m != null) {
						msg.append(m);
						msg.append("\n");
					}
				}
			}

		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}
}
