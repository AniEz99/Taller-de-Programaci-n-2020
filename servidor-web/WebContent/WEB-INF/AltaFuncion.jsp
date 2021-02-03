<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="webservices.EspectaculoDto"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.LinkedList"%>
<%@ taglib prefix="t" tagdir= "/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!doctype html>
<html lang="en">

<t:temp_top></t:temp_top>



      <!--Página-->
      <div class="col-9">
      	<div class="container p-3 my-3 border">
      		<h4 class="text-center">Ingrese los datos de la funcion</h4>
	      <br><br>
	      
	      
	        <form action = "AltaFuncion" method ="post" enctype="multipart/form-data">
	        	<div class="form-group">
		        	<label>Selecciona uno de tus espectaculos aceptados</label>
		        	<select class="form-control" name="espectaculo">
		        	<%
		        		List<EspectaculoDto> espectaculosDelArtista = new LinkedList<EspectaculoDto>();
		        		espectaculosDelArtista = (List<EspectaculoDto>) request.getAttribute("espectaculos");
		        		for(int i = 0; i < espectaculosDelArtista.size(); i++ ){%>
		        	
		        			<option><%=espectaculosDelArtista.get(i).getNombre()%></option>
		        		<% }%>
		  			</select><br>
		  		</div>
		  		<div class="form-group">	
		  			<label for="nombreFuncion">Nombre de la funcion: </label>
		  			<input type="text" id="nombreFuncion" name="nombreFuncion" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"><br>
		  		</div>
		  		<div class="form-group">
		  			<label for="fechaFuncion">Fecha de la funcion: </label>
		  			<input required type="date" id="fechaFuncion" name="fechaFuncion" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" "><br>
		  		</div>
		  		<div class="form-group">
		  			<label for="horaFuncion">Ingresar hora:</label>
		  			<div class="input-group input-group-sm">
		  				<div class="input-group-prepend">
	          				<div class="input-group-text">Hs: </div>
	        			</div>
	        			<input class="form-control form-control-sm" type="number" id="durEspectaculoH" name="horas" min="0" max="24" value="0" required>
		  			</div>
		  			
		  			<div class="input-group input-group-sm mt-2">
		  				<div class="input-group-prepend ">
	          				<div class="input-group-text">Min: </div>
	        			</div>
	        			<input type="number" style ="width: 100px" class ="form-control form-control-sm" id="durEspectaculoM" name="minutos" min="0" max="60" value="0" required > 
		  		    </div><br>
		  		</div>
		  		<div class="form-group">
		  			<label for="artistas" >Seleccione artistas invitados:</label><br>
		  			<select class="selectpicker" name="invitados" multiple data-live-search="true" multiple data-style="btn-primary" data-size="3" >
		  			 <%
		        		List<String> artistas = (List<String>) request.getAttribute("artistas");
		        		for(int i = 0; i < artistas.size(); i++ ){%>
		        	
		        			<option><%= artistas.get(i)%></option>
		        		<% }%>
		  			</select><br><br>
		  		</div>
		  		<div class="form-group">
		  			<div class="custom-file">
                    	<input type="file" class="custom-file-input" id="imagen" name="imagen">
                    	<label class="custom-file-label" for="imagen">Elije una imagen</label>
                  </div>

		  		</div>
		  		<div class="form-group">
		  			<button id="btnComprar" type="submit" class="btn btn-primary" >Confirmar Alta</button>
		  		</div>
	        </form>
	    </div>   
      </div>

  
   

  <t:temp_bot></t:temp_bot>

<script src="../bootstrap/js/jquery-3.3.1.slim.min.js"></script>
<script src="../bootstrap/js/bootstrap.bundle.min.js"></script>
</html>
