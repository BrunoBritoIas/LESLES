<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${listaPedidos == null}">

</c:if>
<table id="datatable" class="table table-striped table-bordered"
	cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>N.Pedido</th>
			<th>Quantidade</th>
			<th>Frete</th>
			<th>PrecoFinal</th>
			<th>Data</th>
			<th>Status</th>
			<th>Detalhes</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listaPedidos}" var="pedido">
			<tr>

				<%-- <td><select class="form-control" id="exampleFormControlSelect1">
						<c:forEach var="i" begin="0" end="${listaPedidos.get(i).getCardPed().size()}">
							<option>${listaPedidos.get(i).getCardPed().get(i).getNumCartao()}</option>
						</c:forEach>
				</select></td> --%>
				<td>${pedido.id}</td>
				<td>${pedido.qtdItens}</td>
				<td>${pedido.precoFrete}</td>
				<td>${pedido.precoFinal}</td>
				<td>${pedido.dtPedido}</td>
				<td>${pedido.status}</td>
				<td><a href="finalizaCompra?operacao=VERPEDIDO&idPedido=${pedido.id}">Saiba Mais</a></td>
			</tr>
		</c:forEach>

	</tbody>
</table>
<form action="finalizaCompra" method="post">
	<button type="submit" class="btn btn-primary btn-lg" name="operacao"
		value=CONSULTAPEDIDO id="operacao" style="width: 95%;">
		<span class="glyphicon glyphicon-ok-sign"></span> consultar
	</button>
</form>