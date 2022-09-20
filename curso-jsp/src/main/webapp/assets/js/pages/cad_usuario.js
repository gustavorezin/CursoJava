$('#botaoRemover').hide();
	
// LIMPAR FORMULARIO **************************************************************************
function limparForm() {
var elementos = document.getElementById("formUser").elements; // retorna os elementos dentro do form
	for(i = 0; i < elementos.length; i++) {
		elementos[i].value = '';
	}
}

// DELETE USUARIO *****************************************************************************
function criarDeleteAjax() {
	if (confirm("Deseja realmente deletar este usuário?")){
		const urlAction = document.getElementById("formUser").action;
		const codUser = document.getElementById("codigo").value;
		
		$.ajax({
			method: 'get',
			url: urlAction,
			data: "codigo=" + codUser + "&acao=deletarajax",
			success: function(response) {
				limparForm();
				document.getElementById("msg").textContent = response;
			}
		}).fail(function(xhr, status, errorThrown){
			alert("Erro ao deletar usuário por id: " + xhr.responseText);
		});
	}
}

// BUSCA DE USUARIO (MODAL) *******************************************************************
function buscarUser() {
	var nomeBusca = document.getElementById("nomeBusca").value;
	
	if (nomeBusca != null && nomeBusca != "" && nomeBusca.trim() != ""){ // validar se tem dados para buscar no banco de dados
		
		const urlAction = document.getElementById("formUser").action;
	
		$.ajax({
			method: 'get',
			url: urlAction,
			data: "nomeBusca=" + nomeBusca + "&acao=buscarUserAjax",
			success: function(response) {
				
				const json = JSON.parse(response); // divide cada usuario em um array
				
				$("#tabelaRetornoBusca > tbody > tr").remove();
				$("#tabelaRetornoBusca > thead > tr").remove();
				
				$("#tabelaRetornoBusca > thead").append('<tr><th class="col-sm-2">Código</th> <th>Nome</th> <th class="col-sm-2"></th></tr>')
				
				for (i = 0; i < json.length; i++) {
					$("#tabelaRetornoBusca > tbody").append('<tr> <th>' + json[i].codigo + '</th> <td>' + json[i].nome + '</td> <td><button class="btn btn-primary btn-sm" onclick="verEditar(' + json[i].codigo + ')"><i class="fa fa-edit"></i></button></td></tr>')
				}
				
				document.getElementById("totalResultado").textContent = 'Exibindo ' + json.length + ' registros';
				
			}
		}).fail(function(xhr, status, errorThrown){
			alert("Erro ao buscar usuário por nome: " + xhr.responseText);
		});
	}
}

// VER EDITAR USUARIO APOS BUSCA (modal) ******************************************************
function verEditar(codigo) {
	const urlAction = document.getElementById('formUser').action;
	
	window.location.href = urlAction + '?acao=buscarEditar&codigo=' + codigo;
	
}