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
					</div>
					</li>
					</ul>
				</div>

				<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
	</nav>
	</header>
	<!-- Full Width Column -->

	<div class="content-wrapper">
		<div class="container">
			<br> <br> <br> <br>
			<!-- INíCIO do CONTEÚDO -->
			<div class="container">
				<form class="form-horizontal" role="form" method="post"
					action="SalvarCliente">
					<h2 style="text-align: center">Cadastro</h2>
					<div class="form-group">
						<label for="Primeiro Nome" class="col-sm-3 control-label">Nome</label>
						<div class="col-sm-9">
							<input type="text" id="txtNome" name="txtNome"
								placeholder="First Name" class="form-control" autofocus>
						</div>
					</div>

					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">Email* </label>
						<div class="col-sm-9">
							<input type="email" id="txtEmail" name="txtEmail"
								placeholder="Email" class="form-control" name="email">
						</div>
					</div>
					<div class="form-group">
						<label for="txtCpf" class="col-sm-3 control-label">CPF </label>
						<div class="col-sm-9">
							<input type="text" id="txtCpf" name="txtCpf" placeholder="CPF"
								class="form-control" name="CPF">
						</div>
					</div>
					<div class="form-group">
						<label for="senha" class="col-sm-3 control-label">senha*</label>
						<div class="col-sm-9">
							<input type="password" id="txtSenha" name="txtSenha"
								placeholder="Senha" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-3 control-label">Confirme
							a Senha</label>
						<div class="col-sm-9">
							<input type="password" id="password" placeholder="Password"
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="birthDate" class="col-sm-3 control-label">Data
							de Nascimento</label>
						<div class="col-sm-9">
							<input type="date" id="bday" name="bday" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="phoneNumber" class="col-sm-3 control-label">Numero
							de telefone</label>
						<div class="col-sm-9">
							<input type="text" id="phoneNumber" name="phoneNumber"
								placeholder="Ex (11)95555-0809" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Genero</label>
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-4">
									<label class="radio-inline"> <input type="radio"
										id="txtGender" name="txtGender" value="Mulher">Mulher
									</label>
								</div>
								<div class="col-sm-4">
									<label class="radio-inline"> <input type="radio"
										id="txtGender" name="txtGender" value="Homem">Homem
									</label>
								</div>
							</div>
						</div>
					</div>

					<c:choose>
						<c:when test="${resultadoSalvar == null}">
							<div></div>
						</c:when>
						<c:when
							test="${ resultadoSalvar != null && resultado.msg == null}">
							<div class="alert alert-success"
								style="margin-left: 294px; class: center; text-align: center; width: 74%;"
								role='alert'>
								<strong>Parabens</strong> Cliente cadastrado com sucesso!, clique <strong><a href="Login.jsp">Aqui</a></strong> para logar.
							</div>
						</c:when>

						<c:when
							test="${resultadoSalvar.msg !=null}">
							<div class="alert alert-danger"
								style="margin-left: 294px; class: center; text-align: center; width: 74%;"
								role="alert">${resultadoSalvar.msg}</div>
						</c:when>

					</c:choose>
					<button type="submit" style="margin-left: 600px"
						class="btn btn-primary" name="operacao" value="SALVAR"
						id="operacao">CADASTRAR</button>
				</form>
				<!-- /form -->
			</div>
			<!-- ./container -->

			<!-- FIM  do CONTEÚDO -->

			<!-- /.content -->
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
