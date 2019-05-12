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

<script src="plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
</head>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-green layout-top-nav">
	<%@include file="NavBar.jsp"%>
	<!-- Full Width Column -->
	<div class="content-wrapper">
		<div class="container">


			<!-- INÍCIO DO CONTEÚDO -->
			<section class="content-header">
				<h1></h1>
			</section>

			<c:forEach items="${supDetail}" var="suplemento">
				<section class="content">

					<div class="row">
						<div class="col-xs-12">
							<div class="box box-solid">
								<div class="box-header with-border">
									<i class="fa fa-home"></i>

									<h3 class="box-title">${suplemento.nome}</h3>
									<span class="pull-right"><i class="fa fa-star"></i> 4.5</span>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<div class="col-sm-3 col-xs-12">
										<img class="box-img-detail"
											src="assets/img/restaurants/whey.png" />
									</div>

									<dl class="col-sm-9 col-xs-12">
										<dt>Categoria</dt>
										<dd>${suplemento.categoria}</dd>
										<dt>Peso</dt>
										<dd>${suplemento.peso}gr</dd>
										<dt>Marca</dt>
										<dd>${suplemento.marca}</dd>
										<dt>Preço</dt>
										<dd>
											<span class="menu-item-info-box-price">R$${suplemento.preco}</span>
										</dd>
									</dl>
								</div>
								<!-- /.box-body -->
							</div>
						</div>
						<div></div>

					</div>

					<h3 style="text-align: center;">MODO DE USO:</h3>
					<div class="row">
						<!--INÍCIO DO MENU -->
						<div class="col-xs-12">

							<div class="menu-item-info-box">
								<div class="menu-item-info-box-content">
									<span class="menu-item-info-box-text">Descrição</span> <span
										class="menu-item-info-box-detail">${suplemento.descricao}</span>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->



						</div>

						<!-- FIM DO MENU -->
					</div>
					<h3 style="text-align: center;">TABELA NUTRICIONAL:</h3>
					<div class="row">
						<!--INÍCIO DO MENU -->
						<div class="col-xs-12">

							<div class="menu-item-info-box">
								<div class="menu-item-info-box-content">
									<table style="width: 100%">
										<tr>
											<th>Proteina</th>
											<th>Carboidrato</th>
											<th>Gordura</th>
											<th>Calorias</th>
										</tr>
										<tr>
											<td>${suplemento.proteina}</td>
											<td>${suplemento.carboidratos}</td>
											<td>${suplemento.gordura}</td>
											<td>${suplemento.calorias}</td>
									</table>
								</div>
								<!-- /.info-box-content -->
							</div>
							<!-- /.info-box -->



						</div>

						<!-- FIM DO MENU -->
					</div>
			</c:forEach>
			<div class="text-center">
				<form method="post" action="adicionarCarrinho">
					<button class="btn btn btn-success" type="submit" id="operacao" value="addCarrinho" name="operacao">Adicionar</button>
				</form>
			</div>
			<!-- FIM DO CONTEÚDO -->
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
</body>
</html>
