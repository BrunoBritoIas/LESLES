<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
function mandaIdpedido(num){
	 document.getElementById("idPedido").value = num;
}
</script>

<table id="datatable" class="table table-striped table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>Nome</th>
			<th>CPF</th>
			<th>Frete</th>
			<th>Valor</th>
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
				<td>R$ ${pedido.precoFinal}</td>
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
							<button type="button" class="btn btn-danger btn-xs"
								onclick="mandaIdpedido(${pedido.id})"
								data-title="Edit" data-toggle="modal" data-target="#reprova"> <span
									class="glyphicon glyphicon-ok-sign"></span>
							</button>
						</p></td>
				</c:if>
				<c:if test="${pedido.status eq 'APROVADO'}">
					<td><p data-placement="top" data-toggle="tooltip" title="Edit">
							<a
								href="finalizaCompra?operacao=SPEDIDO&status=ENTREGUE&idPedido=${pedido.id}"><button
									type="button" class="btn btn-warning btn-xs">
									<span class="glyphicon glyphicon-flag"></span>
								</button></a>
						</p></td>

					<td><p data-placement="top" data-toggle="tooltip" title="Edit">
							<button disabled type="button" class="btn btn-danger btn-xs">
								<span class="glyphicon glyphicon-ok-sign"></span>
							</button>
						</p></td>
				</c:if>

				<c:if
					test="${pedido.status eq 'REPROVADO' || pedido.status eq 'Troca ATIVA'|| pedido.status eq 'ENTREGUE'|| pedido.status eq 'FINALIZADO' }">
					<td><p data-placement="top" data-toggle="tooltip"
							title="ENTREGE">
							<a
								href="finalizaCompra?operacao=SPEDIDO&status=ENTREGUE&idPedido=${pedido.id}">
								<button disabled type="button" class="btn btn-success btn-xs">
									<span class="glyphicon glyphicon-ok-sign"></span>
								</button>
							</a>
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



<div class="modal fade" id="reprova" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<form method="get" action="finalizaCompra">
				<div class="modal-body">
					<h3 style="text-align: center;">MOTIVO</h3>
					<div class="form-group">
						<input class="form-control " type="text" placeholder=""
							id="txtMotivo" name="txtMotivo"> <input
							class="form-control " type="hidden" placeholder="" id="idPedido"
							name="idPedido"> <input class="form-control " type="hidden"
							placeholder="" value="REPROVADO" id="status" name="status">
					</div>


				</div>
				<div class="modal-footer ">
					<button type="submit" name="operacao" value="SPEDIDO"
						class="btn btn-danger btn-lg" style="width: 100%;">
						<span class="glyphicon glyphicon-ok-sign"></span> REPROVAR
					</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>