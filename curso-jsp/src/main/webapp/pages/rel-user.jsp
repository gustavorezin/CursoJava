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
										<h2 class="page-title">Relatório de usuário</h2>
									</div>
									<div class="col-md-4">
										<ul class="breadcrumb-title">
											<li class="breadcrumb-item"><a
												href="<%=request.getContextPath()%>/pages/telainicial.jsp"><i
													class="fa fa-home"></i> </a></li>
											<li class="breadcrumb-item"><span>#</span></li>
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
														<!-- Nav tabs -->
														<ul class="nav nav-tabs tabs" role="tablist">
															<li class="nav-item"><a class="nav-link active"
																data-toggle="tab" href="#menu01" role="tab">Principal</a>
															</li>
														</ul>
														<form action="<%=request.getContextPath()%>/ServletUsuarioController" method="get" id="formUser">
															<input type="hidden" name="acao" id="acao" value="imprimirRelatorioHtml"> <!-- ação para pegar no GET do servlet -->
															<!-- Tab panes -->
															<div class="tab-content tabs card-block">
																<!-- TAB PRINCIPAL ---------- -->
																<div class="tab-pane active" id="menu01"
																	role="tabpanel">
																	<div class="form-group row">
																		<div class="form-group col-md-6">
																			<label for="nome">Nome</label> 
																			<input type="text" class="form-control" name="nome" id="nome" value="${nome}">
																		</div>
																	</div>
																</div>
																<hr>
																<!-- BOTOES ---------- -->
																<button class="btn btn-success waves-effect waves-light" id="botaoGerar">Gerar</button>
															</div>
														</form>
														<c:if test="${listaUser != null}">
															<div class="table-responsive">
																<table class="table table-hover"
																	id="tabelaRetornoBusca">
																	<thead>
																		<tr>
																			<th class="col-sm-2">Código</th>
																			<th>Nome</th>
																			<th>Email</th>
																			<th>Login</th>
																		</tr>
																	</thead>
																	<tbody>
																	  <c:forEach items="${listaUser}" var="ml">
																	  	<tr>
																	  		<td><c:out value="${ml.codigo}"></c:out></td>
																	  		<td><c:out value="${ml.nome}"></c:out></td>
																	  		<td><c:out value="${ml.email}"></c:out></td>
																	  		<td><c:out value="${ml.login}"></c:out></td>
																	  	</tr>
																	  </c:forEach>
																	</tbody>
																</table>
															</div>
														</c:if>
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

	

	<!-- Modal RELATORIO -->
	<div class="modal fade" id="modalRelatorio" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header bg-info">
					<h5 class="modal-title" id="exampleModalLabel">
						<i class="fa fa-trash-o"></i> Impressão
					</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"> 
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h6>Selecione uma opção para impressão</h6>
					<hr>
					<button type="button" onclick="imprimirPdf()" class="btn btn-danger">Imprimir PDF</button> <br> <br>
					<button type="button" onclick="imprimirHtml()" class="btn btn-secondary">Imprimir na tela</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<script type="text/javascript">
	/*
	function imprimirPdf() {
	    document.getElementById("acaoTipoRel").value = 'imprimirRelatorioPDF';
	    $("#formUser").submit();
	    return false;
	}

	function imprimirHtml() {
	    document.getElementById("acaoTipoRel").value = 'imprimirRelatorioHtml';
	    $("#formUser").submit();
	}*/

	</script>

</body>

</html>
