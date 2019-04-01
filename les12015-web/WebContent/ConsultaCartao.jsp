<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:if test="${listaCartoes == null}">
	<c:redirect url="SaveCards?operacao=CONSULTACARD" />
</c:if>
<table id="datatable" class="table table-striped table-bordered"
	cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>Titular</th>
			<th>Numero</th>
			<th>Bandeira</th>
			<th>Preferencial</th>
			<th>Validade</th>
			<th>Apagar</th>		
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listaCartoes}" var="cartao">
			<tr>
				<td>${cartao.titular}</td>
				<td>${cartao.numero}</td>
				<td>${cartao.bandeira}</td>
				<td>${cartao.preferencial}</td>
				<td>${cartao.validade}</td>
				<td><p data-placement="top" data-toggle="tooltip" title="Edit">
					<form action="SaveCards">
						<input type="hidden" id="txtIdnovo" name="txtIdnovo"
							value="${cartao.id}">
						<button type="submit" class="btn btn-danger btn-xs" id="operacao"
							name="operacao" value="CARDEL">
							<span class="	glyphicon glyphicon-remove"></span>
						</button>
					</form></td>

			</tr>
		</c:forEach>

	</tbody>
</table>
<form action="SaveCards" method="post">
	<button type="submit" class="btn btn-primary btn-lg" name="operacao"
		value=CONSULTACARD id="operacao" style="width: 95%;">
		<span class="glyphicon glyphicon-ok-sign"></span> consultar
	</button>
</form>