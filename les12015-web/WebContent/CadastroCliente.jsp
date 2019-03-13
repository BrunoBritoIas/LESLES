<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  target="_parent">
	<div class="container">
		<a class="navbar-brand"
			href="http://localhost:8080/MyProjectWeb/Index.jsp">PaPaZim</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Sobre</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Serviços</a>
				</li>
				
				</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="http://localhost:8080/MyProjectWeb/SalvarCarrinho">Carrinho</a>

				</li>
			</ul>
		</div>
	</div>
	</nav> <br><br><br>
	<form method="post" action="SalvarCliente">
			Nome<input type="text" id="txtNome" name="txtNome"/></br>
			CPF<input type="text" id="txtCpf" name="txtCpf"/></br>
			E-mail<input type="text" id="txtEmail" name="txtEmail"/></br>
			Genero<select id="txtGender" name="txtGender">
			  <option value="masculino">Masculino</option>
			  <option value="feminino">Feminino</option>
			  <option value="outros">Outros</option>
			</select></br>
			Nascimento: <input type="date" name="bday" id="bday">
		<input type="submit" name="operacao" value="SALVAR" id="operacao">
	</form>
</body>
</html>