<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table id="datatable" class="table table-striped table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>Produto</th>
			<th>Quantidade</th>
			<th>Credito</th>
			<th>Usuario</th>
			<th>CPF</th>
			<th>Status</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${clienteTroca}" var="troca">
			<tr>
				<td>${troca.produto.nome}</td>
				<td>${troca.qtdItens}</td>
				<td>${troca.qtdCredito}</td>
				<td>${troca.user.nome}</td>
				<td>${troca.user.cpf}</td>
				<td>${troca.status}</td>			
			</tr>
		</c:forEach>

	</tbody>
</table>
<form action="finalizaCompra" method="post">
	<button type="submit" class="btn btn-primary btn-lg" name="operacao"
		value=TROCACLI id="operacao" style="width: 95%;">
		<span class="glyphicon glyphicon-ok-sign"></span> consultar
	</button>
</form>