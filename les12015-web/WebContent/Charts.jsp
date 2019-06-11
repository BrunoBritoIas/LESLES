<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
</head>

<body>
	<canvas id="myChart" width="5" height="5"></canvas>
	<script>
		function getRandomColor() {
			var letters = '0123456789ABCDEF';
			var color = '#';
			for (var i = 0; i < 6; i++) {
				color += letters[Math.floor(Math.random() * 16)];
			}
			return color;
		}
		</script>
	<script>
		var ctx = document.getElementById('myChart');
		var myChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
				datasets : [ 
					<c:forEach items="${dados}" var="data">
				{
					label : '${data.categoria}',
					data : [ '${data.qtdMes.get(0)}', '${data.qtdMes.get(1)}', '${data.qtdMes.get(2)}', '${data.qtdMes.get(3)}',
							 '${data.qtdMes.get(4)}', '${data.qtdMes.get(5)}','${data.qtdMes.get(6)}', '${data.qtdMes.get(7)}', 
							'${data.qtdMes.get(8)}', '${data.qtdMes.get(9)}', '${data.qtdMes.get(10)}', '${data.qtdMes.get(11)}' ],
					backgroundColor : [ 'Transparent' ],
					borderColor : [ getRandomColor() ],
					borderWidth : 5
				}, 
				</c:forEach>
				]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				}
			}
		});
	</script>
	<form action="pesquisaGraficos" method="get">
		<button type="submit" class="btn btn-primary btn-lg" name="operacao"
			value=GRAFICOS id="operacao" style="width: 95%;">
			<span class="glyphicon glyphicon-ok-sign"></span> consultar
		</button>
		<select name="carlist" form="carform">
			<option value="1">Janeiro</option>
			<option value="2">Fevereiro</option>
			<option value="3">Março</option>
			<option value="4">Abril</option>
			<option value="5">Maio</option>
			<option value="6">Junho</option>
			<option value="7">Julho</option>
			<option value="8">Agosto</option>
			<option value="9">Setembro</option>
			<option value="10">Outubro</option>
			<option value="11">Novembro</option>
			<option value="12">Dezembro</option>
		</select> ATÉ <select name="carlist" form="carform">
		<option value="1">Janeiro</option>
			<option value="2">Fevereiro</option>
			<option value="3">Março</option>
			<option value="4">Abril</option>
			<option value="5">Maio</option>
			<option value="6">Junho</option>
			<option value="7">Julho</option>
			<option value="8">Agosto</option>
			<option value="9">Setembro</option>
			<option value="10">Outubro</option>
			<option value="11">Novembro</option>
			<option value="12">Dezembro</option>
		</select> <input type="number" min="2015" max="2019" step="1" value="2019"
			name="ano"
			style="margin-left: 111px; width: 163px; text-align: center; font-size: 35px;">
	</form>
</body>
</html>