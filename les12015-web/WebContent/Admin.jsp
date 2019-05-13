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
	// $('.nav-pills a').click(function(){
	// var content = $(this).attr('data-id');
	//$('.content .panel').removeClass('is-active');
	//$('.content .panel[id="'+content+'"]').addClass('is-active');
	//});
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
.navbar {
	background-color: #00a65a;
}
</style>
<script>

function mandaId(num, numm){
	 document.getElementById("txtIdnovo").value = num;
	 document.getElementById("txtStatus").value = numm;
}

</script>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-green layout-top-nav">

	<%@include file="NavBar.jsp"%>
	<div class="content-wrapper">
		<div class="container">

			<!-- INíCIO do CONTEÚDO -->
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<ul class="nav nav-pills nav-stacked admin-menu">
							<li class="active"><a href="#" data-target-id="home"><i
									class="fa fa-home fa-fw"></i>Usuarios</a></li>
							<li><a href="http://www.jquery2dotnet.com"
								data-target-id="widgets"><i class="fa fa-list-alt fa-fw"></i>Produtos</a></li>
							<li><a href="http://www.jquery2dotnet.com"
								data-target-id="pages"><i class="fa fa-file-o fa-fw"></i>Produtos</a></li>
							<li><a href="http://www.jquery2dotnet.com"
								data-target-id="charts"><i class="fa fa-tasks fa-fw"></i>Cupom</a></li>
							<li><a href="http://www.jquery2dotnet.com"
								data-target-id="table"><i class="fa fa-table fa-fw"></i>Pedidos</a></li>
							<li><a href="http://www.jquery2dotnet.com"
								data-target-id="forms"><i class="fa fa-table fa-fw"></i>Trocas</a></li>
							<li><a href="http://www.jquery2dotnet.com"
								data-target-id="calender"><i class="fa fa-charts fa-fw"></i>Estatisticas</a></li>
						</ul>
					</div>
					<!-- HOME -->
					<div class="col-md-9 content admin-content" id="home">
						<form method="post" action="SalvarCliente">
							Nome<input type="text" id="txtNome" name="txtNome" /> CPF<input
								type="text" id="txtCpf" name="txtCpf" /> <input type="submit"
								name="operacao" value="CONSULTAR" id="operacao"> <br>
							<br> <br>
							<table id="datatable" class="table table-striped table-bordered"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Nome</th>
										<th>CPF</th>
										<th>Genero</th>
										<th>E-mail</th>
										<th>Nascimento</th>
										<th>Endereço</th>
										<th>Editar</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listaCliente}" var="cliente">
										<tr>
											<td>${cliente.nome}</td>
											<td>${cliente.cpf}</td>
											<td>${cliente.genero}</td>
											<td>${cliente.email}</td>
											<td>${cliente.dtNasc}</td>
											<td><input type="button" name="operacao"
												value="Endereço" id="operacao"></td>
											<td><p data-placement="top" data-toggle="tooltip"
													title="Edit">
													<button type="button" class="btn btn-primary btn-xs"
														data-title="Edit" data-toggle="modal" data-target="#edit">
														<span class="glyphicon glyphicon-pencil"></span>
													</button>
												</p></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>
					</div>
					<div class="col-md-9 well admin-content" id="widgets">

						<form method="post" action="SalvarProduto">

							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Nome</label> <input type="text" id="txtNome"
										name="txtNome" class="form-control">
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
							<input type="submit" name="operacao" value="CONSULTARSUP"
								id="operacao"> <br> <br> <br>
							<table id="datatable" class="table table-striped table-bordered"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Marca</th>
										<th>Peso</th>
										<th>Categoria</th>
										<th>Rating</th>
										<th>Validade</th>
										<th>Quantidade</th>
										<th>Status</th>
										<th>Alterar</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listaSuplementos}" var="suplemento">
										<tr>
											<td>${suplemento.nome}</td>
											<td>${suplemento.marca}</td>
											<td>${suplemento.peso}</td>
											<td>${suplemento.categoria}</td>
											<td>${suplemento.rating}</td>
											<td>${suplemento.validade}</td>
											<td>${suplemento.quantidade}</td>
											<td>${suplemento.status}</td>
											<td><p data-placement="top" data-toggle="tooltip"
													title="Edit">
													<button type="button" class="btn btn-primary btn-xs"
														id="${suplemento.status}" data-toggle="modal"
														data-target="#editProd"
														onclick="mandaId(${suplemento.id},this.id)">
														<span class="glyphicon glyphicon-pencil"></span>
													</button>
												</p></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>


					</div>

					<div class="col-md-9 well admin-content" id="pages"
						style="padding: 0px">
						<div class="container">
							<div class="row">
								<form method="post" action="SalvarProduto">
									<div class="col-sm-9">
										<div class="row">
											<div class="col-sm-4 form-group">
												<label>Nome</label> <input type="text" id="txtNome"
													name="txtNome" class="form-control">
											</div>
											<div class="col-sm-4 form-group">
												<label>Marca</label> <input type="text" id="txtMarca"
													name="txtMarca" class="form-control">
											</div>
											
											<div class="col-sm-2 form-group">
												<label>Quantidade</label> <input type="number" id="txtQuantidade"
													name="txtQuantidade" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label>Descrição</label>
											<textarea rows="3" class="form-control" id="txtDesc"
												name="txtDesc"></textarea>
										</div>
										<div class="row">
											<div class="col-sm-3 form-group">
												<label>Peso (Gr)</label> <input type="number"
													class="form-control" id="txtPeso" name="txtPeso">
											</div>
											<div class="col-sm-3 form-group">
												<label>Preço</label> <input type="number"
													class="form-control" id="txtPreco" name="txtPreco">
											</div>
											<div class="col-sm-3 form-group">
												<label>Categoria</label>
												<div>
													<select class="form-control" id="exampleFormControlSelect1"
														id="txtCategoria" name="txtCategoria">
														<option>Massa Magra</option>
														<option>Emagrecimento</option>
														<option>Vegano</option>
													</select>
												</div>
											</div>
											<div class="col-sm-3 form-group">
												<label>Validade</label><input type="date" name="txtValidade"
													id="txtValidade">
											</div>
										</div>
										<div class="row">
											<div class="col-sm-3 form-group">
												<label>Proteina</label> <input type="number"
													class="form-control" id="txtProt" name="txtProt">
											</div>
											<div class="col-sm-3 form-group">
												<label>Carboidratos</label> <input type="number"
													class="form-control" id="txtCarb" name="txtCarb">
											</div>
											<div class="col-sm-3 form-group">
												<label>Gordura</label> <input type="number"
													class="form-control" id="txtFat" name="txtFat">
											</div>
											<div class="col-sm-3 form-group">
												<label>Calorias</label> <input type="number"
													class="form-control" id="txtCal" name="txtCal">
											</div>
										</div>
										<%
											Resultado result = (Resultado) session.getAttribute("resultado");
											if (result != null) {
												if ("Produto cadastrado com sucesso!".equals(result.getMsg())) {
													StringBuilder st = new StringBuilder();
													st.append("<div class='alert alert-success' role='alert'>");
													st.append("<strong>Parabéns</strong> Produto cadastrado com sucesso!.");
													st.append("</div>");
													out.print(st.toString());

												} else {
													StringBuilder st = new StringBuilder();
													st.append("<div class='alert alert-danger' role='alert'>");
													st.append(result.getMsg());
													st.append("</div>");
													out.print(st.toString());
												}

											}
										%>
										<div class="text-center">
											<input type="submit" name="operacao" value="SALVARSUP"
												id="operacao">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="col-md-9 well admin-content" id="charts">
						<%@include file="Cupom.jsp"%>
					</div>
					<div class="col-md-9 well admin-content" id="table">
							<%@include file="PedidosAdmin.jsp"%>
					</div>
					<div class="col-md-9 well admin-content" id="forms">
						<%@include file="ListaTrocas.jsp"%>
					</div>
					<div class="col-md-9 well admin-content" id="calender">
						Calender</div>
				</div>
			</div>

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

