<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table id="datatable" class="table table-striped table-bordered"
	cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>N.</th>
			<th>Produto</th>
			<th>Quantidade</th>
			<th>Credito</th>
			<th>Usuario</th>
			<th>CPF</th>
			<th>Status</th>
			<th>Aprovar</th>
			<th>Reprovar</th>



		</tr>
	</thead>
	<tbody>
		<c:forEach items="${todasTrocas}" var="troca">
			<tr>
				<td>${troca.id}</td>
				<td>${troca.produto.nome}</td>
				<td>${troca.qtdItens}</td>
				<td>${troca.qtdCredito}</td>
				<td>${troca.user.nome}</td>
				<td>${troca.user.cpf}</td>
				<td>${troca.status}</td>
				<c:if test="${troca.status eq 'Troca Unitaria'}">
					<td><p data-placement="top" data-toggle="tooltip" title="Edit">
							<a
								href="efetuaTroca?operacao=APROVATROCA&status=APROVADO&idUser=${troca.idUser}&idSup=${troca.produto.id}&idTroca=${troca.id}&credito=${troca.qtdCredito}"><button
									type="button" class="btn btn-success btn-xs">
									<span class="glyphicon glyphicon-ok-sign"></span>
								</button></a>
						</p></td>

					<td><p data-placement="top" data-toggle="tooltip" title="Edit">
							<a
								href="efetuaTroca?operacao=APROVATROCA&status=APROVADO&idUser=${troca.idUser}&idUser=${troca.idSup}"><button
									type="button" class="btn btn-danger btn-xs">
									<span class="glyphicon glyphicon-ok-sign"></span>
								</button></a>
						</p></td>
				</c:if>

				<c:if test="${troca.status ne 'Troca Unitaria'}">
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
		value=TROCADMIN id="operacao" style="width: 95%;">
		<span class="glyphicon glyphicon-ok-sign"></span> consultar
	</button>
</form>