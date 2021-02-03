<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page import="webservices.FuncionDto" %>
<%@page import="webservices.PaqueteDto" %>
<%@page import="webservices.RegistroDto" %>
<%@page import="webservices.EspectaculoDto" %>
<%@page import="webservices.UsuarioDto" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

	<t:temp_top></t:temp_top>
	
	<link href="${pageContext.request.contextPath}/css/detalleFuncion.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/carousel.css" rel="stylesheet" type="text/css">
	

	<% FuncionDto funcion = (FuncionDto) request.getAttribute("funcion"); %>
	<% EspectaculoDto espectaculo = (EspectaculoDto) request.getAttribute("espectaculo"); %>
	<% String plataforma = (String) request.getAttribute("plataforma"); %>
	<% boolean yaSorteado = (boolean) request.getAttribute("yaSorteado"); %>
	<% boolean fechaCorrecta = (boolean) request.getAttribute("fechaCorrecta"); %>
	<div class="presentacion">
		<!-- Imagen provisoria -->
		<img src=" <%= funcion.getImagen() %> " class="portada" alt="Fest">
		<p id="nombre"> <%= funcion.getNombre() %> </p>
		<p id="fecha"> 
			<%= new SimpleDateFormat("dd/MM/yyyy").format(funcion.getFechaInicio().toGregorianCalendar().getTime().getTime()) %>
			-
			<%= funcion.getHoraComienzoHs() %>:<%= funcion.getHoraComienzoMin() %>
		</p>
		<p id="precio"> $ <%= espectaculo.getCosto() %> </p> 
		
		<% if ((session.getAttribute("usuarioLogueado") != null) && (request.getAttribute("tipousuario").equals("espectador"))) { %>
		<% boolean yaRegistrado = (boolean) request.getAttribute("registrado");%>
		<% if (!yaRegistrado && !fechaCorrecta) { %>	
		<div class="btn-group">
			<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Registrarse</button>
			<div class="dropdown-menu">
				<!-- Button trigger modal -->
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#centralModalSm1" Style="width: 100%">Compra normal</a>
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#centralModalSm2" Style="width: 100%">Usando Resgistros</a>
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#centralModalSm3" Style="width: 100%">Usando Paquetes</a>
			</div>
		</div>
		<% } else if (fechaCorrecta) { %>
		<H6> Funcion ya hecha</H6>
		<% } else { %>
		<H6> Funcion ya registrada</H6>
		<% } %>
		<% } %>
		
		<form class="btn-group" action="/servidor-web/DetalleEspectaculo" method="POST"> <!-- revisar -->
			<input type="hidden" name="plataforma" value="<%= plataforma %>"/>
			<input type="hidden" name="espectaculo" value="<%= espectaculo.getNombre() %>"/>
			<button name="espAsoc" id="espAsoc" type="submit" class="btn btn-primary" value="espectaculo">
				Espectáculo asociado
			</button>
		</form>
		
		<% if ((session.getAttribute("usuarioLogueado") != null) && (request.getAttribute("tipousuario").equals("artista"))) { %>
			<% boolean organizador = (boolean) request.getAttribute("organizador");%>
			<% if (!yaSorteado && fechaCorrecta && organizador) { %>
				<% if (espectaculo.getCantPremio() > 0) { %>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
					  Sortear Premio
					</button>
				<% } else { %>
					<H6> No hay premios para sortear</H6>
				<% } %>
			<% } %>
		<% } %>
		
		<% if ((session.getAttribute("usuarioLogueado") != null) && (request.getAttribute("tipousuario").equals("espectador"))) { %>
		<% boolean yaRegistrado = (boolean) request.getAttribute("registrado");%>
		<% if (!yaRegistrado) { %>	
		<form action="/servidor-web/Funcion" method="POST">
		<input type="hidden" name="funcion" value="<%= funcion.getNombre() %>"/>
		<input type="hidden" name="espectaculo" value="<%= espectaculo.getNombre() %>"/>
		<input type="hidden" name="plataforma" value="<%= plataforma %>"/>
		
		<!-- Central Modal Small (Registro normal)-->
		<input id="accionDeComprar" type="hidden" value="registroNormal" name="accionDeComprar">
		<div class="modal fade" id="centralModalSm1">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">Registro a función</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<!-- Modal body -->
					<div class="modal-body">
						<p>Compra normal</p>
					</div>
					<!-- Modal footer -->
					<div class="modal-body" align="right">
						<button id="btnComprar" type="submit" class="btn btn btn-outline-success">Comprar</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Central Modal Small (Registro normal)-->
		</form>
		
		<form action="/servidor-web/Funcion" method="POST">
		<!-- Central Modal Small (Usando Registros)-->
		<input type="hidden" name="funcion" value="<%= funcion.getNombre() %>"/>
		<input type="hidden" name="espectaculo" value="<%= espectaculo.getNombre() %>"/>
		<input type="hidden" name="plataforma" value="<%= plataforma %>"/>
		<input id="accionDeComprar" type="hidden" value="registroCanje" name="accionDeComprar">
		<div class="modal fade" id="centralModalSm2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<!-- Change class .modal-sm to change the size of the modal -->
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title w-100" id="myModalLabel">Registro a función</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>Seleccione tres registros previos 
							<a href="#" data-toggle="tooltip"
								title="Son validos los registros que no han sido utilizados y que no tengan costo 0">
								<i class="far fa-question-circle"></i>
							</a>
						</p>
						<select class="selectpicker" id="registrosCanjeados" name="registrosCanjeados" multiple data-live-search="true" multiple
							data-min-options="3" data-max-options="3" data-style="btn-outline-primary" data-size="3"
							data-width="100%">
							<% 
							List<RegistroDto> registros = (List<RegistroDto>) request.getAttribute("registros");
							for(RegistroDto reg: registros){
		 	  				 %>
							<option value="<%=reg.getNombreFuncion()%>"><%=reg.getNombreFuncion()%></option>
							<% } %>							
						</select>
						<br>
						<br>
						<h6 align="right">Costo: $ 0</h6>
					</div>
					<div class="modal-body" align="right">
						<button id="btnComprar" type="submit" class="btn btn btn-outline-success">Comprar</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Central Modal Small (Usando Registros)-->
		</form>
		
		<form action="/servidor-web/Funcion" method="POST">
		<!-- Central Modal Small (Usando Paquetes)-->
		<input id="accionDeComprar" type="hidden" value="registroPaquete" name="accionDeComprar">
		<input type="hidden" name="funcion" value="<%= funcion.getNombre() %>"/>
		<input type="hidden" name="espectaculo" value="<%= espectaculo.getNombre() %>"/>
		<input type="hidden" name="plataforma" value="<%= plataforma %>"/>
		<div class="modal fade" id="centralModalSm3" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<!-- Change class .modal-sm to change the size of the modal -->
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title w-100" id="myModalLabel">Registro a función</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>Seleccione un paquete
							<a href="#" data-toggle="tooltip"
								title="Son validos los paquetes vigentes que estén asociados a este Espectaculo">
								<i class="far fa-question-circle"></i>
							</a>
						</p>
						<div class="form-group">
			        	<select class="form-control selectpicker" name="paqueteUsado" id="paqueteUsado" data-style="btn btn-outline-primary" required>
			  				<% 
							List<PaqueteDto> paquetes = (List<PaqueteDto>) request.getAttribute("paquetes");
							for(PaqueteDto paq: paquetes){
		 	  				 %>
							<option value="<%=paq.getNombre()%>"><%=paq.getNombre()%></option>
							<% } %>
			  			</select>
			  			<br>
		  				</div>
					</div>
					<div class="modal-body" align="right">
						<button id="btnComprar" style="text-align:right" type="submit" class="btn btn btn-outline-success">Comprar</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Central Modal Small (Usando Paquetes)-->
		</form>
		<% } %>
		<% } %>
		
		<% if ((session.getAttribute("usuarioLogueado") != null) && (request.getAttribute("tipousuario").equals("artista"))) { %>
		<!-- Modal sorteo -->
		<form action="/servidor-web/DetalleFuncion" method="GET">
		<input type="hidden" name="funcion" value="<%= funcion.getNombre() %>"/>
		<input type="hidden" name="espectaculo" value="<%= espectaculo.getNombre() %>"/>
		<input type="hidden" name="plataforma" value="<%= plataforma %>"/>
		<input type="hidden" name="hacerSorteo" value="sortear"/>
		<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Premio:</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		      	<p style="white-space:normal; font-size:1rem"><%= espectaculo.getDescPremio() %> - Cantidad: <%= espectaculo.getCantPremio() %></p>
		        <p> Espectadores: </p>
		        <% List<RegistroDto> registros = (List<RegistroDto>) funcion.getRegistros();
					for(RegistroDto reg: registros){ %>
				<p> - <%= reg.getNomEspectador() %></p>
				<% } %>		        
		      </div>
		      <div class="modal-footer">
		        <button id="btnComprar" type="submit" class="btn btn btn-outline-success">Sortear</button>
		      </div>
		    </div>
		  </div>
		</div>
		</form>
		<% } %>
		
	</div>
	
	<div class="informacion row">	
	<% if (yaSorteado) { %>
	 <div class="col-2">
        <p style="font-size:20px;" class="infoLabel">Ganadores sorteo:</p>
     </div>
     <div class="col-10">
         <ul>
         	<% List<String> ganadores = (List<String>) funcion.getGanadores();
					for(String gan: ganadores){ %>
			<a href="/servidor-web/Usuarios?nickname=<%= gan %>">	
         	<li style="font-size:20px;"> <%= gan %> </li>
         	</a>
			<% } %>
         </ul>
    </div>
    <% } %>
    
    	<div class="col-2">
            <p style="font-size:20px;" class="infoLabel"> Descripción: </p>
          </div>
          <div class="col-10">
            <p style="font-size:20px;"> <%= espectaculo.getDescripcion() %> </p>
          </div>
          
          <div class="col-2">
        <p style="font-size:20px;" class="infoLabel">Artistas Invitados:</p>
    	</div>
	
	</div>
	

		
	
	
	
	<!--Carousel ArtistasInvitados-->
	<div class="container col-8 mx-4">
	    <div id="carouselPaqueteControls" class="carousel slide carousel-multi-item" data-ride="carousel">
	    
	        <!--Slides-->
	        <div class="carousel-inner" role="listbox">
	
            <!--First slide-->
            <% 
            	int slides = 0;
            	int elementosSlide = 0;
            	int invitadosAdd = 0;
            	List<UsuarioDto> lInvitados = funcion.getInvitados();
            	int cantInvitados = lInvitados.size();
            	
            	while (invitadosAdd < cantInvitados) {
            		if (slides == 0) {
            %>
			        <div class="carousel-item active">
		        <%
            		}
            		else {
		        %>
		        	<div class="carousel-item">
		        <%
            		}
		        %>
			            <div class="row">
	            	<%
	            		while( (elementosSlide < 3) && (invitadosAdd < cantInvitados) ) {
	            	%>
			            	<div class="col-md-4">
			                        <div class="card mb-0 h-100">
			                        	<a href="Usuarios?nickname=<%= lInvitados.get(invitadosAdd).getNickname() %>" class="btn btn-outline-primary stretched-link">
			                            <img style="height:200px; width:200px; border-radius:15px;" 
					                         class="card-img-top" alt="Card image cap" 
		                            		src="<%= lInvitados.get(invitadosAdd).getUrlImagen() %>" >
		                                <h6 class="card-text"> 
		                                	<%= lInvitados.get(invitadosAdd).getNombre() +" "+ lInvitados.get(invitadosAdd).getApellido() %> 
		                                </h6>
		                                </a>
			                        </div>
		                    </div>
                    <% 
                    		invitadosAdd++;
                    		elementosSlide++;
	            		}
	            		elementosSlide = 0;
	            		slides++;
                    %>
			            </div>
		            </div>
            <%
            	}
            %>
            </div>
        <!--/.Slides-->
        
		
		<% 
		if (slides > 1) { 
		%>
			<!--Controls-->
	        <a class="carousel-control-prev" href="#carouselPaqueteControls" role="button" data-slide="prev">
	            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	            <span class="sr-only">Previous</span>
	        </a>
	        <a class="carousel-control-next" href="#carouselPaqueteControls" role="button" data-slide="next">
	            <span class="carousel-control-next-icon" aria-hidden="true"></span>
	            <span class="sr-only">Next</span>
	        </a>
	        <!--/.Controls-->
	        
	        <!--Indicators-->
			<ol class="carousel-indicators">
			<% 
				for (int i = 0; i < slides; i++) { 
					if (i == 0) {
			%>
					<li data-target="#multi-item-example" data-slide-to="0" class="active"></li>
		    	<% 	
		    		}
					else {
		    	%>
		        	<li data-target="#multi-item-example" data-slide-to="i"></li>  
		       	<% 
					}
				}
		       	%>    
		    </ol>
		    <!--/.Indicators-->
        <%
		}
        %>
	    </div>
	</div>
	<br>
	<br>
	<br>
	<t:temp_bot></t:temp_bot>

</html>