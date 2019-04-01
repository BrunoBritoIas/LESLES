<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="les12015.core.aplicacao.Resultado, les12015.dominio.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<style>
a {
	color: #014BB0;
	text-decoration: none;
}
</style>

<div class="wrapper">
	<nav class="navbar navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="Home.jsp" class="navbar-brand"><b>SupliMais</b></a>
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-collapse">
					<i class="fa fa-bars"></i>
				</button>
			</div>

			<div class="collapse navbar-collapse pull-right" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="main-restaurants.html"></a></li>
					<c:choose>
						<c:when test="${usuario !=null}">
							<li><a href="CliAdmin.jsp">Perfil </a></li>
							<li><a href="#">Logout</a></li>
						</c:when>
						<c:when test="${usuario ==null}">
					    <li><a href="Login.jsp">Login</a></li>
					  </c:when>

					</c:choose>
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
</div>