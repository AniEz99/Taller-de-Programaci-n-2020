<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="webservices.EspectaculoDto" %>
<%@page import="webservices.FuncionDto" %>
<%@page import="webservices.PaqueteDto" %>
<%@page import="webservices.CategoriaDto" %>
<%@page import="webservices.ValoracionDto" %>
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
    <link href="${pageContext.request.contextPath}/css/detalleEspectaculo.css" rel="stylesheet" type="text/css">
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
	
	<% EspectaculoDto espectaculo = (EspectaculoDto) request.getAttribute("espectaculo"); %>
	<div class="container-fluid content-row">
	    <div class="row">
	        <div class="col-sm-12 col-lg-6">
	        	<p> <br> </p>
	        	<img width="125px" height="125px" style="float:left; border-radius:20%; margin-right: 20px;" src="<%= espectaculo.getUrlImagen() %>"/> 
		        <h3> <%= espectaculo.getNombre() %></h3>
		        <h5> <%= espectaculo.getNombrePlataforma() %></h5>
		        <% 
		        List<ValoracionDto> vals = espectaculo.getValoraciones(); 
		        if (vals.isEmpty()) {
		        %>
		        <p>No hay valoraciones.</p>
		        <%
		        }
		        else {
		        	int promedio = 0;
		        	for (ValoracionDto valesp : vals) {
		        		promedio+= valesp.getEstrellas();
		        	}
		        	promedio = promedio/vals.size();
		        	for (int i=0; i < promedio; i++) {
		        %>
						<i class="fas fa-star fa-1x estrella"></i>
				<% 
					}
		        }
		        %>
		        <div style="clear:left">
			        <a href="<%= espectaculo.getUrl() %>"> <br> Web del Espectaculo </a>
			        <p> <%= espectaculo.getDescripcion() %></p>
			        <hr>
		        </div>
		        <h5>Funciones: </h5>
		        <% for (FuncionDto funcs : espectaculo.getFuncionesAsociadas()) { %>
			        <ul>
			        	<li><a href="DetalleFuncion?tipoGet=mobile&plataforma=<%= espectaculo.getNombrePlataforma() %>&espectaculo=<%= espectaculo.getNombre() %>&funcion=<%= funcs.getNombre() %>"> 
			        		<%= funcs.getNombre() %>
			        	</a></li>
					</ul>
				<% } %>
				<hr>
				<h5>Paquetes: </h5>
				<% for (PaqueteDto paqs : espectaculo.getPaquetesAsociados()) { %>
					<ul>
			        	<li><%= paqs.getNombre() %></li>
					</ul>
				<% } %>
				<hr>
				<h5>Categorias: </h5>
				<% for (CategoriaDto cats : espectaculo.getCategoriasAsociadas()) { %>
					<ul>
			        	<li><%= cats.getNombre() %></li>
					</ul>
				<% } %>
	        </div>
        </div>
   </div>
   
</body>

<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
	//Si se cambia el de dispositivo
	function redireccionar() {
		if (!isMobile()) {
			// FALATARIA CERRAR LA SESION ANTES
			window.location="/servidor-web/Home";
		}
	}
</script>

</html>