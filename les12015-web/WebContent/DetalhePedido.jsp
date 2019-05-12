<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Supplimais</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->

<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="assets/css/skins/_all-skins.min.css">

<link rel="stylesheet" href="assets/css/meat.css">
<script>
	function alerta(id) {

		$("input[type='checkbox']:checked").each(function() {
			alert(this.id)
		});

		var qtdCards = $("input[type='checkbox']:checked").size();
		alert(qtdCards)
	}
</script>

</head>
<style>
a {
	color: #014BB0;
	text-decoration: none;
}

.navbar {
	background-color: #00a65a;
}
</style>
<body class="hold-transition skin-green layout-top-nav">
	<div class="wrapper">
		<nav class="navbar navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<a href="Home.jsp" class="navbar-brand"><b>SupliMais</b></a>
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar-collapse">
						<i class="fa fa-bars"></i>
					</button>
				</div>

				<div class="collapse navbar-collapse pull-right"
					id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="main-restaurants.html"></a></li>
						<c:choose>
							<c:when test="${usuario !=null}">
								<li><a href="CliAdmin.jsp">Perfil </a></li>
								<li><a href="#">Logout</a></li>
							</c:when>
							<c:when test="${usuario ==null}">
								<li><a href="Login.jsp">Login</a></li>
							</c:when>

						</c:choose>
						<li>
							<form class="navbar-form" role="search">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Search"
										name="q">
									<div class="input-group-btn">
										<button class="btn btn-default" type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
							</form>
						</li>
					</ul>
				</div>

				<!-- /.navbar-collapse -->
			</div>
		</nav>
	</div>
	<!-- Full Width Column -->
	<div class="content-wrapper">
		<div class="container">


			<!-- INÍCIO DO CONTEÚDO -->
			<section class="content-header">
				<h1></h1>
			</section>

			<%-- <c:forEach items="${detalhePed}" var="pedido"> --%>
			<section class="content">
				<c:forEach items="${detalhePed.unidade}" var="pedido">
					<div class="row">
						<div class="col-xs-12">
							<div class="box box-solid">
								<div class="box-header with-border">
									<form method="get" action="finalizaCompra">

										<h3 class="box-title pull-right">

											<input type="number" class="pull-right" value="1" placeholder="Quantidade "id="qtdProd" name="qtdProd" style="width: 145px;" min="1" max="${pedido.quantidade}" />
											<input type="hidden" class="pull-right" id="idProd" name="idProd" value="${pedido.idSup}"/> <label
												class="control control-checkbox"><button type="submit"  value="UNICHANGE" name="operacao">Devolução <i class="fa fa-refresh"></i></button></label>
										</h3>										
									</form>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<div class="col-sm-3 col-xs-12">
										<img class="box-img-detail"
											src="assets/img/restaurants/whey.png" />
									</div>

									<dl class="col-sm-9 col-xs-12">
										<dt>Quantidade</dt>
										<dd>${pedido.quantidade}</dd>
										<dt>Peso</dt>
										<dd>${pedido.sup.peso}gr</dd>
										<dt>Marca</dt>
										<dd>${pedido.sup.marca}</dd>
										<dt>Preço</dt>
										<dd>
											<span class="menu-item-info-box-price">R$
												${pedido.sup.preco}</span>
										</dd>
									</dl>
								</div>
								<!-- /.box-body -->
							</div>
						</div>
						<div></div>

					</div>

				</c:forEach>


				<h3 style="text-align: center;">PAGAMENTO:</h3>
				<c:forEach items="${detalhePed.cardPed}" var="card">
					<div class="row">
						<!--INÍCIO DO MENU -->
						<div class="col-xs-12">

							<div class="menu-item-info-box">
								<div class="menu-item-info-box-content">
									<table style="width: 100%">
										<tr>
											<th>Numero do Cartão</th>
											<th>Bandeira</th>
											<th>Numero de Parcelas</th>
											<th>Valor da parcela</th>
											<th>Total a Pagar</th>

										</tr>
										<tr>
											<td>${card.numCartao}</td>
											<td>${card.bandeira}</td>
											<td>${card.numParcela}</td>
											<td>${card.vlrParcela}</td>
											<td>${card.totalParcela}</td>
									</table>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->



						</div>

						<!-- FIM DO MENU -->
					</div>
				</c:forEach>
				<h3 style="text-align: center;">VALORES:</h3>
				<div class="row">
					<!--INÍCIO DO MENU -->
					<div class="col-xs-12">

						<div class="menu-item-info-box">
							<div class="menu-item-info-box-content">
								<table style="width: 100%">
									<tr>
										<th>Preço Produtos</th>
										<th>Preço Frete</th>
										<th>Preço Final</th>
										<th>Quantidade Total</th>
										<th>Status</th>
									</tr>
									<tr>
										<td>${detalhePed.precoFinal}</td>
										<td>${detalhePed.precoFrete}</td>
										<td>${detalhePed.precoFinal + detalhePed.precoFrete}</td>
										<td>${detalhePed.qtdItens}</td>
										<td>${detalhePed.status}</td>
								</table>
							</div>
							<!-- /.info-box-content -->
						</div>




					</div>

					<!-- FIM DO MENU -->
				</div>
				<h4 style="text-align: center;">Endereço de Entrega:</h4>
				<div class="row">
					<!--INÍCIO DO MENU -->
					<div class="col-xs-12">

						<div class="menu-item-info-box">
							<div class="menu-item-info-box-content">
								<table style="width: 100%">
									<tr>
										<th>Estado</th>
										<th>Cidade</th>
										<th>Bairro</th>
										<th>Rua</th>
										<th>Numero</th>
									</tr>
									<tr>
										<td>${detalhePed.endereco.estado}</td>
										<td>${detalhePed.endereco.cidade}</td>
										<td>${detalhePed.endereco.bairro}</td>
										<td>${detalhePed.endereco.logradouro}</td>
										<td>${detalhePed.endereco.numero}</td>
								</table>
							</div>
							<!-- /.info-box-content -->
						</div>




					</div>

					<!-- FIM DO MENU -->
				</div>
		</div>
		<%-- </c:forEach> --%>
		<div class="text-center">
			<form method="get" action="finalizaCompra">
				<c:forEach items="${detalhePed.unidade}" var="pedi">
					<input type="text" id="${pedi.idSup}" name="">
				</c:forEach>

				<button class="btn btn btn-success" type="submit" id="operacao"
					value="addCarrinho" name="operacao">Adicionar</button>
			</form>
		</div>

		<!-- FIM DO CONTEÚDO -->
	</div>
	<!-- /.container -->
	<!-- /.content-wrapper -->
	<footer class="main-footer">
		<div class="container">
			<div class="pull-right hidden-xs">
				<b>Version</b> 2.3.7
			</div>
			<strong>Copyright &copy; 2015-2019 <a>Suplimais</a>.
			</strong> All rights reserved.
		</div>
		<!-- /.container -->
	</footer>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.2.3 -->
	<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="assets/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="assets/js/demo.js"></script>
</body>
</html>
