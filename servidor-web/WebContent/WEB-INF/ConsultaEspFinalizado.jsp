<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="webservices.EspectadorDto" %>
<%@page import="webservices.EspectaculoDto" %>
<%@page import="webservices.FuncionDto" %>
<%@page import="webservices.PremioDto" %>
<%@page import="webservices.RegistroDto" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html lang="en">

<t:temp_top></t:temp_top>



      <!--Página-->
      
   	<c:set var="espectaculo" scope="page" value="${sessionScope.espectaculoDto}"/>
   	<h2>Datos del espectaculo</h2><br>
   	
   	<div class="tab-content" id="myTabContent">
	    <div class="tab-pane fade show active" id="tabGeneral" role="tabpanel" aria-labelledby="general-tab">
	      <div class="row">
	        <div class="col-2">
	          <p class="infoLabel">Nombre:</p>
	        </div>
	        <div class="col-10">
	          <p>${pageScope.espectaculo.getNombre()}</p>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-2">
	          <p class="infoLabel">Descripcion: </p>
	        </div>
	        <div class="col-10">
	          <p>${pageScope.espectaculo.getDescripcion()}</p>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-2">
	          <p class="infoLabel">Duracion: </p>
	        </div>
	        <div class="col-10">
	          <p>${pageScope.espectaculo.getDuracionHoras()}:${pageScope.espectaculo.getDuracionMinutos()}</p>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-2">
	          <p class="infoLabel">Costo: </p>
	        </div>
	        <div class="col-10">
	        	<p>$ ${pageScope.espectaculo.getCosto()}</p>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-2">
	          <p class="infoLabel">Fecha de alta: </p>
	        </div>
	        <div class="col-10">
	          <p> ${formater.format(pageScope.espectaculo.getFechaRegistro().toGregorianCalendar().getTime())}</p>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-2">
	          <p class="infoLabel">Fecha de finalizacion: </p>
	        </div>
	        <div class="col-10">
	          <p>${formater.format(pageScope.espectaculo.getFechaFinalizado().toGregorianCalendar().getTime())}</p>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-2">
	          <p class="infoLabel">URL: </p>
	        </div>
	        <div class="col-10">
	          <p>${pageScope.espectaculo.getUrlVideo()}</p>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-2">
	          <p class="infoLabel">Plataforma: </p>
	        </div>
	        <div class="col-10">
	          <p>${pageScope.espectaculo.getNombrePlataforma()}</p>
	        </div>
	      </div>
	    </div>
   	</div>
   	<br><br>
   	<h2>Datos de las funciones:</h2><br>
   	
   	<c:forEach var="funcion" begin="0" end="${pageScope.espectaculo.getFuncionesAsociadas().size() - 1}">
	   		<div class="tab-content" id="myTabContent">
			    <div class="tab-pane fade show active" id="tabGeneral" role="tabpanel" aria-labelledby="general-tab">
			      <div class="row">
			        <div class="col-2">
			          <p class="infoLabel"><b>Nombre:</b></p>
			        </div>
			        <div class="col-10">
			          <p>${pageScope.espectaculo.getFuncionesAsociadas().get(funcion).getNombre()}</p>
			        </div>
			      </div>
			      <div class="row">
			        <div class="col-2">
			          <p class="infoLabel">Fecha de inicio: </p>
			        </div>
			        <div class="col-10">
			          <p>${formater.format(pageScope.espectaculo.getFuncionesAsociadas().get(funcion).getFechaInicio().toGregorianCalendar().getTime())}</p>
			        </div>
			      </div>
			      <div class="row">
			        <div class="col-2">
			          <p class="infoLabel">Hora de comienzo: </p>
			        </div>
			        <div class="col-10">
			          <p>${pageScope.espectaculo.getFuncionesAsociadas().get(funcion).getHoraComienzoHs()}:${pageScope.espectaculo.getFuncionesAsociadas().get(funcion).getHoraComienzoMin()}</p>
			        </div>
			      </div>
			      <div class="row">
			        <div class="col-2">
			          <p class="infoLabel">Fecha de registro: </p>
			        </div>
			        <div class="col-10">
			        	<p>${formater.format(pageScope.espectaculo.getFuncionesAsociadas().get(funcion).getFechaRegistro().toGregorianCalendar().getTime())}</p>
			        </div>
			      </div>
			    </div>
	   		</div>
		<h5>Registros</h5>
		<c:set var="registro" scope="page" value="${pageScope.espectaculo.getFuncionesAsociadas().get(funcion).getRegistros()}"/>
		<c:if test="${registro.size() > 0 }">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Nombre de Espectador</th>
						<th scope="col">Fecha de registro</th>
						<th scope="col">Coste</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" begin="0" end="${pageScope.registro.size() - 1}">
						<tr scope="row">
						  <td scope="col">${e + 1}</td>
						  <td scope="col">${pageScope.registro.get(e).getNomEspectador()}</td>
						  <td scope="col">${formater.format(pageScope.registro.get(e).getFecha().toGregorianCalendar().getTime())}</td>
						  <td scope="col">${pageScope.registro.get(e).getCosto()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${registro.size() == 0 }">
			<h5>No hay registros para esta funcion</h5>
		</c:if>
		<br><br>
          		
   	</c:forEach>

<t:temp_bot></t:temp_bot>

<script src="../bootstrap/js/jquery-3.3.1.slim.min.js"></script>
<script src="../bootstrap/js/bootstrap.bundle.min.js"></script>
</html>