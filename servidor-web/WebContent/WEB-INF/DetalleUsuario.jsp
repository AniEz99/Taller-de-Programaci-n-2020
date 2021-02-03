<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="webservices.EspectadorDto" %>
<%@page import="webservices.PremioDto" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>

<t:temp_top></t:temp_top>

	<link href="${pageContext.request.contextPath}/css/DetalleUsuario.css" rel="stylesheet" type="text/css">
	
	<c:if test="${!sessionScope.usuarioConsultadoEsArtista}">

          <div class="presentacion">
            <img src="${sessionScope.espectadorConsultado.getUrlImagen()}" class="imgPerfil">
            <p class="nombre"> ${sessionScope.espectadorConsultado.getNombre()} ${sessionScope.espectadorConsultado.getApellido()}
            <c:if test="${sessionScope.esMiPerfil != null && sessionScope.usuarioLogueado != null}">
              <button type="button" class="btn btn-outline-light text-dark" data-toggle="modal"
                data-target="#editar_perfil">
                <i class="far fa-edit"></i>
              </button>
              </c:if>
            </p>
            <p> ${sessionScope.espectadorConsultado.getNickname()} - ${sessionScope.espectadorConsultado.getCorreo() } </p>
            <c:if test="${sessionScope.esMiPerfil == null && sessionScope.usuarioLogueado != null}">
            <form action="Seguidores" method="POST">
            	<input type="hidden" value="${sessionScope.usuarioLogueado.getNickname()}" name="seguidor">
            	<input type="hidden" value="${sessionScope.espectadorConsultado.getNickname()}" name="seguido">
            	<input id="hdnAccionSeguir" type="hidden" value="" name="accion">
            	<c:if test="${sessionScope.yaLoSigue == null }">
            		<button id="btnSeguir" onclick="document.getElementById('hdnAccionSeguir').value = 'seguir';" 
            			type="submit" class="btn btn-outline-primary btnSeguir">Seguir</button>
            	</c:if>
            	<c:if test="${sessionScope.yaLoSigue != null }">
            		<button id="btnDejarSeguir" onclick="document.getElementById('hdnAccionSeguir').value = 'dejarDeSeguir';" 
            			type="submit" class="btn btn-outline-danger btnSeguir">Dejar de seguir</button>
            	</c:if>
            </form>
            </c:if>
          </div>

          <div class="tabsContainer">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
              <li class="nav-item">
                <a class="nav-link active" id="general-tab" data-toggle="tab" href="#tabGeneral" role="tab"
                  aria-controls="tabGeneral" aria-selected="true">General</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="registros-tab" data-toggle="tab" href="#tabRegistros" role="tab"
                  aria-controls="tabRegistros" aria-selected="false">Registros</a>
              </li>
              <c:if test="${sessionScope.esMiPerfil != null}">
              <li class="nav-item">
                <a class="nav-link" id="paquetes-tab" data-toggle="tab" href="#tabPaquetes" role="tab"
                  aria-controls="tabPaquetes" aria-selected="false">Paquetes</a>
              </li>
              </c:if>
              <li class="nav-item">
                <a class="nav-link" id="seguidores-tab" data-toggle="tab" href="#tabSeguidores" role="tab"
                  aria-controls="tabSeguidores" aria-selected="false">Seguidores</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="seguidos-tab" data-toggle="tab" href="#tabSeguidos" role="tab"
                  aria-controls="tabSeguidos" aria-selected="false">Seguidos</a>
              </li>
              <c:if test="${sessionScope.esMiPerfil != null}">
              <li class="nav-item">
                <a class="nav-link" id="premios-tab" data-toggle="tab" href="#tabPremios" role="tab"
                  aria-controls="tabPremios" aria-selected="false">Premios</a>
              </li>
              </c:if>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active" id="tabGeneral" role="tabpanel" aria-labelledby="general-tab">
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Nombre: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.espectadorConsultado.getNombre()} ${sessionScope.espectadorConsultado.getApellido()}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Nickname: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.espectadorConsultado.getNickname()}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Correo: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.espectadorConsultado.getCorreo()}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Fecha de nacimiento: </p>
                  </div>
                  <div class="col-10">
                  	<p>${formater.format(sessionScope.espectadorConsultado.getFechaNacimiento().toGregorianCalendar().getTime())}</p>
                  </div>
                </div>
              </div>
              
              <c:if test="${sessionScope.esMiPerfil != null}">
              <div class="tab-pane fade" id="tabPremios" roles="tabpanel" aria-labelledby="premios-tab">
	              <% SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); %>
	           	  <% EspectadorDto espect = (EspectadorDto) request.getAttribute("espectadorCons"); %>
	           	  <% List<PremioDto> premios = espect.getPremiosGanados(); %>
	           	  <% if (!premios.isEmpty()) { %>
		            <% for (PremioDto pre: premios) { %>
		            	<div class="row">
		                  <div class="col-2">
		                  	<strong>
		                    <li style="font-size:20px;"> Premio: </li>
		                    </strong>
		                  </div>
		                  <div class="col-10">
		                    <p><%=pre.getNombrePremio()%></p>
		                  </div>
		                </div>
		                <div class="row">
		                  <div class="col-2">
		                    <p class="infoLabel">Fecha ganado: </p>
		                  </div>
		                  <div class="col-10">
		                    <p><%=formateador.format(pre.getFechaPremio().toGregorianCalendar().getTime()) %></p>
		                  </div>
		                </div>
		                <div class="row">
		                  <div class="col-2">
		                    <p class="infoLabel">Funcion asociada: </p>
		                  </div>
		                  <div class="col-10">
		                  <a href="DetalleFuncion?plataforma=<%= pre.getPlatAsociada() %>&espectaculo=<%= pre.getEspAsociado() %>&funcion=<%= pre.getFuncAsociada() %>"><%= pre.getFuncAsociada() %></a>
		                  </div>
		                </div>
		                <div class="row">
		                  <div class="col-2">
		                    <p class="infoLabel">Espectaculo asociado: </p>
		                  </div>
		                  <div class="col-10">
		                    <a href="DetalleEspectaculo?plataforma=<%= pre.getPlatAsociada() %>&espectaculo=<%= pre.getEspAsociado() %>"><%= pre.getEspAsociado() %></a>
		                  </div>
		                </div>
		                <div>
		                <% Date dt = pre.getFechaPremio().toGregorianCalendar().getTime();
		                  Calendar c = Calendar.getInstance();
		               	  c.setTime(dt);
		                  c.add(Calendar.DATE, 30);
		                  dt = c.getTime(); %>
		                <a href="/servidor-web/descargarPDF?espectador=<%=espect.getNombre()%>&espectaculo=<%= pre.getEspAsociado() %>&funcion=<%= pre.getFuncAsociada() %>&premio=<%=pre.getNombrePremio()%>&fecha=<%=formateador.format(pre.getFechaPremio().toGregorianCalendar().getTime()) %>&fechaFin=<%=formateador.format(dt)%>">Descargar comprobante</a>
		                </div>
		                <br>
		            <% } %>
	            <% } else {%>
	                <br>
	            	<p class="infoLabel">Aun no ganaste ningun premio, la proxima sale</p>
	            <% } %>
            </div>
            </c:if>
              
              <div class="tab-pane fade" id="tabRegistros" role="tabpanel" aria-labelledby="registros-tab">
                <div class="container carousel-inner no-padding">

                  <div id="demo" class="carousel slide" data-ride="carousel">
                  
                  	<c:set var="funciones" scope="page" value="${sessionScope.espectadorConsultado.getFunciones()}"/>
                  	<c:set var="cantFunciones" scope="page" value="${pageScope.funciones.size()}"/>
                  	<c:set var="cantPaginasFunciones" scope="page" value="${pageScope.cantFunciones % 3 == 0 ? pageScope.cantFunciones / 3 : pageScope.cantFunciones / 3 + 1 }"/>
                    
                    
                    <c:if test="${cantFunciones > 0 }">
                  	<c:forEach var="paginaFunciones" begin="0" end="${pageScope.cantPaginasFunciones - 1}">
                  		<c:if test="${paginaFunciones == 0 }">
                  		<div class="carousel-item active">
                  		</c:if>
                  		<c:if test="${paginaFunciones != 0 }">
                  		<div class="carousel-item">
                  		</c:if>
                  			
                  			<c:forEach var="f" begin="${paginaFunciones * 3}" end="${paginaFunciones * 3 + 2 < pageScope.cantFunciones ? paginaFunciones * 3 + 2 : pageScope.cantFunciones - 1}">
                  				<a href="DetalleFuncion?plataforma=${pageScope.funciones[f].getNombrePlat()}&espectaculo=${pageScope.funciones[f].getNombreEsp()}&funcion=${pageScope.funciones[f].getNombreFunc()}">
                        			<div class="col-xs-4 col-sm-4 col-md-4">
                         				<img src="${port.seleccionarFuncion(pageScope.funciones[f].getNombreFunc(), pageScope.funciones[f].getNombreEsp(), pageScope.funciones[f].getNombrePlat()).getImagen() }">
										<p>${pageScope.funciones[f].getNombreFunc()}</p>
                        			</div>
                      			</a>
                  			</c:forEach>
                  			
                  		</div>
                  		              		
                  	</c:forEach>

                    <ul class="carousel-indicators">
                    	<c:forEach var="indF" begin="0" end="${pageScope.cantPaginasFunciones - 1}">
                    		<c:if test="${indF == 0}">
                    			<li data-target="#demo" data-slide-to="${indF}" class="active"></li>
                    		</c:if>	
                    		<c:if test="${indF != 0}">
                    			<li data-target="#demo" data-slide-to="${indF}" ></li>
                    		</c:if>	
                    	</c:forEach>
                    </ul>
	                </c:if>
                  </div>
                  
                </div>
              </div>
              <c:if test="${sessionScope.esMiPerfil != null}">
              <div class="tab-pane fade" id="tabPaquetes" role="tabpanel" aria-labelledby="paquetes-tab">
                
                <div class="container carousel-inner no-padding">

                  <div id="demo" class="carousel slide" data-ride="carousel">
                  
                  	<c:set var="paquetes" scope="page" value="${sessionScope.espectadorConsultado.getPaquetes()}"/>
                  	<c:set var="cantPaquetes" scope="page" value="${pageScope.paquetes.size()}"/>
                  	<c:set var="cantPaginasPaquetes" scope="page" value="${pageScope.cantPaquetes % 3 == 0 ? pageScope.cantPaquetes / 3 : pageScope.cantPaquetes / 3 + 1 }"/>
                    
                    <c:if test="${cantPaquetes > 0 }">
                  	<c:forEach var="paginaPaquetes" begin="0" end="${pageScope.cantPaginasPaquetes - 1}">
                  		<c:if test="${paginaPaquetes == 0 }">
                  		<div class="carousel-item active">
                  		</c:if>
                  		<c:if test="${paginaPaquetes != 0 }">
                  		<div class="carousel-item">
                  		</c:if>
                  		
                  			<c:forEach var="p" begin="${paginaPaquetes * 3}" end="${paginaPaquetes * 3 + 2 < pageScope.cantPaquetes ? paginaPaquetes * 3 + 2 : pageScope.cantPaquetes - 1}">
                  				<a href="DetallePaquete?paquete=${pageScope.paquetes[p]}">
                        			<div class="col-xs-4 col-sm-4 col-md-4">
                         				<img src="${port.getPaquete(paquetes[p]).getUrlImagen()}">
										<p>${pageScope.paquetes[p]}</p>
                        			</div>
                      			</a>
                  			</c:forEach>
                  			
                  		</div>
                  		              		
                  	</c:forEach>

                    <ul class="carousel-indicators">
                    	<c:forEach var="indP" begin="0" end="${pageScope.cantPaginasPaquetes - 1}">
                    		<c:if test="${indP == 0}">
                    			<li data-target="#demo" data-slide-to="${indP}" class="active"></li>
                    		</c:if>	
                    		<c:if test="${indP != 0}">
                    			<li data-target="#demo" data-slide-to="${indP}" ></li>
                    		</c:if>	
                    	</c:forEach>
                    </ul>
	                </c:if>
                  </div>
                </div>
              </div>
              </c:if>
              <div class="tab-pane fade" id="tabSeguidores" roles="tabpanel" aria-labelledby="seguidores-tab">
              
              	<div class="list-group">
              		<c:forEach var="seguidor" items="${ sessionScope.espectadorConsultado.getSeguidores() }" >
              		
              			<a href="Usuarios?nickname=${ seguidor }" class="list-group-item list-group-item-action flex-column align-items-start">
                    		<div class="row">
	                      		<img src="${port.obtenerUsuario(seguidor).getUrlImagen()}" class="col-3" alt="">
	                      		<div class="col-9 right">
	                        		<div class="d-flex w-100 justify-content-between">
	                         			 <h5 class="mb-1">${ seguidor }</h5>
	                        		</div>
	                      		</div>
	                    	</div>
                  		</a>
              		
              		</c:forEach>
              	</div>                
        
              </div>
              <div class="tab-pane fade" id="tabSeguidos" roles="tabpanel" aria-labelledby="seguidos-tab">
                <div class="list-group">
                  <c:forEach var="seguido" items="${ sessionScope.espectadorConsultado.getSeguidos() }" >
              		
              			<a href="Usuarios?nickname=${ seguido }" class="list-group-item list-group-item-action flex-column align-items-start">
                    		<div class="row">
	                      		<img src="${port.obtenerUsuario(seguido).getUrlImagen()}" class="col-3" alt="">
	                      		<div class="col-9 right">
	                        		<div class="d-flex w-100 justify-content-between">
	                         			 <h5 class="mb-1">${ seguido }</h5>
	                        		</div>
	                      		</div>
	                    	</div>
                  		</a>
              		
              		</c:forEach>
              </div>
            </div>
          </div>
        </c:if>
        
        
        <c:if test="${sessionScope.usuarioConsultadoEsArtista}">

          <div class="presentacion">
            <img src="${sessionScope.artistaConsultado.getUrlImagen()}" class="imgPerfil">
            <p class="nombre"> ${sessionScope.artistaConsultado.getNombre()} ${sessionScope.artistaConsultado.getApellido()}
            <c:if test="${sessionScope.esMiPerfil != null && sessionScope.usuarioLogueado != null}">
           <button type="button" class="btn btn-outline-light text-dark" data-toggle="modal"
                data-target="#editar_perfil">
                <i class="far fa-edit"></i>
              </button>
              </c:if>
            </p>
            <p> ${sessionScope.artistaConsultado.getNickname()} - ${sessionScope.artistaConsultado.getCorreo() } </p>
            <c:if test="${sessionScope.esMiPerfil == null && sessionScope.usuarioLogueado != null}">
            <form action="Seguidores" method="POST">
            	<input type="hidden" value="${sessionScope.usuarioLogueado.getNickname()}" name="seguidor">
            	<input type="hidden" value="${sessionScope.artistaConsultado.getNickname()}" name="seguido">
            	<input id="hdnAccionSeguir" type="hidden" value="" name="accion">
            	<c:if test="${sessionScope.yaLoSigue == null }">
            		<button id="btnSeguir" onclick="document.getElementById('hdnAccionSeguir').value = 'seguir';" 
            			type="submit" class="btn btn-outline-primary btnSeguir">Seguir</button>
            	</c:if>
            	<c:if test="${sessionScope.yaLoSigue != null }">
            		<button id="btnDejarSeguir" onclick="document.getElementById('hdnAccionSeguir').value = 'dejarDeSeguir';" 
            			type="submit" class="btn btn-outline-danger btnSeguir">Dejar de seguir</button>
            	</c:if>
            </form>
            </c:if>
          </div>
          
          <div class="tabsContainer">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
              <li class="nav-item">
                <a class="nav-link active" id="general-tab" data-toggle="tab" href="#tabGeneral" role="tab"
                  aria-controls="tabGeneral" aria-selected="true">General</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="espectaculos-tab" data-toggle="tab" href="#tabEspectaculos" role="tab"
                  aria-controls="tabRegistros" aria-selected="false">Espectaculos</a>
              </li>
              <c:if test="${sessionScope.esMiPerfil != null}">
              <li class="nav-item">
                <a class="nav-link" id="espectaculosNoAceptados-tab" data-toggle="tab" href="#tabEspectaculosNoAceptados" role="tab"
                  aria-controls="tabEspectaculosNoAceptados" aria-selected="false">Espectaculos sin aceptar</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="espectaculosFinalizados-tab" data-toggle="tab" href="#tabEspectaculosFinalizados" role="tab"
                  aria-controls="tabEspectaculosFinalizados" aria-selected="false">Espectaculos finalizados</a>
              </li>
              </c:if>
              <li class="nav-item">
                <a class="nav-link" id="seguidores-tab" data-toggle="tab" href="#tabSeguidores" role="tab"
                  aria-controls="tabSeguidores" aria-selected="false">Seguidores</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="seguidos-tab" data-toggle="tab" href="#tabSeguidos" role="tab"
                  aria-controls="tabSeguidos" aria-selected="false">Seguidos</a>
              </li>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active" id="tabGeneral" role="tabpanel" aria-labelledby="general-tab">
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Nombre: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.artistaConsultado.getNombre()} ${sessionScope.artistaConsultado.getApellido()}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Nickname: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.artistaConsultado.getNickname()}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Correo: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.artistaConsultado.getCorreo()}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Fecha de nacimiento: </p>
                  </div>
                  <div class="col-10">
                  	<p>${formater.format(sessionScope.artistaConsultado.getFechaNacimiento().toGregorianCalendar().getTime())}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Descripción: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.artistaConsultado.getDescripcion()}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Biografía: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.artistaConsultado.getBiografia()}</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-2">
                    <p class="infoLabel">Sitio web: </p>
                  </div>
                  <div class="col-10">
                    <p>${sessionScope.artistaConsultado.getSitioWeb()}</p>
                  </div>
                </div>
              </div>
              
              <div class="tab-pane fade" id="tabEspectaculos" role="tabpanel" aria-labelledby="espectaculos-tab">
                <div class="container carousel-inner no-padding">

                  <div id="demo" class="carousel slide" data-ride="carousel">
                  
                  	<c:set var="espectaculos" scope="page" value="${sessionScope.espectaculosDelArtista}"/>
                  	<c:set var="cantEspectaculos" scope="page" value="${pageScope.espectaculos.size()}"/>
                  	<c:set var="cantPaginasEspectaculos" scope="page" value="${pageScope.cantEspectaculos % 3 == 0 ? pageScope.cantEspectaculos / 3 : pageScope.cantEspectaculos / 3 + 1 }"/>
                    
                    
                    <c:if test="${cantEspectaculos > 0 }">
                  	<c:forEach var="paginaEspectaculos" begin="0" end="${pageScope.cantPaginasEspectaculos - 1}">
                  		<c:if test="${paginaEspectaculos == 0 }">
                  		<div class="carousel-item active">
                  		</c:if>
                  		<c:if test="${paginaEspectaculos != 0 }">
                  		<div class="carousel-item">
                  		</c:if>
                  			
                  			<c:forEach var="e" begin="${paginaEspectaculos * 3}" end="${paginaEspectaculos * 3 + 2 < pageScope.cantEspectaculos ? paginaEspectaculos * 3 + 2 : pageScope.cantEspectaculos - 1}">
                  				<a href="DetalleEspectaculo?plataforma=${pageScope.espectaculos[e].getNomPlat()}&espectaculo=${pageScope.espectaculos[e].getNomEsp()}">
                        			<div class="col-xs-4 col-sm-4 col-md-4">
                         				<img src="${port.obtenerDatosEspectaculo(pageScope.espectaculos[e].getNomPlat(), pageScope.espectaculos[e].getNomEsp()).getUrlImagen() }">
										<p>${pageScope.espectaculos[e].getNomEsp()}</p>
                        			</div>
                      			</a>
                  			</c:forEach>
                  			
                  		</div>
                  		              		
                  	</c:forEach>

                    <ul class="carousel-indicators">
                    	<c:forEach var="indE" begin="0" end="${pageScope.cantPaginasEspectaculos - 1}">
                    		<c:if test="${indE == 0}">
                    			<li data-target="#demo" data-slide-to="${indE}" class="active"></li>
                    		</c:if>	
                    		<c:if test="${indE != 0}">
                    			<li data-target="#demo" data-slide-to="${indE}" ></li>
                    		</c:if>	
                    	</c:forEach>
                    </ul>
	                </c:if>
                  </div>
                  
                </div>
              </div>
              <c:if test="${sessionScope.esMiPerfil != null}">
              <div class="tab-pane fade" id="tabEspectaculosNoAceptados" role="tabpanel" aria-labelledby="espectaculosNoAceptados-tab">
              
              	<h2>Espectaculos ingresados:</h2>
              	
                <div class="container carousel-inner no-padding">
					
                  <div id="demo" class="carousel slide" data-ride="carousel">
                  
                  	<c:set var="espectaculosIngresados" scope="page" value="${sessionScope.espectaculosIngresadosDelArtista}"/>
                  	<c:set var="cantEspectaculosIngresados" scope="page" value="${pageScope.espectaculosIngresados.size()}"/>
                  	<c:set var="cantPaginasEspectaculosIngresados" scope="page" value="${pageScope.cantEspectaculos % 3 == 0 ? pageScope.cantEspectaculosIngresados / 3 : pageScope.cantEspectaculosIngresados / 3 + 1 }"/>
                    
                    
                    <c:if test="${cantEspectaculosIngresados > 0 }">
                  	<c:forEach var="paginaEspectaculosIngresados" begin="0" end="${pageScope.cantPaginasEspectaculosIngresados - 1}">
                  		<c:if test="${paginaEspectaculosIngresados == 0 }">
                  		<div class="carousel-item active">
                  		</c:if>
                  		<c:if test="${paginaEspectaculosIngresados != 0 }">
                  		<div class="carousel-item">
                  		</c:if>
                  			
                  			<c:forEach var="e" begin="${paginaEspectaculosIngresados * 3}" end="${paginaEspectaculosIngresados * 3 + 2 < pageScope.cantEspectaculosIngresados ? paginaEspectaculosIngresados * 3 + 2 : pageScope.cantEspectaculosIngresados - 1}">
                  				<a href="DetalleEspectaculo?plataforma=${pageScope.espectaculosIngresados[e].getNomPlat()}&espectaculo=${pageScope.espectaculosIngresados[e].getNomEsp()}">
                        			<div class="col-xs-4 col-sm-4 col-md-4">
                         				<img src="${port.obtenerDatosEspectaculo(pageScope.espectaculosIngresados[e].getNomPlat(), pageScope.espectaculosIngresados[e].getNomEsp()).getUrlImagen() }">
										<p>${pageScope.espectaculosIngresados[e].getNomEsp()}</p>
                        			</div>
                      			</a>
                  			</c:forEach>
                  			
                  		</div>
                  		              		
                  	</c:forEach>

                    <ul class="carousel-indicators">
                    	<c:forEach var="indEin" begin="0" end="${pageScope.cantPaginasEspectaculosIngresados - 1}">
                    		<c:if test="${indEin == 0}">
                    			<li data-target="#demo" data-slide-to="${indEin}" class="active"></li>
                    		</c:if>	
                    		<c:if test="${indEin != 0}">
                    			<li data-target="#demo" data-slide-to="${indEin}" ></li>
                    		</c:if>	
                    	</c:forEach>
                    </ul>
	                </c:if>
                  </div>
                  
                </div>
              
              <hr>
              
              	<h2>Espectaculos rechazados:</h2>
              	
                <div class="container carousel-inner no-padding">
					
                  <div id="demo" class="carousel slide" data-ride="carousel">
                  	
                  	<c:set var="espectaculosRechazados" scope="page" value="${sessionScope.espectaculosRechazadosDelArtista}"/>
                  	<c:set var="cantEspectaculosRechazados" scope="page" value="${pageScope.espectaculosRechazados.size()}"/>
                  	<c:set var="cantPaginasEspectaculosRechazados" scope="page" value="${pageScope.cantEspectaculos % 3 == 0 ? pageScope.cantEspectaculosRechazados / 3 : pageScope.cantEspectaculosRechazados / 3 + 1 }"/>
                    
                    
                    <c:if test="${cantEspectaculosRechazados > 0 }">
                  	<c:forEach var="paginaEspectaculosRechazados" begin="0" end="${pageScope.cantPaginasEspectaculosRechazados - 1}">
                  		<c:if test="${paginaEspectaculosRechazados == 0 }">
                  		<div class="carousel-item active">
                  		</c:if>
                  		<c:if test="${paginaEspectaculosRechazados != 0 }">
                  		<div class="carousel-item">
                  		</c:if>
                  			
                  			<c:forEach var="e" begin="${paginaEspectaculosRechazados * 3}" end="${paginaEspectaculosRechazados * 3 + 2 < pageScope.cantEspectaculosRechazados ? paginaEspectaculosRechazados * 3 + 2 : pageScope.cantEspectaculosRechazados - 1}">
                  				<a href="DetalleEspectaculo?plataforma=${pageScope.espectaculosRechazados[e].getNomPlat()}&espectaculo=${pageScope.espectaculosRechazados[e].getNomEsp()}">
                        			<div class="col-xs-4 col-sm-4 col-md-4">
                         				<img src="${port.obtenerDatosEspectaculo(pageScope.espectaculosRechazados[e].getNomPlat(), pageScope.espectaculosRechazados[e].getNomEsp()).getUrlImagen() }">
										<p>${pageScope.espectaculosRechazados[e].getNomEsp()}</p>
                        			</div>
                      			</a>
                  			</c:forEach>
                  			
                  		</div>
                  		              		
                  	</c:forEach>

                    <ul class="carousel-indicators">
                    	<c:forEach var="indEre" begin="0" end="${pageScope.cantPaginasEspectaculosRechazados - 1}">
                    		<c:if test="${indEre == 0}">
                    			<li data-target="#demo" data-slide-to="${indEre}" class="active"></li>
                    		</c:if>	
                    		<c:if test="${indEre != 0}">
                    			<li data-target="#demo" data-slide-to="${indEre}" ></li>
                    		</c:if>	
                    	</c:forEach>
                    </ul>
	                </c:if>
                  </div>
                  
                </div>
              </div>
              
              
              <div class="tab-pane fade" id="tabEspectaculosFinalizados" role="tabpanel" aria-labelledby="espectaculosFinalizados-tab">
                <div class="container carousel-inner no-padding">

                  <div id="demo" class="carousel slide" data-ride="carousel">
                  
                  	<c:set var="espectaculosFinalizados" scope="page" value="${sessionScope.espectaculosFinalizadosDelArtista}"/>
                  	<c:set var="cantEspectaculosFinalizados" scope="page" value="${pageScope.espectaculosFinalizados.size()}"/>
                  	<c:set var="cantPaginasEspectaculosFinalizados" scope="page" value="${pageScope.cantEspectaculosFinalizados % 3 == 0 ? pageScope.cantEspectaculosFinalizados / 3 : pageScope.cantEspectaculosFinalizados / 3 + 1 }"/>
                    
                    
                    <c:if test="${cantEspectaculosFinalizados > 0 }">
                  	<c:forEach var="paginaEspectaculosFinalizados" begin="0" end="${pageScope.cantPaginasEspectaculosFinalizados - 1}">
                  		<c:if test="${paginaEspectaculosFinalizados == 0 }">
                  		<div class="carousel-item active">
                  		</c:if>
                  		<c:if test="${paginaEspectaculosFinalizados != 0 }">
                  		<div class="carousel-item">
                  		</c:if>
                  			
                  			<c:forEach var="e" begin="${paginaEspectaculosFinalizados * 3}" end="${paginaEspectaculosFinalizados * 3 + 2 < pageScope.cantEspectaculosFinalizados ? paginaEspectaculosFinalizados * 3 + 2 : pageScope.cantEspectaculosFinalizados - 1}">
                  				<a href="ConsultaEspFinalizado?plataforma=${pageScope.espectaculosFinalizados[e].getNomPlat()}&espectaculo=${pageScope.espectaculosFinalizados[e].getNomEsp()}">
                        			<div class="col-xs-4 col-sm-4 col-md-4">
                         				<img src="${port.obtenerDatosEspectaculo(pageScope.espectaculosFinalizados[e].getNomPlat(), pageScope.espectaculosFinalizados[e].getNomEsp()).getUrlImagen() }">
										<p>${pageScope.espectaculosFinalizados[e].getNomEsp()}</p>
                        			</div>
                      			</a>
                  			</c:forEach>
                  			
                  		</div>
                  		              		
                  	</c:forEach>

                    <ul class="carousel-indicators">
                    	<c:forEach var="indE" begin="0" end="${pageScope.cantPaginasEspectaculosFinalizados - 1}">
                    		<c:if test="${indE == 0}">
                    			<li data-target="#demo" data-slide-to="${indE}" class="active"></li>
                    		</c:if>	
                    		<c:if test="${indE != 0}">
                    			<li data-target="#demo" data-slide-to="${indE}" ></li>
                    		</c:if>	
                    	</c:forEach>
                    </ul>
	                </c:if>
                  </div>
                  
                </div>
              </div>
              
              
              </c:if>
              
              <div class="tab-pane fade" id="tabSeguidores" roles="tabpanel" aria-labelledby="seguidores-tab">
              
              	<div class="list-group">
              		<c:forEach var="seguidor" items="${ sessionScope.artistaConsultado.getSeguidores() }" >
              		
              			<a href="Usuarios?nickname=${ seguidor }" class="list-group-item list-group-item-action flex-column align-items-start">
                    		<div class="row">
	                      		<img src="${port.obtenerUsuario(seguidor).getUrlImagen()}" class="col-3" alt="">
	                      		<div class="col-9 right">
	                        		<div class="d-flex w-100 justify-content-between">
	                         			 <h5 class="mb-1">${ seguidor }</h5>
	                        		</div>
	                      		</div>
	                    	</div>
                  		</a>
              		
              		</c:forEach>
              	</div>                
        
              </div>
              <div class="tab-pane fade" id="tabSeguidos" roles="tabpanel" aria-labelledby="seguidos-tab">
                <div class="list-group">
                  <c:forEach var="seguido" items="${ sessionScope.artistaConsultado.getSeguidos() }" >
              		
              			<a href="Usuarios?nickname=${ seguido }" class="list-group-item list-group-item-action flex-column align-items-start">
                    		<div class="row">
	                      		<img src="${port.obtenerUsuario(seguido).getUrlImagen()}" class="col-3" alt="">
	                      		<div class="col-9 right">
	                        		<div class="d-flex w-100 justify-content-between">
	                         			 <h5 class="mb-1">${ seguido }</h5>
	                        		</div>
	                      		</div>
	                    	</div>
                  		</a>
              		
              		</c:forEach>
              </div>
            </div>
              
             </div>
             </div>       
          
          </c:if>
          
          <!--  Modal de modificacion de datos ARTISTA -->
          <c:if test="${sessionScope.usuarioConsultadoEsArtista}">
	          <div class="modal fade" id="editar_perfil" tabindex="-1" role="dialog"
	            aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	            <div class="modal-dialog modal-dialog-centered" role="document">
	              <form class="modal-content" action="/servidor-web/Usuarios" method="post" enctype="multipart/form-data">
	                <input type='hidden' name='modificarDatos' value='true'>
	                <div class="modal-header">
	                  <h5 class="modal-title" >Modificar Datos</h5>
	                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                  </button>
	                </div>
	                <div class="modal-body">
                		<div class="form-row">
		                    <div class="form-group col-md-6">
		                      <label for="inputNombre">Nombre:</label>
		                      <input required type="text" class="form-control" id="inputNombre"  
		                      	value="${sessionScope.artistaConsultado.getNombre() }"
		                        name="name" placeholder="Nombre">
		                    </div>
		                    <div class="form-group col-md-6">
		                      <label for="inputApellido">Apellido:</label>
		                      <input required type="text" class="form-control" id="inputApellido" 
		                      	value="${sessionScope.artistaConsultado.getApellido() }"
		                        name="surname" placeholder="Apellido">
		                    </div>
		                  </div>
		                  <div class="form-row">
		                    <div class="form-group col-md-6">
		                      <label for="inputContra">Contraseña:</label>
		                      <input required type="password" class="form-control" id="inputContra" 
		                      	value="${sessionScope.artistaConsultado.getContrasenia() }"
		                        name="password" placeholder="Contraseña">
		                    </div>
		                    <div class="form-group col-md-6">
		                      <label for="inputConfContra">Confirmación de contraseña:</label>
		                      <input required type="password" class="form-control" id="inputConfContra" 
			                      value="${sessionScope.artistaConsultado.getContrasenia() }"
			                      name="passwordConf" placeholder="Contraseña">
		                    </div>
		                  </div>
		                  <div class="form-group">
		                  	<label for="inputConfContra">Descripcion:</label>
		                  	<textarea required class="form-control" id="desc" name="desc" placeholder="Descripcion">
		                  		${sessionScope.artistaConsultado.getDescripcion() }
			               	</textarea>
		                  </div>
		                  <div class="form-group">
		                  	<label for="inputConfContra">Biografia:</label>
			                <textarea required class="form-control" id="bio" name="bio" placeholder="Biografia">
		                  		${sessionScope.artistaConsultado.getBiografia() }
			               	</textarea>
		                  </div>
		                  <div class="form-group">
		                  	<label for="inputConfContra">Sitio web:</label>
		                  	<input required type="text" class="form-control" id="web" 
			                      value="${sessionScope.artistaConsultado.getSitioWeb() }"
			                      name="web" placeholder="Sitio Web">
		                  </div>
		                  <div class="form-group input-group">
		                    <div class="input-group-prepend">
		                      <label class="input-group-text" for="inputFecha">Fecha de nacimiento:</label>
		                    </div>
		                   	<input required id="inputFecha" class="form-control" type="date" name="bday" max="3000-12-31"
		                      min="1000-01-01" class="form-control" 
		                      value="${formater.format(sessionScope.artistaConsultado.getFechaNacimiento().toGregorianCalendar().getTime())}">
		                  </div>
		                  <div class="form-group input-group">
		                    <div class="input-group-prepend">
		                      <span class="input-group-text">Foto de perfil:</span>
		                    </div>
		                    <div class="custom-file">
		                      <input type="file" class="custom-file-input" id="customFile" name="imagen">
		                      <label class="custom-file-label" for="customFile">Elegir foto</label>
		                    </div>
		                  </div>
		                </div>
		                <div class="modal-footer">
		                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		                  <button name="nickname" value="${sessionScope.artistaConsultado.getNickname() }" type="submit" class="btn btn-primary">
		                  	Confirmar
		                  </button>
		                </div>
	              </form>
	            </div>
	          </div>
          </c:if>
          <!--  Modal de modificacion de datos ARTISTAS -->
          
          <!--  Modal de modificacion de datos ESPECTADORES -->
        <c:if test="${!sessionScope.usuarioConsultadoEsArtista}">
	          <div class="modal fade" id="editar_perfil" tabindex="-1" role="dialog"
	            aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	            <div class="modal-dialog modal-dialog-centered" role="document">
	              <form class="modal-content" action="/servidor-web/Usuarios"  method="post" enctype="multipart/form-data">
	                <input type='hidden' name='modificarDatos' value='true'>
	                <div class="modal-header">
	                  <h5 class="modal-title" >Modificar Datos</h5>
	                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                  </button>
	                </div>
	                <div class="modal-body">
	                		<div class="form-row">
			                    <div class="form-group col-md-6">
			                      <label for="inputNombre">Nombre:</label>
			                      <input required type="text" class="form-control" id="inputNombre"  
			                      	value="${sessionScope.espectadorConsultado.getNombre() }"
			                        name="name" placeholder="Nombre">
			                    </div>
			                    <div class="form-group col-md-6">
			                      <label for="inputApellido">Apellido:</label>
			                      <input required type="text" class="form-control" id="inputApellido" 
			                      	value="${sessionScope.espectadorConsultado.getApellido() }"
			                        name="surname" placeholder="Apellido">
			                    </div>
			                  </div>
			                  <div class="form-row">
			                    <div class="form-group col-md-6">
			                      <label for="inputContra">Contraseña:</label>
			                      <input required type="password" class="form-control" id="inputContra" 
			                      	value="${sessionScope.espectadorConsultado.getContrasenia() }"
			                        name="password" placeholder="Contraseña">
			                    </div>
			                    <div class="form-group col-md-6">
			                      <label for="inputConfContra">Confirmación de contraseña:</label>
			                      <input required type="password" class="form-control" id="inputConfContra" 
				                      value="${sessionScope.espectadorConsultado.getContrasenia() }"
				                      name="passwordConf" placeholder="Contraseña">
			                    </div>
			                  </div>
			                  <div class="form-group input-group">
			                    <div class="input-group-prepend">
			                      <label class="input-group-text" for="inputFecha">Fecha de nacimiento:</label>
			                    </div>
		                    	<input required id="inputFecha" class="form-control" type="date" name="bday" max="3000-12-31"
		                      		min="1000-01-01" class="form-control" 
		                      		value="${formater.format(sessionScope.espectadorConsultado.getFechaNacimiento().toGregorianCalendar().getTime())}">
			                  </div>
			                  <div class="form-group input-group">
			                    <div class="input-group-prepend">
			                      <span class="input-group-text">Foto de perfil:</span>
			                    </div>
			                    <div class="custom-file">
			                      <input type="file" class="custom-file-input" name="imagen" id="customFile">
			                      <label class="custom-file-label" for="customFile">Elegir foto</label>
			                    </div>
			                  </div>
			                </div>
			                <div class="modal-footer">
			                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
			                  <button name="nickname" value="${sessionScope.espectadorConsultado.getNickname() }" type="submit" class="btn btn-primary">
			                  	Confirmar
			                  </button>
			                </div>
	              </form>
	            </div>
	          </div>
          </c:if>

<t:temp_bot></t:temp_bot>
</html>