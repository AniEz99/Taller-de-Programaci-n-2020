<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="webservices.LogActividadDto"%>

<!DOCTYPE html>
<html>

	<t:temp_top></t:temp_top>
   
		<h2>Registro de actividad</h2>
		
		<table id="table" class="table table-striped table-bordered" style="width:100%">
	        <thead>
	            <tr>
	                <th>Dirección IP</th>
	                <th>URL accedida</th>
	                <th>Browser</th>
	                <th>Sistema operativo</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<% for (int i = 0; i < ((List<LogActividadDto>)request.getAttribute("logs")).size(); i++) {%>
				<tr>
                	<td><%=((List<LogActividadDto>)request.getAttribute("logs")).get(i).getIpAddr()%></td>
                	<td><%=((List<LogActividadDto>)request.getAttribute("logs")).get(i).getUrl()%></td>
                	<td><%=((List<LogActividadDto>)request.getAttribute("logs")).get(i).getBrowser()%></td>
                	<td><%=((List<LogActividadDto>)request.getAttribute("logs")).get(i).getOpSis()%></td>
            	</tr>
            	<%
	        	}
            	%>
	        </tbody>
	    </table>
	        
		
		

  	<t:temp_bot></t:temp_bot>
  	
	  	
	<script type="text/javascript">
	
		$(document).ready(function() {
	    	$('#table').DataTable({
	    		language: {
	    	        search:        "Buscar&nbsp;:",
	    	        lengthMenu:    "Mostrando _MENU_ registros",
	    	        info:           "Mostrando del _START_ al _END_ de _TOTAL_ registros",
	    	        infoEmpty:      "Mostrando 0 registros",
	    	        infoFiltered:   "(filtrados de _MAX_ registros totales)",
	    	        infoPostFix:    "",
	    	        loadingRecords: "Cargando...",
	    	        zeroRecords:    "No hay registros disponibles",
	    	        emptyTable:     "No hay registros disponibles",
	    	        paginate: {
	    	            first:      "Primera",
	    	            previous:   "Anterior",
	    	            next:       "Siguiente",
	    	            last:       "Ultima"
	    	        }
	    		}
	    	});
	    	console.log("HOLA");
		} );
	</script>



</html>