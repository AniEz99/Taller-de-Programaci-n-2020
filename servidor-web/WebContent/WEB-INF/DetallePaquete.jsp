<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page import="webservices.PaqueteDto" %>
<%@page import="webservices.EspectaculoDto" %>
<%@page import="webservices.ArtistaDto" %>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>

	<t:temp_top></t:temp_top>
	
	<link href="${pageContext.request.contextPath}/css/detallePaquete.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/carousel.css" rel="stylesheet" type="text/css">
	
        <div class="presentacion">
        	<% PaqueteDto paq = (PaqueteDto) request.getAttribute("paquete"); %>
        	<% Boolean comprado = (Boolean) request.getAttribute("paqueteComprado"); %>
			<% List<EspectaculoDto> espectaculos = (List<EspectaculoDto>) request.getAttribute("espDePaquete"); %>
			<% SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); %>
			
          <img src="<%= paq.getUrlImagen() %>" class="imgPaquete">
          <p class="nombre"> <%= paq.getNombre() %> </p>
          
          <% if ((session.getAttribute("usuarioLogueado") != null) && (request.getAttribute("tipousuario").equals("espectador"))) { %>
          
          <% if (!comprado) { %>
          	<form action="/servidor-web/DetallePaquete" method="POST">
          		<input type="hidden" name="paquete" value="<%= paq.getNombre() %>"/>
          		<button id="btnComprar" type="submit" class="btn btn btn-outline-success">Comprar</button>
          	</form>
          <% } else {%>
          	<p id="pComprado">Ya compraste este paquete.</p>
          <% } %>
          
          <% } %>
          
        </div>
        <div class="informacion row">
          <div class="col-2">
            <p style="font-size:20px;" class="infoLabel"> Descripción: </p>
          </div>
          <div class="col-10">
            <p style="font-size:20px;"> <%= paq.getDescripcion() %> </p>
          </div>
          <div class="col-2">
            <p style="font-size:20px;" class="infoLabel">Descuento:</p>
          </div>
          <div class="col-10">
            <p style="font-size:20px;"> <%= paq.getDescuento() %> % </p>
          </div>
          <div class="col-2">
            <p style="font-size:20px;" class="infoLabel">Periodo:</p>
          </div>
          <div class="col-10">
            <p style="font-size:20px;">
            	<%= formateador.format(paq.getFechaInicio().toGregorianCalendar().getTime()) %> 
            	- 
            	<%= formateador.format(paq.getFechaFin().toGregorianCalendar().getTime()) %> 
            </p>
          </div>
          <div class="col-2">
              <p style="font-size:20px;" class="infoLabel">Categorías:</p>
          </div>
          <div class="col-10">
              <ul>
              	<% 
              		List<String> cats = (List<String>) request.getAttribute("catsPaq");
              		for (String aux : cats) {
              	%>
              	<li style="font-size:20px;"> <%= aux %> </li>
				<% } %>
              </ul>
          </div>
          <div class="col-2">
            <p style="font-size:20px;" class="infoLabel">Espectáculos: <br></p>
          </div>
           <script type="text/javascript">
          	function platFormSetVisible(){
          		document.getElementById("platForm").style.visibility = "visible";
          	}
          </script>
          <% if ((session.getAttribute("usuarioLogueado") != null) && session.getAttribute("usuarioLogueado") instanceof ArtistaDto) {%>
          <div>
            <button id="btnComprar" type="button" class="btn btn-success" onClick="platFormSetVisible()">Agregar</button>
          </div>
          <div>
          	<form action="AgregarEspPaquete" id="platForm" method ="get" style="padding-top: 10px ">
          		<label>Selecciona Plataforma:</label>
          		<select name="plataforma" class="selectpicker">
          		<% 
          		List<String> platafomras = new LinkedList<String>();
          		platafomras = (List<String>) request.getAttribute("plataformas");
          		for(String plat: platafomras){%>
		        	<option><%=plat%></option>
		       <% }%>
          		</select>
          		<input type="hidden" name="paquete" value="<%=paq.getNombre()%>">
          		<input type="Submit" class="btn btn-success" >
          	</form>
          	<script type="text/javascript">document.getElementById("platForm").style.visibility = "hidden";</script>
          </div>
		</div>
		<% } %>
        <!-- Carousel Espectaculos Asociados -->
		<div class="container col-8 mx-4">
	    	<div id="carouselPaqueteControls" class="carousel slide carousel-multi-item" data-ride="carousel">	
		    
		        <!--Slides-->
	        	<div class="carousel-inner" role="listbox">
		
	            <!--First slide-->
	            <% 
	            	int slides = 0;
	            	int elementosSlide = 0;
	            	int espectaculosAdd = 0;
	            	int cantEspectaculos = espectaculos.size();
	            	
	            	while (espectaculosAdd < cantEspectaculos) {
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
		            		while( (elementosSlide < 3) && (espectaculosAdd < cantEspectaculos) ) {
		            	%>
				            	<div class="col-md-4">
				                        <div class="card mb-0 h-100">
				                        	<a href="DetalleEspectaculo?espectaculo=<%=espectaculos.get(espectaculosAdd).getNombre()%>&plataforma=<%=espectaculos.get(espectaculosAdd).getNombrePlataforma()%>" class="btn btn-outline-primary stretched-link">
				                            <img style="height:200px; width:200px; border-radius:15px;" 
				                            	class="card-img-top" alt="Card image cap" 
			                            		src="<%= espectaculos.get(espectaculosAdd).getUrlImagen() %>" >
			                                <h6 class="card-text"> 
			                                	<%= espectaculos.get(espectaculosAdd).getNombre() %> 
			                                </h6>
			                                </a>
				                        </div>
			                    </div>
	                    <% 
	                    		espectaculosAdd++;
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
      
      <t:temp_bot></t:temp_bot>
      
</html>