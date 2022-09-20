<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

	<link rel="stylesheet" href="css/styleLogin.css">
	
	<title>Curso JSP</title>
</head>
<body>
	<div class="container">
		<h1>Curso de JSP</h1>
		
		<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
		<form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="needs-validation" novalidate>
			
			<div class="mb-3">
				<label class="form-label" for="login">Login</label>
		  		<input class="form-control" id="login" name="login" type="text" required>
		  		<div class="invalid-feedback">
			      	Informe o login!
			    </div>
	 		</div> 
	 
	 		<div class="mb-3">
	  			<label class="form-label" for="senha">Senha</label> 
	  			<input class="form-control" id="senha" name="senha" type="password" required>
	  			<div class="invalid-feedback">
			      	Informe a senha!
			    </div>
			</div> 
			
			<div class="botao">
				<input type="submit" value="Acessar" class="btn btn-primary">
			</div>
			
		</form>
		
		<p class="msg">${msg}</p>
	</div>
		
	
	<!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	
	<script type="text/javascript">
		(function () {
			'use strict'
	
		  	var forms = document.querySelectorAll('.needs-validation')
			
		  	Array.prototype.slice.call(forms)
			    .forEach(function (form) {
			      	form.addEventListener('submit', function (event) {
		    	    	if (!form.checkValidity()) {
		          			event.preventDefault()
		          			event.stopPropagation()
		       			}
		        		form.classList.add('was-validated')
		     		}, false)
		    	})
			})()
	</script>
	
</body>
</html>