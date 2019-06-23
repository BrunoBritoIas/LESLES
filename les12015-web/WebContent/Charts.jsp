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
		
		function filtro(){ 
			 var val = 0;
			 for(var i = 11 ; i >= 0; i--){
				 for(var y = 0 ; y < 5; y++){	
					val = val + myChart.data.datasets[y].data[i];
					 }
				if(val==0){
					delete myChart.data.datasets[0].data[i]
					delete myChart.data.datasets[1].data[i]
					delete myChart.data.datasets[2].data[i]
					delete myChart.data.datasets[3].data[i]
					delete myChart.data.datasets[4].data[i]
					delete myChart.data.datasets[5].data[i]
				}
				else{
					myChart.update();
					return;
				}
			 
			   
			 }	 			
		}
		function filtro2(){ 
			 var val = 0;
			 for(var i = 0 ; i <= 11; i++){
				 for(var y = 0 ; y < 5; y++){	
					val = val + myChart.data.datasets[y].data[i];
					 }
				if(val==0){
					delete myChart.data.datasets[0].data[i]
					delete myChart.data.datasets[1].data[i]
					delete myChart.data.datasets[2].data[i]
					delete myChart.data.datasets[3].data[i]
					delete myChart.data.datasets[4].data[i]
					delete myChart.data.datasets[5].data[i]
				}
				else{
					myChart.update();
					return;
				}
			 
			   
			 }	 			
		}
		 
			function setaMes() {
 
				myChart.data.labels[0] = "Jan";
				myChart.data.labels[1] = "Fev";
				myChart.data.labels[2] = "Mar";
				myChart.data.labels[3] = "Abril";
				myChart.data.labels[4] = "Maio";
				myChart.data.labels[5] = "Jun";
				myChart.data.labels[6] = "Jul";
				myChart.data.labels[7] = "Ago";
				myChart.data.labels[8] = "Set";
				myChart.data.labels[9] = "Out";
				myChart.data.labels[10] = "Nov";
				myChart.data.labels[11] = "Dez";
				myChart.update();
	
		}
		</script>
	<script>
		var ctx = document.getElementById('myChart');
		var myChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels :
					
					[],
				
					
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
		<c:forEach items="${dados}" var="data" varStatus="loop">
				setaMes('${loop.index}')
		</c:forEach>
				filtro()
				//filtro2()
	</script>
	<form action="pesquisaGraficos" method="get">
		
		<select name="mes1" id="mes1">
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
		</select> ATÉ 
		<select name="mes2" id="mes2">
			<option value="12">Dezembro</option>
			<option value="11">Novembro</option>
			<option value="10">Outubro</option>
			<option value="9">Setembro</option>
			<option value="8">Agosto</option>
			<option value="7">Julho</option>
			<option value="6">Junho</option>
			<option value="5">Maio</option>
			<option value="4">Abril</option>
			<option value="3">Março</option>
			<option value="2">Fevereiro</option>
			<option value="1">Janeiro</option>
		</select>
		 
		 <input type="number" min="2015" max="2019" step="1" value="2019" name="ano"
			style="margin-left: 111px; width: 163px; text-align: center; font-size: 35px;">
			<button type="submit" class="btn btn-primary btn-lg" name="operacao"
			value=GRAFICOS id="operacao" style="width: 95%;">
			<span class="glyphicon glyphicon-ok-sign"></span> consultar
		</button>
	</form>
</body>
</html>