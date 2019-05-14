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
	function mandaId(num) {
		document.getElementById("Idnovo").value = num;
		//document.getElementById("txtStatus").value = numm;
	}
</script>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-green layout-top-nav">

	<c:if test="${listaEnderecos == null}">
		<c:redirect url="ConsultarEndereco?operacao=CONSULTAREND" />
	</c:if>

	<%@include file="NavBar.jsp"%>

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
								data-target-id="cartoes"><i class="fa fa-bar-chart-o fa-fw"></i>Cadastro
									Cartões </a></li>
							<li><a href="http://www.jquery2dotnet.com"
								data-target-id="conCards"><i class="fa fa-bar-chart-o fa-fw"></i>Consulta
									Cartões </a></li>
							<li><a href="http://www.jquery2dotnet.com"
								data-target-id="conPedidos"><i
									class="fa fa-bar-chart-o fa-fw"></i>Consulta Pedidos </a></li>
									<li><a href="http://www.jquery2dotnet.com"
								data-target-id="conTrocas"><i
									class="fa fa-bar-chart-o fa-fw"></i>Trocas </a></li>
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
										<th>Saldo</th>
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
											<td>$ ${usuario.saldo}</td>
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
													name="txtLogradouro">
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
													class="form-control" id="txtCidade" name="txtCidade">
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
													id="txtCep" name="txtCep">
											</div>

											<div class="col-md-2 mb-2">
												<label>Número</label> <input type="text"
													class="form-control" id="txtNumero" name="txtNumero">
											</div>

										</div>


										<div class="row">
											<div class="form-group">
												<label>Descrição</label>
												<textarea rows="3" class="form-control" id="txtObservacao"
													name="txtObservacao"></textarea>
											</div>
										</div>
										<c:choose>
											<c:when test="${cadastroEnd == null}">
												<div></div>
											</c:when>
											<c:when test="${ cadastroEnd.msg == null}">
												<div class="alert alert-success"
													style="class: center; text-align: center; width: 97%;"
													role='alert'>
													<strong>Parabens</strong> Endereco cadastrado com sucesso!,
												</div>
											</c:when>

											<c:when test="${cadastroEnd.msg != null}">
												<div class="alert alert-danger"
													style="class: center; text-align: center; width: 97%;"
													role="alert">${cadastroEnd.msg}</div>
											</c:when>

										</c:choose>
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
												<button type="button" class="btn btn-primary btn-xs"
													id="btn" data-toggle="modal" data-target="#editEnd"
													onclick="mandaId(${endereco.id})">
													<span class="glyphicon glyphicon-pencil"></span>
												</button>
											</p></td>

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

					<div class="col-md-9 content admin-content" id="cartoes">
						<%@include file="CardsCadastro.jsp"%>
					</div>
					<div class="col-md-9 content admin-content" id="conCards">
						<%@include file="ConsultaCartao.jsp"%>
					</div>
					<div class="col-md-9 content admin-content" id="conPedidos">
						<%@include file="CliPedido.jsp"%>
					</div>
					<div class="col-md-9 content admin-content" id="conTrocas">
						<%@include file="ClienteTrocas.jsp"%>
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


<div class="modal fade" id="editEnd" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>

			</div>

			<form class="form-horizontal" role="form" method="post"
				action="DeleteEndereco">
				<div class="modal-body">

					<div class="row">
						<div class="col-sm-6 form-group">
							<label>Logradouro</label> <input type="text" class="form-control"
								id="txtLogradouro" name="txtLogradouro">
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
							<label>Bairro</label> <input type="text" class="form-control"
								id="txtBairro" name="txtBairro" />
						</div>
						<div class="col-sm-5 form-group">
							<label>Cidade</label> <input type="text" class="form-control"
								id="txtCidade" name="txtCidade">
						</div>
					</div>


					<div class="row">
						<div class="col-sm-3 form-group">
							<label>Estado</label> <select class="form-control" id="txtEstado"
								name="txtEstado">
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
						<div class="col-sm-6 form-group">
							<label>CEP</label> <input type="text" class="form-control"
								id="txtCep" name="txtCep">
						</div>

						<div class="col-sm-6 form-group">
							<label>Número</label> <input type="text" class="form-control"
								id="txtNumero" name="txtNumero">
						</div>
						<div class="col-md-2 mb-2">
							<input type="hidden" class="form-control" id="Idnovo"
								name="txtIdnovo">
						</div>

					</div>


					<div class="row">
						<div class="form-group">
							<label>Descrição</label>
							<textarea rows="3" class="form-control" id="txtObservacao"
								name="txtObservacao"></textarea>
						</div>
					</div>

					<c:choose>
						<c:when test="${resultadoAlterar == null}">
							<div></div>
						</c:when>
						<c:when test="${ resultadoAlterar.msg == null}">
							<div class="alert alert-success"
								style="class: center; text-align: center; width: 97%;"
								role='alert'>
								<strong>Parabens</strong> Endereco cadastrado com sucesso!,
							</div>
						</c:when>

						<c:when test="${resultadoAlterar.msg != null}">
							<div class="alert alert-danger"
								style="class: center; text-align: center; width: 97%;"
								role="alert">${resultadoAlterar.msg}</div>
						</c:when>

					</c:choose>
					<button type="submit" class="btn btn-primary btn-lg"
						name="operacao" value="DELEND" id="operacao" style="width: 95%;">
						<span class="glyphicon glyphicon-ok-sign"></span> Cadastrar
					</button>
				</div>
			</form>
			<!-- /form -->
		</div>
	</div>
</div>
</html>
