<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>

			</div>
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
							<div class="col-sm-4">
								<input type="hidden" id="Idnovo" name="txtIdnovo">
							</div>
						</div>
					</div>
				</div>
				<c:choose>
					<c:when test="${resultado == null}">
						<div></div>
					</c:when>
					<c:when test="${ resultado != null && resultado.msg == null}">
						<div class="alert alert-success"
							style="margin-left: 294px; class: center; text-align: center; width: 74%;"
							role='alert'>
							<strong>Parabens</strong> Cliente cadastrado com sucesso!, clique
							<strong><a href="Login.jsp">Aqui</a></strong> para logar.
						</div>
					</c:when>

					<c:when test="${resultado.msg !=null}">
						<div class="alert alert-danger"
							style="margin-left: 294px; class: center; text-align: center; width: 74%;"
							role="alert">${resultado.msg}</div>
					</c:when>

				</c:choose>
				<button type="submit" style="margin-left: 600px"
					class="btn btn-primary" name="operacao" value="SALVAR"
					id="operacao">CADASTRAR</button>
			</form>
			<!-- /form -->
		</div>
	</div>