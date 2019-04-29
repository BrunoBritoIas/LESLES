package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Pedido;

public class PedidoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
		Pedido ped = (Pedido) request.getSession().getAttribute("pedido");
		Pedido pedido = new Pedido();
		LocalDateTime ldt = LocalDateTime.now().plusDays(1);
		DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY", Locale.ENGLISH);
		String data = formatter.format(ldt);
		
		if(operacao.equals("FINALIZAR")) {
			pedido.setDtPedido(data);
			pedido.setEndereco(cliente.getEndereco().get(1));
			pedido.setIDusuario(cliente.getIdCliente());
			pedido.setUnidade(ped.getUnidade());
			pedido.setStatus("Aguardando Aprovação");
			pedido.setPrecoFinal(ped.getPrecoFinal());
			pedido.setPrecoFrete(ped.getPrecoFrete());
			pedido.setPrecoTotal(ped.getPrecoTotal());
			pedido.setQtdItens(ped.getQtdItens());
			
			return pedido;
		}
			
		return null;
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}
