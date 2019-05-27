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
					<c:forEach items="${categorias}" var="suplemento">
				{
					label : '${suplemento.categoria}',
					data : [ '${suplemento.quantidade}', 19, 3, 5, 2, 3, 12, 19, 3, 5, 2, 3 ],
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
<form action="pesquisaGraficos" method="post">
	<button type="submit" class="btn btn-primary btn-lg" name="operacao"
		value=GRAFICOS id="operacao" style="width: 95%;">
		<span class="glyphicon glyphicon-ok-sign"></span> consultar
	</button>
</form>
</body>
</html>