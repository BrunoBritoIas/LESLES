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
<title>SupliMais</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->

<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="assets/css/skins/_all-skins.min.css">

<style>
.navbar {
	background-color: #00a65a;
}
</style>



<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-green layout-top-nav">
	<%@include file="NavBar.jsp"%>
	<div class="content-wrapper">
		<div class="container">

			<!--INICIO DO CONTEUDO-->
			<section class="content-header"></section>

			<section class="content">
				<section class="invoice">

					<form>
						<div class="row">
							<div class="col-xs-12">
								<h2 class="page-header">
									<i class="fa fa-shopping-cart"></i> Finalize o seu pedido
								</h2>
							</div>
						</div>

						<div class="row invoice-info">
							<div class="col-xs-12">
								<p class="lead">Endereço de Entrega:</p>
							</div>
							<div style="padding-left: 540px">Cupom de desconto:</div>
							<div class="col-sm-6 col-xs-12">
								<div>
									<select class="form-control" id="exampleFormControlSelect1">
										<option>Rua Bacana que legal mano</option>
										<option>Av. melancia loca</option>
										<option>Rua Maneirinha</option>
									</select>
								</div>
							</div>
							<div class="col-sm-2 col-xs-6">
								<div class="form-group">
									<label class="control-label sr-only" for="inputSuccess"><i
										class="fa fa-check"></i> Número</label> <input type="text"
										class="form-control" placeholder=" EX. Arnold10">
									<!-- <input type="text" class="form-control" id="inputSuccess" placeholder="Número"> -->
									<!-- <span class="help-block"><i class="fa fa-check"></i> Ok</span> -->
								</div>
							</div>
						</div>
						<!-- /.row -->

						<!-- Table row -->
						<div class="row">
							<div class="col-xs-12">
								<p class="lead">Itens do Pedido:</p>
							</div>

							<div class="col-xs-12 table-responsive">

								<table class="table table-striped">
									<thead>
										<tr>
											<th class="text-center">Quantidade</th>
											<th>Item</th>
											<th>Marca</th>
											<th class="text-right">Subtotal</th>
											<th class="text-right"></th>
										</tr>
									</thead>
									<c:if test="${carrinho !=null }">
									
										<tbody>
											<c:forEach items="${itens}" var="car">
												<tr>
													<td class="text-center"><a class="btn btn-sm">
													<a href="adicionarCarrinho?operacao=QUANTIDADE&txtqtd=${car.getQuantidade() -1 }&id=${car.getSup().getId()}"><button type="button" class="fa fa-minus"></button></a>
													
													</a> ${car.getQuantidade()} <a href="adicionarCarrinho?operacao=QUANTIDADE&txtqtd=${car.getQuantidade() + 1}&id=${car.getSup().getId()}"><button type="button" class="fa fa-plus"></button></a></td>
													<td>${car.getSup().getNome()}</td>

													<td>${car.getSup().getMarca()}</td>
													<td class="text-right">${car.getSup().getPreco().toString()}</td>
													<td class="text-right"><a  href="adicionarCarrinho?operacao=REMOVER&id=${car.getSup().getId()}" class="btn btn-sm danger"><i
															class="fa fa-remove"></i></a></td>
												</tr>
											</c:forEach>
										</tbody>
									</c:if>
								</table>

							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->

						<div class="row">
							<!-- accepted payments column -->
							<div class="col-sm-6 col-xs-12">
								<p class="lead">
									<b>Cartões de Credito:</b>
								</p>

								<div class="form-group">
									<div></div>
									<div>
										<label>
											<div>
												<select class="form-control" id="exampleFormControlSelect1">
													<option>Master Card</option>
													<option>Visa</option>
													<option>BitchCoin</option>
												</select>
											</div>
										</label>
									</div>

								</div>

							</div>
							<!-- /.col -->
							<div class="col-sm-6 col-xs-12">
								<p class="lead">Frete e Total:</p>

								<div class="table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<th style="width: 50%">Itens:</th>
												<td class="text-right">R$ 55,00</td>
											</tr>
											<tr>
												<th>Frete:</th>
												<td class="text-right">R$ 8,00</td>
											</tr>
											<tr>
												<th>Descontos:</th>
												<td class="text-right">R$ -14,00</td>
											</tr>
											<tr>
												<th>Total:</th>
												<td class="text-right">R$ 49,00</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- /.col -->
						</div>

					</form>

					<div class="row">
						<div class="col-xs-12">
							<a href="order-summary.html" class="btn btn-success pull-right"><i
								class="fa fa-credit-card"></i> Concluir Pedido </a>
						</div>
					</div>

				</section>
			</section>
			<!-- FIM DO CONTEUDO-->
		</div>
		<!-- /.container -->
	</div>
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

	<script src="plugins/iCheck/icheck.min.js"></script>

</body>
</html>