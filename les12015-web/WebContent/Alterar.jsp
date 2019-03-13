<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta Clientes</title>
</head>
<body>
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
			<input type="submit" name="operacao" value="EDITAR" id="operacao">
	</form>
</body>
</html>