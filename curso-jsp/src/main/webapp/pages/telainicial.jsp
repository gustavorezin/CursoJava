<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

<jsp:include page="head.jsp"></jsp:include>

<body>

<jsp:include page="themeloader.jsp"></jsp:include>

<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
	<div class="pcoded-overlay-box"></div>
  	<div class="pcoded-container navbar-wrapper">
  
		<!-- NAVBAR #################################### -->
		<jsp:include page="navbar.jsp"></jsp:include>
		<!-- NAVBAR #################################### -->

		<div class="pcoded-main-container">
        	<div class="pcoded-wrapper">
          
				<!-- NAVBAR MAIN MENU #################################### -->
				<jsp:include page="navbar-mainmenu.jsp"></jsp:include>
				<!-- NAVBAR MAIN MENU #################################### -->
		
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
					                        <a href="<%= request.getContextPath() %>/pages/telainicial.jsp"><i class="fa fa-home"></i> </a>
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
                                    <div class="row">
                                    
                                        <!-- CONTEÚDO DA PÁGINA ################################### -->
										<h1>Conteúdo da Página</h1>
                                        <!-- CONTEÚDO DA PÁGINA ################################### -->
                                        
                                    </div>
                                </div>
								<!-- Page-body end -->
                            </div>
                        	<div id="styleSelector"> </div>
                    	</div>
					</div>
				</div>
            </div>
    	</div>
	</div>
</div>

<jsp:include page="javascriptfile.jsp"></jsp:include>
    
</body>

</html>
