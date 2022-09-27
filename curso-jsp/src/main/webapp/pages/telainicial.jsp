<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

<jsp:include page="head.jsp"></jsp:include>

<body>

	<jsp:include page="themeloader.jsp"></jsp:include>

	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<!-- NAVBAR ******************************************************************************* -->
			<jsp:include page="navbar.jsp"></jsp:include>
			<!-- FIM - NAVBAR ************************************************************************* -->

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- NAVBAR MAIN MENU ************************************************************* -->
					<jsp:include page="navbar-mainmenu.jsp"></jsp:include>
					<!-- FIM - NAVBAR MAIN MENU ******************************************************* -->

					<div class="pcoded-content">

						<!-- CABEÇALHO -->
						<div class="page-header">
							<div class="page-block">
								<div class="row align-items-center">
									<div class="col-md-8">
										<h2 class="page-title">Painel de controle</h2>
									</div>
									<div class="col-md-4">
										<ul class="breadcrumb-title">
											<li class="breadcrumb-item">
												<a href="<%=request.getContextPath()%>/pages/telainicial.jsp"><i class="fa fa-home"></i></a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>

						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<!-- CONTEÚDO DA PÁGINA *************************************** -->
										<div class="card">
											<div class="card-block">
												<div class="row">
													<div class="col-md-12">
														<form action="<%=request.getContextPath()%>/ServletFuncionario" method="get" id="formFunc">
															<input type="hidden" name="acao" id="acao" value=""> <!-- ação para pegar no GET do servlet -->

															<div class="row">
																<div class="form-group col-md-12">
																	<table style="width: 100%">
																		<tr>
																			<td width="20%">
																				Cargo 
																				<select class="form-control" name="cargo" id="cargo">
																					<option value="">Selecione</option>
																					<option value="ADMIN" ${cargo == 'ADMIN' ? 'selected' : ''}>Administrador(a)</option>
																					<option value="SECRETARIO" ${cargo == 'SECRETARIO' ? 'selected' : ''}>Secretário(a)</option>
																					<option value="SUPORTEDEV" ${cargo == 'SUPORTEDEV' ? 'selected' : ''}>Suporte/Dev</option>
																				</select>
																			</td >
																			<td width="2%"></td>
																			<td>
																				<br>
																				<button type="button" class="btn btn-success waves-effect waves-light" id="botaoGerar" 
																					onclick="geraGrafico()">
																					<i class="fa fa-search"></i>
																				</button>
																			</td>
																		</tr>
																	</table>
																</div>
															</div>

															<hr>

															<div>
																<canvas id="myChart"></canvas>
															</div>

														</form>
													</div>
												</div>
											</div>
										</div>

										<!-- FIM - CONTEÚDO DA PÁGINA ********************************* -->

									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<script type="text/javascript">
		var myChart = new Chart(document.getElementById('myChart'));
	
		function geraGrafico() {
			
			// AJAX GRAFICO *****************************************************************************
			var urlAction = document.getElementById('formFunc').action;
			var cargo = document.getElementById('cargo').value;
			
			$.ajax({
				method : 'get',
				url : urlAction,
				data : 'cargo=' + cargo + '&acao=graficoSalario',
				success : function(response) {
					
					var json = JSON.parse(response)
					
					myChart.destroy();
					
					myChart = new Chart(
				    document.getElementById('myChart'),
				    { //config
				    	type : 'bar',
							data : {
								labels : json.cargoList,
								datasets : [{
									label : 'Média salárial',
									backgroundColor : 'rgb(255, 99, 132)',
									borderColor : 'rgb(255, 99, 132)',
									data : json.salarioList,
								}]
							},
							options : {}
						}
				  );
				}
			}).fail(function(xhr, status, errorThrown) {
				alert('Erro ao buscar dados: ' + xhr.responseText);
			});
		}
		
	</script>

</body>

</html>
