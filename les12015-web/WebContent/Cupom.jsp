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
<body>
	<form action="SalvarCupom" method="post" id="frmSalvarEndereco">
		<table class="table table-bordered">

			<tr>
				Dados do Cupom
				<td><input type="date" placeholder="Data de validade"
					class="form-control" id="dtValidade" name="dtValidade"> <input
					type="text" placeholder="Valor do Desconto" class="form-control"
					id="txtDesconto" name="txtDesconto"> <input type="text"
					placeholder="Codigo" class="form-control" id="txtSerial"
					name="txtSerial"> <input type="hidden" placeholder="Número"
					class="form-control" id="txtId" name="txtId"> Promocional <input
					type="radio" id="tpCupom" name="tpCupom" value="Promocional" checked>
					Troca <input type="radio" id="tpCupom" name="tpCupom" value="false">
				</td>

			</tr>
		</table>
		<input type="submit" class="btn btn-primary" id="operacao"
			name="operacao" value="SAVECUPOM" class="btn btn-default" />
	</form>
</body>
</html>