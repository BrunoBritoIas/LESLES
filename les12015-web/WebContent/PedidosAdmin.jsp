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
			<th>N.Pedido</th>
			<th>Quantidade</th>
			<th>PrecoFinal</th>
			<th>Status</th>
			<th>Aprovar</th>
			<th>Reprovar</th>
			<th>Troca</th>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listaPedidos}" var="pedido">
			<tr>
				<td>${pedido.nomeUser}</td>
				<td>${pedido.cpfUser}</td>
				<td>${pedido.precoFrete}</td>
				<td>${pedido.precoFinal}</td>
				<td>${pedido.dtPedido}</td>
				<td>${pedido.status}</td>
				<td><p data-placement="top" data-toggle="tooltip" title="Edit">
						<button type="button" class="btn btn-success btn-xs"
							data-title="Edit" data-toggle="modal" data-target="#edit">
							<span class="glyphicon glyphicon-ok-sign"></span>
						</button>
					</p></td>
				<td><p data-placement="top" data-toggle="tooltip" title="Edit">
						<button type="button" class="btn btn-danger btn-xs"
							data-title="Edit" data-toggle="modal" data-target="#edit">
							<span class="glyphicon glyphicon-remove-sign"></span>
						</button>
					</p></td>
				<td><p data-placement="top" data-toggle="tooltip" title="Edit">
						<button type="button" class="btn btn-warning btn-xs"
							data-title="Edit" data-toggle="modal" data-target="#edit">
							<span class="glyphicon glyphicon-pencil"></span>
						</button>
					</p></td>
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