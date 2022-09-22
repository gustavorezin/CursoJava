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
										<h2 class="page-title">Cadastro de usuário</h2>
									</div>
									<div class="col-md-4">
										<ul class="breadcrumb-title">
											<li class="breadcrumb-item"><a
												href="<%=request.getContextPath()%>/pages/telainicial.jsp"><i
													class="fa fa-home"></i> </a></li>
											<li class="breadcrumb-item"><span>Cadastros</span></li>
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
														<form action="<%=request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
															<input type="hidden" name="acao" id="acao" value=""> <!-- ação para pegar no GET do servlet -->
															<!-- Tab panes -->
															<div class="tab-content tabs card-block">
																<!-- TAB PRINCIPAL ---------- -->
																<div class="tab-pane active" id="menu01"
																	role="tabpanel">
																	<div class="form-group row">
																		<!-- linha 1 -->
																		<div class="form-group col-md-1">
																			<label for="codigo">Código</label> <input type="text"
																				class="form-control" name="codigo" id="codigo"
																				readonly="readonly" value="${modelLogin.codigo}">
																		</div>
																		<div class="form-group col-md-5">
																			<label class="control-label" for="nome">Nome</label>
																			<div class="input-group">
																				<input type="text" class="form-control" name="nome"
																					id="nome" maxlength="60" required
																					value="${modelLogin.nome}"> <span
																					class="input-group-btn"> <a
																					class="btn btn-info waves-effect waves-light btn-md"
																					data-toggle="modal" href="#modalUsuario"
																					data-target="#modalUsuario" title="Pesquisar"><i
																						class="fa fa-search" aria-hidden="true"></i></a>
																				</span>
																			</div>
																		</div>
																		<div class="form-group col-md-6">
																			<label for="email">E-mail</label> <input type="email"
																				class="form-control" name="email" id="email"
																				required value="${modelLogin.email}">
																		</div>
																		<!-- linha 2 -->
																		<div class="form-group col-md-3">
																			<label for="login">Login</label> <input type="text"
																				class="form-control" name="login" id="login"
																				maxlength="25" required value="${modelLogin.login}">
																		</div>
																		<div class="form-group col-md-3">
																			<label for="senha">Senha</label> <input
																				type="password" class="form-control" name="senha"
																				id="senha" autocomplete="off" required
																				value="${modelLogin.senha}">
																		</div>
																		<div class="form-group col-md-3">
																			<label class="control-label" for="grupoUsuario">Grupo</label>
																			<select class="form-control" name="grupoUsuario"
																				id="grupoUsuario" required>
																				<option value="">Selecione</option>
																				<option value="ADMIN" ${modelLogin.grupo == 'ADMIN' ? 'selected' : ''}>Administração</option>
																				<option value="SECRETARIO" ${modelLogin.grupo == 'SECRETARIO' ? 'selected' : ''}>Secretário(a)</option>
																			</select>
																		</div>
																		<!-- linha 3 -->
																	</div>
																</div>

																<hr>
																<!-- BOTOES ---------- -->
																<c:if test="${modelLogin.codigo == null}">
																	<button class="btn btn-success waves-effect waves-light" id="botaoSalvar">Salvar</button>
																</c:if>
																<c:if test="${modelLogin.codigo > 0}">
																	<button class="btn btn-success waves-effect waves-light" id="botaoSalvar">Atualizar</button>
																	<a type="button"
																		class="btn btn-primary waves-effect waves-light"
																		id="botaoLimpar"
																		href="<%=request.getContextPath()%>/ServletUsuarioController">Limpar</a>
																	<button type="button"
																		class="btn btn-danger waves-effect waves-light"
																		id="botaoRemover" data-toggle="modal"
																		data-target="#modalDeletarUser" data-toggle="tooltip"
																		data-placement="top" title="Deletar">
																		<i class="fa fa-trash-o"></i>
																	</button>
																</c:if>
																<br/>
																<br/>
																<div id="mensagem">${msg}</div>
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

	<!-- Modal BUSCAR USER -->
	<div class="modal fade" id="modalUsuario" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header bg-info">
					<h5 class="modal-title" id="exampleModalLongTitle">
						<i class="fa fa-search"></i> Busca de usuário
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control"
							placeholder="Nome do usuário" aria-label="Nome do usuário"
							id="nomeBusca" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button"
								onclick="buscarUser()">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>

					<div class="table-responsive">
						<table class="table table-hover" id="tabelaRetornoBusca">
							<thead>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>

					<div class="modal-footer ">
						<span id="totalResultado"></span>
						<button type="button" class="btn btn-info" data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal DELETAR USER -->
	<div class="modal fade" id="modalDeletarUser" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header bg-danger">
					<h5 class="modal-title" id="exampleModalLabel">
						<i class="fa fa-trash-o"></i> Deletar usuário
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Você tem certeza que deseja remover este usuário?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" data-dismiss="modal">Fechar</button>
					<button type="button" class="btn btn-danger" onclick="criarDeleteAjax()" data-dismiss="modal">Deletar</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<script type="text/javascript">
		$('#salario').maskMoney({
			showSymbol : true,
			symbol : 'R$ ',
			decimal : ',',
			thousands : '.'
		});
		$('#salario').focus();

		// PERMITIR APENAS NUMEROS NOS CAMPOS SELECIONADOS ********************************************
		$('#fone1, #fone2, #celular1, #celular2, #cep, #numero').keypress(
				function(event) {
					return /\d/.test(String.fromCharCode(event.keyCode));
				});

		// PESQUISA DE CEP NA ABA ENDERECO ************************************************************
		function pesquisaCep() {
			var cep = $('#cep').val();

			$.getJSON('https://viacep.com.br/ws/' + cep + '/json/?callback=?',
				function(dados) {
					if (!("erro" in dados)) {
						//Atualiza os campos com os valores da consulta.
						$("#cep").val(dados.cep);
						$("#uf").val(dados.uf);
						$("#cidade").val(dados.localidade);
						$("#logradouro").val(dados.logradouro);
						$("#bairro").val(dados.bairro);
					} else {
						//CEP pesquisado não foi encontrado.
						limpa_formulário_cep();
						alert("CEP não encontrado.");
					}
				});
		}

		// DELETE USUARIO *****************************************************************************
		function criarDeleteAjax() {

			const urlAction = document.getElementById('formUser').action;
			const codigo = document.getElementById('codigo').value;

			$.ajax({
				method : 'get',
				url : urlAction,
				data : 'codigo=' + codigo + '&acao=deletarajax',
				success : function(response) {
					window.location.href = urlAction;
					document.getElementById('mensagem').textContent = response;
				}
			}).fail(function(xhr, status, errorThrown) {
				alert('Erro ao deletar usuário por id: ' + xhr.responseText);
			});

			$('#mensagem').hide();
		}

		// BUSCA DE USUARIO (MODAL) *******************************************************************
		function buscarUser() {
			var nomeBusca = document.getElementById('nomeBusca').value;
			const urlAction = document.getElementById('formUser').action;

			$.ajax(
							{
								method : 'get',
								url : urlAction,
								data : 'nomeBusca=' + nomeBusca
										+ '&acao=buscarUserAjax',
								success : function(response) {

									const json = JSON.parse(response); // divide cada usuario em um array

									$('#tabelaRetornoBusca > tbody > tr').remove();
									$('#tabelaRetornoBusca > thead > tr').remove();

									$('#tabelaRetornoBusca > thead').append('<tr><th class="col-sm-2">Código</th> <th>Nome</th> <th class="col-sm-2"></th></tr>')

									for (i = 0; i < json.length; i++) {
										$('#tabelaRetornoBusca > tbody').append(
												'<tr>' 
													+ '<th>' + json[i].codigo + '</th>'
													+ '<td>' + json[i].nome + '</td>'
													+ '<td><a style="cursor: pointer;" onclick="verEditar(' + json[i].codigo + ')"><i class="fa fa-edit"></i></a></td>'
												+ '</tr>'
											)
									}

									document.getElementById('totalResultado').textContent = 'Exibindo '
											+ json.length + ' registros';
								}
							}).fail(
							function(xhr, status, errorThrown) {
								alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
							});
		}

		// VER EDITAR USUARIO APOS BUSCA (modal) ************************************************
		function verEditar(codigo) {
			const urlAction = document.getElementById('formUser').action;

			window.location.href = urlAction + '?acao=buscarEditar&codigo=' + codigo;
		}
	</script>

</body>

</html>
