<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<div class="panel panel-success">
	<div class="panel-heading">
		<span><i class="glyphicon glyphicon-lock"></i></span> Pagamento Seguro
	</div>
	<form method="post" action="SaveCards">
		<div class="panel-body">
			<div class="form-group">
				<div class="col-md-12">
					<strong>Bandeira</strong>
				</div>
				<div class="col-md-12">
					<select id="txtBandeira" placeholder="Bandeira" name="txtBandeira"
						class="form-control">
						<option value="Visa">Visa</option>
						<option value="MasterCard">MasterCard</option>
						<option value="Xerecard Express">Xerecard Express</option>
						<option value="Discover">Discover</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<strong>Nome Titular:</strong>
				</div>
				<div class="col-md-12">
					<input type="text" class="form-control" name="txtTitular" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<strong>Numero:</strong>
				</div>
				<div class="col-md-12">
					<input type="text" placeholder="Numero do Cartão"
						class="form-control" id="txtNumero" name="txtNumero" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<strong>Codigo de segurança:</strong>
				</div>
				<div class="col-md-12">
					<input type="text" placeholder="Codigo" class="form-control"
						id="txtCodigo" name="txtCodigo" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<strong>Data de Expiração</strong>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12"
					class="form-control" id="txtValidade" name="txtValidade">
					<input type="text" placeholder="Validade" class="form-control"
						id="txtValidade" name="txtValidade">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12">
					<button type="submit" class="btn btn-primary btn-submit-fix"
						id="operacao" name="operacao" value="SAVECARD">Cadastrar</button>
				</div>
			</div>
		</div>
	</form>
</div>
