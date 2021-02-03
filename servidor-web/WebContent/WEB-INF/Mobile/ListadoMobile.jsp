<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="webservices.EspectaculoDto" %>
<%@page import="webservices.FuncionDto" %>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-16">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inicio.css">
    <title>Espectaculos</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<script src="${pageContext.request.contextPath}/js/isMobile.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/font-awesome.js"></script>
</head>
<body onresize="redireccionar()">

	<nav class="navbar navbar-expand-md bg-light navbar-light">
	  <!-- Brand -->
  	  <a class="navbar-brand" href="#">${usuarioLogueado.getNickname()}</a>
	  <a class="navbar-brand" href="Home?tipo=mobile"> CoronaTickets <i class="fas fa-crown"></i> </a>
	
	  <!-- Toggler/collapsibe Button -->
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	    <i class="fas fa-bars"></i>
	  </button>
	
	  <!-- Navbar links -->
	  <div class="collapse navbar-collapse" id="collapsibleNavbar">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link" href="Search?tipoGet=listado&tipoLista=espectaculos">Ver Espectaculos</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="Autenticacion">Salir</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	
	<% 
	if (request.getAttribute("espectaculos") != null) {
		List<EspectaculoDto> esps = (List<EspectaculoDto>) request.getAttribute("espectaculos"); 
	%>
		<div class="container-fluid content-row">
		    <div class="row">
		        <div class="col-sm-12 col-lg-6">
		        <h4 align="center"><br>Espectaculos disponibles:<br><br></h4>
		        <% for(EspectaculoDto e : esps) { %>
		        	<div class="card mb-0">
		        	  <a href="DetalleEspectaculo?tipoGet=mobile&plataforma=<%=e.getNombrePlataforma() %>&espectaculo=<%=e.getNombre() %>"> 
					  	<img class="card-img-top" src="<%= e.getUrlImagen() %>" alt="Card image">
					  </a>
					  <div class="card-body">
					    <h5 class="card-title"><%= e.getNombre() %></h5>
					  </div>
		        	</div>
				<% } %>
				</div>
	        </div>
    	</div>
    <%
	}
    %>
    
</body>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
	//Si se cambia el de dispositivo
	function redireccionar() {
		if (!isMobile()) {
			// FALATARIA CERRAR LA SESION ANTES
			window.location="http://localhost:8080/servidor-web/Home";
		}
	}
</script>

</html>