<div class="modal fade" id="edit" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<form method="post" action="SalvarCliente"></form>
			</div>
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

				<div class="form-group">
					<input class="form-control " type="text" placeholder="E-mail"
						id="txtEmail" name="txtEmail">
				</div>
				Nascimento
				<div class="form-group">
					<input class="form-control " type="date" placeholder="Niver"
						id="bday" name="bday">
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
				<button type="button" class="btn btn-primary btn-lg"
					style="width: 100%;">
					<span class="glyphicon glyphicon-ok-sign"></span> ALTERAR
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div class="modal fade" id="editProd" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 900px; margin-left: -100px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<div class="container">
					<div class="row">
						<form method="post" action="SalvarProduto">
							<div class="col-sm-9">
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>Nome</label> <input type="text" id="txtNome"
											name="txtNome" class="form-control">
									</div>
									<div class="col-sm-4 form-group">
										<label>Marca</label> <input type="text" id="txtMarca"
											name="txtMarca" class="form-control">
									</div>
									<div class="col-sm-3 form-group">
										<label>Status</label> <select class="form-control"
											id="txtStatus" name="txtStatus">
											<option>ATIVO</option>
											<option>INATIVO</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label>Descrição</label>
									<textarea rows="3" class="form-control" id="txtDesc"
										name="txtDesc"></textarea>
								</div>
								<div class="row">
									<div class="col-sm-3 form-group">
										<label>Peso</label> <input type="number" class="form-control"
											id="txtPeso" name="txtPeso">
									</div>
									<div class="col-sm-3 form-group">
										<label>Preço</label> <input type="number" class="form-control"
											id="txtPreco" name="txtPreco">
									</div>
									<div class="col-sm-3 form-group">
										<label>Categoria</label>
										<div>
											<select class="form-control" id="exampleFormControlSelect1"
												id="txtCategoria" name="txtCategoria">
												<option>Massa Magra</option>
												<option>Emagrecimento</option>
												<option>Vegano</option>
											</select>
										</div>
									</div>
									<div class="col-sm-3 form-group">
										<label>Validade</label><input type="date" name="txtValidade"
											id="txtValidade">
									</div>
								</div>
								<div class="row">
									<div class="col-sm-3 form-group">
										<label>Proteina</label> <input type="number"
											class="form-control" id="txtProt" name="txtProt">
									</div>
									<div class="col-sm-3 form-group">
										<label>Carboidratos</label> <input type="number"
											class="form-control" id="txtCarb" name="txtCarb">
									</div>
									<div class="col-sm-3 form-group">
										<label>Gordura</label> <input type="number"
											class="form-control" id="txtFat" name="txtFat">
									</div>
									<div class="col-sm-3 form-group">
										<label>Calorias</label> <input type="number"
											class="form-control" id="txtCal" name="txtCal">
									</div>
									<div class="col-sm-3 form-group">
										<input type="hidden" id="txtIdnovo" name="txtIdnovo">
									</div>
									<input class="btn btn-success btn-lg" type="submit"
										name="operacao" value="ALTERARSUP" id="operacao"
										style="margin-left: 15%;">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="modal-footer "></div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
</html>
