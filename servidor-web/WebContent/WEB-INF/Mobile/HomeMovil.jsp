<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
  <title>Inicio</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <script src="${pageContext.request.contextPath}/js/isMobile.js"></script>
  <script src="${pageContext.request.contextPath}/bootstrap/js/font-awesome.js"></script>

</head>

<body onresize="redireccionar()">

	<c:if test = "${ sessionScope.usuarioLogueado.getClass().toString().equals('class webservices.ArtistaDto')}">
         <% response.sendRedirect("/servidor-web/Autenticacion"); %>
    </c:if>
    
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

	
	<div class="container-fluid content-row">
	    <div class="row">
	        <div class="col-sm-12 col-lg-6">
	        	<h4  align="center">
					  <br>
					  <br>
					  Espectaculos destacados:
				</h4>
	            <div class="card bg-light border-0">
	            	<a href="DetalleEspectaculo?tipoGet=mobile&plataforma=Facebook Watch&espectaculo=Global Spirit">
						<img style="border-radius: 35px; height:85px;" class="card-img-top" src=https://cuidateplus.marca.com/sites/default/files/styles/natural/public/montadores-espectaculos.jpg alt="Card image">
					    <div class="card-img-overlay">
					      <h3 align="center" style="color:white;"><br> Global Spirit</h3>
					    </div>
				    </a>
				</div>
				<p></p>
			    <div class="card bg-light border-0">
			        <a href="DetalleEspectaculo?tipoGet=mobile&plataforma=Twitter Live&espectaculo=Bien de Familia">
						<img style="border-radius: 35px; height:85px;" class="card-img-top" src="https://cuidateplus.marca.com/sites/default/files/styles/natural/public/montadores-espectaculos.jpg" alt="Card image">
						<div class="card-img-overlay">
							<h3 align="center" style="color:white;"><br> Bien de Familia</h3>
					    </div>
					</a>
				</div>
			</div>
	    </div>
	</div>
	
	<div class="container-fluid content-row">
	    <div class="row">
	        <div class="col-sm-12 col-lg-6">
				<h4  align="center">
					  <br>
					  Funciones destacadas:
				</h4>
	            <div class="card bg-light border-0">
	            	<a href="DetalleFuncion?tipoGet=mobile&plataforma=Facebook Watch&espectaculo=Global Spirit&funcion=Global Spirit (II)">
						<img style="border-radius: 35px; height:85px;" class="card-img-top" src="https://www.ersmgrupo.com/wp-content/uploads/2017/05/espectaculos-1200x675.jpg" alt="Card image">
					    <div class="card-img-overlay">
					      <h3 align="center" style="color:white;" class="card-title"><br> Global Spirit (II)</h3>
					    </div>
				    </a>
				</div>
				<p></p>
		        <div class="card bg-light border-0">
		         	<a href="DetalleFuncion?tipoGet=mobile&plataforma=Twitter Live&espectaculo=Bien de Familia&funcion=Bien de Familia - B">
						<img style="border-radius: 35px; height:85px;" class="card-img-top" src="https://www.ersmgrupo.com/wp-content/uploads/2017/05/espectaculos-1200x675.jpg" alt="Card image">
					    <div class="card-img-overlay">
					      <h3 align="center" style="color:white;" class="card-title"><br> Bien de Familia - B</h3>
					    </div>
					</a>
				</div>
			</div>
	    </div>
	</div>
	
</body>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
