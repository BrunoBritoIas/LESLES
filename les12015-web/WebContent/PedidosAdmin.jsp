<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table id="datatable" class="table table-striped table-bordered"
	cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>Nome</th>
			<th>CPF</th>
			<th>Frete</th>
			<th>Quantidade</th>
			<th>Data</th>
			<th>Status</th>
			<th>Aprovar</th>
			<th>Reprovar</th>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${todosPedidos}" var="pedido">
			<tr>
				<td>${pedido.nomeUser}</td>
				<td>${pedido.cpfUser}</td>
				<td>${pedido.precoFrete}</td>
				<td>${pedido.precoFinal}</td>
				<td>${pedido.dtPedido}</td>
				<td>${pedido.status}</td>
				<c:if test="${pedido.status eq 'Aguardando Aprovação'}">
					<td><p data-placement="top" data-toggle="tooltip" title="Edit">
							<a
								href="finalizaCompra?operacao=SPEDIDO&status=APROVADO&idPedido=${pedido.id}"><button
									type="button" class="btn btn-success btn-xs">
									<span class="glyphicon glyphicon-ok-sign"></span>
								</button></a>
						</p></td>

					<td><p data-placement="top" data-toggle="tooltip" title="Edit">
							<a
								href="finalizaCompra?operacao=SPEDIDO&status=REPROVADO&idPedido=${pedido.id}"><button
									type="button" class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-ok-sign"></span>
								</button></a>
						</p></td>
				</c:if>

				<c:if test="${pedido.status ne 'Aguardando Aprovação'}">
					<td><p data-placement="top" data-toggle="tooltip" title="Edit">

							<button disabled type="button" class="btn btn-success btn-xs">
								<span class="glyphicon glyphicon-ok-sign"></span>
							</button>
						</p></td>

					<td><p data-placement="top" data-toggle="tooltip" title="Edit">
							<button disabled type="button" class="btn btn-danger btn-xs">
								<span class="glyphicon glyphicon-ok-sign"></span>
							</button>
						</p></td>
				</c:if>
			</tr>
		</c:forEach>

	</tbody>
</table>
<form action="finalizaCompra" method="post">
	<button type="submit" class="btn btn-primary btn-lg" name="operacao"
		value=PEDIDOADMIN id="operacao" style="width: 95%;">
		<span class="glyphicon glyphicon-ok-sign"></span> consultar
	</button>
</form>