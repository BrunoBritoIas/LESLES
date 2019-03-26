<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
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



<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>
<script>
	
	 $(document).ready(function() {

		var navItems = $('.admin-menu li > a');
		var navListItems = $('.admin-menu li');
		var allWells = $('.admin-content');
		var allWellsExceptFirst = $('.admin-content:not(:first)');

		allWellsExceptFirst.hide();
		navItems.click(function(e) {
			e.preventDefault();
			navListItems.removeClass('active');
			$(this).closest('li').addClass('active');

			allWells.hide();
			var target = $(this).attr('data-target-id');
			$('#' + target).show();
		});
	});
</script>
<style>
textarea {
	resize: none;
	margin-left: 16px;
}

.form-control {
	width: 95%;
}

.navbar {
	background-color: #00a65a;
}
</style>
<script>
	function mandaId(num, numm) {
		document.getElementById("txtIdnovo").value = num;
		document.getElementById("txtStatus").value = numm;
	}
</script>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-green layout-top-nav">

	<c:if test="${listaEnderecos == null}">
		<c:redirect url="ConsultarEndereco?operacao=CONSULTAREND" />
	</c:if>

	<div class="wrapper">
		<nav class="navbar navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<a href="home.html" class="navbar-brand"><b>SupliMais</b></a>
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar-collapse">
						<i class="fa fa-bars"></i>
					</button>
				</div>

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

				<!-- /.navbar-collapse -->
			</div>
		</nav>
		<div class="content-wrapper">
			<div class="container">

				<!-- INíCIO do CONTEÚDO -->
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="container">
					<div class="row">
						<div class="col-md-3">
							<ul class="nav nav-pills nav-stacked admin-menu">
								<li><a href="#" data-target-id="home"><i
										class="fa fa-home fa-fw"></i>Perfil</a></li>
								<li><a href="http://www.jquery2dotnet.com"
									data-target-id="widgets"><i class="fa fa-list-alt fa-fw"></i>
										Cadastro Endereço</a></li>
								<li class="active"><a href="" id="consultaEnd"
									data-target-id="pages"><i class="fa fa-file-o fa-fw"></i>Consulta
										Endereços</a></li>
								<li><a href="http://www.jquery2dotnet.com"
									data-target-id="charts"><i class="fa fa-bar-chart-o fa-fw"></i>Compras
										Efetuadas</a></li>
							</ul>
						</div>
						<!-- HOME -->
						<div class="col-md-9 content admin-content" id="home">
							<form method="post" action="SalvarCliente">

								<table id="datatable" class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<thead>
										<tr>
											<th>Nome</th>
											<th>CPF</th>
											<th>Genero</th>
											<th>E-mail</th>
											<th>Nascimento</th>
											<th>Telefone</th>
											<th>Editar</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${usuario !=null}">
											<tr>
												<td>${usuario.nome}</td>
												<td>${usuario.cpf}</td>
												<td>${usuario.genero}</td>
												<td>${usuario.email}</td>
												<td>${usuario.dtNasc}</td>
												<td>${usuario.telefone}</td>

												<td><p data-placement="top" data-toggle="tooltip"
														title="Edit">
														<button type="button" class="btn btn-primary btn-xs"
															data-title="Edit" data-toggle="modal" data-target="#edit">
															<span class="glyphicon glyphicon-pencil"></span>
														</button>
													</p></td>
											</tr>
										</c:if>
									</tbody>
								</table>
							</form>
						</div>
						<div class="col-md-9 well admin-content" id="widgets">



							<div class="container">
								<div class="row">
									<form method="post" action="SalvarEndereco">
										<div class="col-sm-9">

											<div class="row">
												<div class="col-sm-6 form-group">
													<label>Logradouro</label> <input type="text"
														class="form-control" id="txtLogradouro"
														name="txtLogradouro" required>
												</div>
												<label>Tipo Logradouro</label>
												<div class="col-sm-6 form-group">
													<select class="form-control" style="width: 189px;">
														<option>Selecione</option>
														<option>Casa</option>
														<option>Apartamento</option>
														<option>Sitio</option>
													</select>
												</div>
												<div class="col-sm-6 form-group">
													<label>Bairro</label> <input type="text"
														class="form-control" id="txtBairro" name="txtBairro" />
												</div>
												<div class="col-sm-5 form-group">
													<label>Cidade</label> <input type="text"
														class="form-control" id="txtCidade" name="txtCidade"
														required>
												</div>
											</div>


											<div class="row">
												<div class="col-sm-3 form-group">
													<label>Estado</label> <select class="form-control"
														id="txtEstado" name="txtEstado">
														<option value="AC">Acre</option>
														<option value="AL">Alagoas</option>
														<option value="AP">Amapa</option>
														<option value="AM">Amazonas</option>
														<option value="BA">Bahia</option>
														<option value="CE">Ceara</option>
														<option value="DF">Distrito Federal</option>
														<option value="ES">Espirito Santo</option>
														<option value="GO">Goias</option>
														<option value="MA">Maranhão</option>
														<option value="MT">Mato Grosso</option>
														<option value="MS">Mato Grosso do Sul</option>
														<option value="MG">Minas Gerais</option>
														<option value="PA">Para</option>
														<option value="PB">Paraiba</option>
														<option value="PR">Parana</option>
														<option value="PE">Pernambuco</option>
														<option value="PI">Piau</option>
														<option value="RJ">Rio de Janeiro</option>
														<option value="RN">Rio Grande do Norte</option>
														<option value="RS">Rio Grande do Sul</option>
														<option value="RO">Rondonia</option>
														<option value="RR">Roraima</option>
														<option value="SC">Santa Catarina</option>
														<option value="SP">Sao Paulo</option>
														<option value="SE">Sergipe</option>
														<option value="TO">Tocantins</option>
													</select>
												</div>
												<div class="col-sm-3 form-group">
													<label>CEP</label> <input type="text" class="form-control"
														id="txtCep" name="txtCep" required>
												</div>

												<div class="col-md-2 mb-2">
													<label>Número</label> <input type="text"
														class="form-control" id="txtNumero" name="txtNumero"
														required>
												</div>

											</div>


											<div class="row">
												<div class="form-group">
													<label>Descrição</label>
													<textarea rows="3" class="form-control" id="txtObservacao"
														name="txtObservacao"></textarea>
												</div>
											</div>

											<button type="submit" class="btn btn-primary btn-lg"
												name="operacao" value="SALVAREND" id="operacao"
												style="width: 95%;">
												<span class="glyphicon glyphicon-ok-sign"></span> Cadastrar
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>

						<div class="col-md-9 well admin-content" id="pages">
							<table id="datatable" class="table table-striped table-bordered"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>logradouro</th>
										<th>bairro</th>
										<th>CEP</th>
										<th>cidade</th>
										<th>estado</th>
										<th>obs</th>
										<th>Deletar</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listaEnderecos}" var="endereco">
										<tr>
											<td>${endereco.logradouro}</td>
											<td>${endereco.bairro}</td>
											<td>${endereco.cep}</td>
											<td>${endereco.cidade}</td>
											<td>${endereco.estado}</td>
											<td>${endereco.obs}</td>
											<td><p data-placement="top" data-toggle="tooltip"
													title="Edit">
												<form action="DeleteEndereco">
													<input type="hidden" id="txtIdnovo" name="txtIdnovo"
														value="${endereco.id}">
													<button type="submit" class="btn btn-danger btn-xs"
														id="operacao" name="operacao" value="DELEND">
														<span class="	glyphicon glyphicon-remove"></span>
													</button>
												</form></td>

										</tr>
									</c:forEach>

								</tbody>
							</table>
							<form action="ConsultarEndereco">
								<button type="submit" class="btn btn-primary btn-lg"
									name="operacao" value=CONSULTAREND id="operacao"
									style="width: 95%;">
									<span class="glyphicon glyphicon-ok-sign"></span> consultar
								</button>
							</form>

						</div>

					</div>

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
		<c:if test="${uiui != null}">
	</c:if>
