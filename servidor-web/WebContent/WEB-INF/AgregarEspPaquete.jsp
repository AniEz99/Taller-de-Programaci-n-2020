<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page import="webservices.PaqueteDto" %>
<%@page import="webservices.EspectaculoDto" %>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>

	<t:temp_top></t:temp_top>
	
    <div class="col-9">
      	<div class="container p-3 my-3 border">
      		<h4 class="text-center">Elija el espectaculo:</h4>
	      <br><br>
	      
	      
	        <form action = AgregarEspPaquete method ="post">
	        	<div class="form-group">
		        	<label>Selecciona que espectaculo de la plataforma <%=request.getAttribute("plataforma")%> quieres agregar al paquete <%=request.getAttribute("paquete")%></label>
		        	<select class="form-control" name="espectaculo">
		        	<% 
		          		List<String> espectaculos = new LinkedList<String>();
						espectaculos = (List<String>) request.getAttribute("espectaculos");
		          		for(String plat: espectaculos){%>
				        	<option><%=plat%></option>
				       <% }%>
			
		  			</select><br>
		  			<input type="hidden" value="<%=request.getAttribute("plataforma")%>" name="plataforma">
		  			<input type="hidden" value="<%=request.getAttribute("paquete")%>" name="paquete">
		  			<input type="submit" class="btn btn-success">
		  		</div>
	        </form>
	    </div>   
      </div>

  
   

  <t:temp_bot></t:temp_bot>

<script src="../bootstrap/js/jquery-3.3.1.slim.min.js"></script>
<script src="../bootstrap/js/bootstrap.bundle.min.js"></script>
</html>