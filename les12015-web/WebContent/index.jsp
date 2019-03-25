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
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="assets/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="assets/css/skins/_all-skins.min.css">

<link rel="stylesheet" href="assets/css/meat.css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<style>
.arruma-img {
	height: 120px;
	height: 120px;
}
</style>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-green layout-top-nav">

	<c:if test="${listaSuplementos ==null}">
		<c:redirect url="SalvarProduto?operacao=CONSULTARHOME" />
	</c:if>

	<div class="wrapper">

		<header class="main-header">
			<nav class="navbar navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<a href="home.html" class="navbar-brand"><b>SupliMais</b></a>
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar-collapse">
							<i class="fa fa-bars"></i>
						</button>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse pull-right"
						id="navbar-collapse">
						<ul class="nav navbar-nav">
							<li><a href="main-restaurants.html"> <span
									class="sr-only">(current)</span></a></li>
							<li><a href="main-restaurants.html">Categorias <span
									class="sr-only">(current)</span></a></li>
							<li><a href="main-restaurants.html">Marcas <span
									class="sr-only">(current)</span></a></li>
							<li><a href="#">Login</a></li>
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

				</div>

				<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->

	<!-- Full Width Column -->
	<div class="content-wrapper">
		<div class="container">

			<!-- INíCIO do CONTEÚDO -->
			<section class="content-header">
				<h1></h1>
			</section>

			<br> <br> <br>


			<form method="post" action="SalvarProduto">

				<div class="row">
					<div class="col-sm-6 form-group">
						<label>Nome</label> <input type="text" id="txtNome" name="txtNome"
							class="form-control">
					</div>
					<div class="col-sm-6 form-group">
						<label>Marca</label> <input type="text" id="txtMarca"
							name="txtMarca" class="form-control">
					</div>

				</div>
				<div>
					<label>Categoria</label> <label class="checkbox-inline"><input
						type="radio" id="txtCategoria" name="txtCategoria"
						value="Massa Magra">Massa Magra</label> <label
						class="checkbox-inline"><input type="radio"
						id="txtCategoria" name="txtCategoria" value="Emagrecimento">Emagrecimento</label>
					<label class="checkbox-inline"><input type="radio"
						id="txtCategoria" name="txtCategoria" value="Vegano">Vegano</label>
				</div>
				<input type="submit" name="operacao" value="CONSULTARHOME"
					id="operacao"> <br> <br> <br>

				<div class="row">
					<c:forEach items="${listaSuplementos}" var="suplemento">
						<div class="col-sm-6 col-xs-12">
							<a href="detail-restaurant.html">
								<div class="place-info-box">
									<span class="place-info-box-icon"><img
										src="assets/img/restaurants/bcaa.png" class="arruma-img" /></span>

									<div class="place-info-box-content">
										<span class="place-info-box-text">${suplemento.nome}</span> <span
											class="place-info-box-star"><i class="fa fa-star"></i>
											4.8</span> <span class="place-info-box-detail">${suplemento.marca}</span>
										<span class="place-info-box-detail">${suplemento.peso}gr</span>
										<span class="menu-item-info-box-price">R$
											${suplemento.preco}</span>
									</div>
									<!-- /.info-box-content -->
								</div>
							</a>
							<!-- /.info-box -->
						</div>
					</c:forEach>

				</div>

				<!-- FIM  do CONTEÚDO -->

			</form>
		</div>

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