</body>

<div class="modal fade" id="edit" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>

			</div>
			<form method="post" action="AlterarCliente">
				<div class="modal-body">

					<div class="form-group">
						<input class="form-control " type="text" placeholder="Nome"
							id="txtNome" name="txtNome">
					</div>

					<div class="form-group">
						<input class="form-control " type="password" placeholder="Senha"
							id="txtSenha" name="txtSenha">
					</div>

					<div class="form-group">
						<input class="form-control " type="text" placeholder="CPF"
							id="txtCpf" name="txtCpf">
					</div>
					Telefone:
					<div class="form-group">
						<input type="text" id="phoneNumber" name="phoneNumber"
							placeholder="Ex (11)95555-0809" class="form-control">
					</div>

					<div class="form-group">
						<input class="form-control " type="text" placeholder="E-mail"
							id="txtEmail" name="txtEmail" value="">
					</div>
					Nascimento
					<div class="form-group">
						<input class="form-control " type="date" placeholder="Niver"
							id="bday" name="bday">
					</div>
					<div class="form-group">
						<input class="form-control " type="hidden" placeholder="Niver"
							id="txtIdnovo" name="txtIdnovo" value="${usuario.idCliente}">
					</div>

					<div class="form-group">
						Genero <select id="txtGender" name="txtGender">
							<option value="masculino">Masculino</option>
							<option value="feminino">Feminino</option>
							<option value="outros">Outros</option>
						</select></br>
					</div>


				</div>
				<div class="modal-footer ">
					<button type="submit" class="btn btn-primary btn-lg"
						style="width: 100%;" name="operacao" id="operacao" value="ALTERAR">
						<span class="glyphicon glyphicon-ok-sign"></span> ALTERAR
					</button>
				</div>
			</form>

		</div>

		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
</html>
