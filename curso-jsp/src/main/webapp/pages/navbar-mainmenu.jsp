<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<nav class="pcoded-navbar">
    <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
    <div class="pcoded-inner-navbar main-menu">
        <div class="">
            <div class="main-menu-header">
                <img class="img-80 img-radius" src="<%= request.getContextPath() %>/assets/images/avatar-4.jpg" alt="User-Profile-Image">
                <div class="user-details">
                    <span id="more-details"><%= session.getAttribute("usuario") %><i class="fa fa-caret-down"></i></span>
                </div>
            </div>

            <div class="main-menu-content">
                <ul>
                    <li class="more-details">
                        <!--  <a href="user-profile.html"><i class="ti-user"></i>View Profile</a>
                        <a href="#!"><i class="ti-settings"></i>Settings</a> -->
                        <a href="<%= request.getContextPath() %>/ServletLogin?acao=sair"><i class="ti-layout-sidebar-left"></i>Sair</a>
                    </li>
                </ul>
            </div>
        </div>
        <ul class="pcoded-item pcoded-left-item">
            <li class="active">
                <a href="<%= request.getContextPath() %>/pages/telainicial.jsp" class="waves-effect waves-dark" style="margin-top: 20px">
                    <span class="pcoded-micon"><i class="ti-home"></i></span>
                    <span class="pcoded-mtext" data-i18n="nav.dash.main">Painel de controle</span>
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
        </ul>
        <div class="pcoded-navigation-label" data-i18n="nav.category.navigation">Menu</div>
        <ul class="pcoded-item pcoded-left-item">
            
            <!-- CADASTROS ---------- -->
            <li class="pcoded-hasmenu">
                <a href="javascript:void(0)" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="fa fa-archive" aria-hidden="true"></i></span>
                    <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">Cadastros</span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">
                    <li class=" ">
                    	<c:if test="${grupo == 'ADMIN'}">
	                        <a href="<%= request.getContextPath() %>/ServletUsuarioController" class="waves-effect waves-dark">
	                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
	                            <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Usuário</span>
	                            <span class="pcoded-mcaret"></span>
	                        </a>
                    	</c:if>
                    </li>
                    <li class=" ">
                        <a href="<%= request.getContextPath() %>/ServletFuncionario" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Funcionário</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                
                </ul>
                
                <!-- RELATORIOS ---------- -->
                <li class="pcoded-hasmenu">
	                <a href="javascript:void(0)" class="waves-effect waves-dark">
	                    <span class="pcoded-micon"><i class="fa fa-file-text" aria-hidden="true"></i></span>
	                    <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">Relatórios</span>
	                    <span class="pcoded-mcaret"></span>
	                </a>
	                <ul class="pcoded-submenu">
	                    <li class="pcoded-hasmenu ">
		                	<a href="javascript:void(0)" class="waves-effect waves-dark">
	                            <span class="pcoded-micon"><i class="ti-direction-alt"></i></span>
	                            <span class="pcoded-mtext" data-i18n="nav.menu-levels.menu-level-22.main">Cadastros</span>
	                            <span class="pcoded-mcaret"></span>
	                        </a>
			                <ul class="pcoded-submenu">
			                    <li class="">
			                        <a href="<%= request.getContextPath() %>/pages/rel-user.jsp" class="waves-effect waves-dark">
			                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                            <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Usuario</span>
			                            <span class="pcoded-mcaret"></span>
			                        </a>
			                    </li>
		                	</ul>
		            	</li>	
	                </ul>  
            	</li>
        </ul>
        
    </div>
</nav>