<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- tag lib importa la carpeta de tags -->
<%@ taglib prefix="t" tagdir= "/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="webservices.EspectadorDto" %>
<%@page import="webservices.EspectaculoDto" %>
<!DOCTYPE html>
<html lang="es">
<link href="${pageContext.request.contextPath}/css/detalleEspectaculo.css" rel="stylesheet" type="text/css">

<t:temp_top></t:temp_top>

<!-- Nombre y foto -->

<div class="container p-4 mt-3 border">
	<div class="row">
		<div class="col-4">
			<img class="img-fluid rounded" src="${espectaculo.getUrlImagen() != null ? espectaculo.getUrlImagen() : "https://tutaki.org.nz/wp-content/uploads/2019/04/no-image-1.png"}">
		</div>
		<div class="col-8">
			<h2> ${espectaculo.getNombre()} </h2>
			<small>Categorias: </small>
			<c:forEach items="${categorias}" var="cats">
			<a class="tag-categoria" href="Search?tipoGet=listado&tipoLista=categorias&categoria=${cats.getNombre()}">
				<small>${cats.getNombre()}</small>
			</a>
			</c:forEach>
			
			<div class="row">
				<h3><span class="badge badge-secondary">Costo: $${espectaculo.getCosto()}</span></h3>		
			</div>
			
			<c:if test="<%=(boolean) request.getAttribute(\"puedeFinalizar\") == true%>">
				<form action="FinalizarEspectaculo">
					<input type="hidden" name="espectaculo" value="${espectaculo.getNombre()}">
					<input type="hidden" name="plataforma" value="${espectaculo.getNombrePlataforma()}">
					<input type="submit" class="btn btn-success" value="Finalizar espectaculo">
				</form>
			</c:if>
			
			<c:if test="<%=session.getAttribute(\"usuarioLogueado\") instanceof EspectadorDto%>">
			<div class="row">
				<div class="col-1">
					<form action="Favoritos" method="POST">
						<input id="hdnAccionFavorito" type="hidden" name="accion" value=""/>
						<input type="hidden" name="espectaculo" value="${espectaculo.getNombre()}"/>
						<input type="hidden" name="plataforma" value="${espectaculo.getNombrePlataforma()}"/>
						<input type="hidden" name="usuario" value="${usuarioLogueado.getNickname()}"/>
						<c:if test="<%= !((EspectadorDto)session.getAttribute(\"usuarioLogueado\")).getEspectaculosFavoritos().contains(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombre()) %>">
						<button type="submit" class="btn btn-link" data-toggle="tooltip" data-placement="bottom" title="Toca para marcar como favorito..."
							onclick="document.getElementById('hdnAccionFavorito').value = 'marcar'">
							<i class="far fa-heart fa-2x favorito"></i>
						</button>
						</c:if>
						<c:if test="<%= ((EspectadorDto)session.getAttribute(\"usuarioLogueado\")).getEspectaculosFavoritos().contains(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombre()) %>">
						<button type="submit" class="btn btn-link" data-toggle="tooltip" data-placement="bottom" title="Toca para desmarcar favorito..."
							onclick="document.getElementById('hdnAccionFavorito').value = 'desmarcar'">
							<i class="fas fa-heart fa-2x favorito"></i>
						</button>
						</c:if>
					</form>
				</div>
				<div class="col" style="margin-top: auto;margin-bottom: auto;">
					${espectaculo.getCantFavoritos() } espectadores marcaron como favorito
				</div>
			</div>
			</c:if>
			
			<c:if test="<%=
						// Si hay un espectador logueado
						session.getAttribute(\"usuarioLogueado\") instanceof EspectadorDto && 
						// Y el espectador todavía *no* valoró al espectaculo
						!((EspectadorDto)session.getAttribute(\"usuarioLogueado\")).getValoraciones().stream()
							.anyMatch(val -> val.getPlataforma().equals( ((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombrePlataforma()) 
									      && val.getEspectaculo().equals(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombre())) &&
						// Y si el espectador esta anotado en una función del espectaculo
						((EspectadorDto)session.getAttribute(\"usuarioLogueado\")).getFunciones().stream().anyMatch(f -> 
								f.getNombrePlat().equals(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombrePlataforma()) &&
									f.getNombreEsp().equals(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombre()))	
				/*
					Por las dudas no lo borro
					
					((EspectadorDto)session.getAttribute(\"usuarioLogueado\"))
						.getValoracion(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombrePlataforma(), ((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombre()) == null &&
							((EspectadorDto)session.getAttribute(\"usuarioLogueado\")).getFunciones().stream().anyMatch(f -> 
								f[0].equals(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombrePlataforma()) &&
									f[1].equals(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombre()))
				*/
						%>">
				<div class="row">
					<div class="col-2">
						<p>Valora este espectaculo:</p>
					</div>
					<div class="col">
						<c:forEach var="i" begin="1" end="5">
							<i class="far fa-star fa-2x estrella" id="estrella${i}" onmouseenter="marcarEstrellas(${i})"
								onmouseleave="desmarcarEstrellas()" onclick="valorar(${i})"></i>
						</c:forEach>
					</div>
				</div>
				
				<form action="Valoraciones" method="POST" id="formValoracion">
					<input type="hidden" name="espectaculo" value="${espectaculo.getNombre()}"/>
					<input type="hidden" name="plataforma" value="${espectaculo.getNombrePlataforma()}"/>
					<input type="hidden" name="usuario" value="${usuarioLogueado.getNickname()}"/>
					<input type="hidden" name="valoracion" id="hdnValoracion"/>
				</form>
			</c:if>
			
			<c:if test="<%=session.getAttribute(\"usuarioLogueado\") instanceof EspectadorDto && 
						((EspectadorDto)session.getAttribute(\"usuarioLogueado\")).getValoraciones().stream()
							.anyMatch(val -> val.getPlataforma().equals( ((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombrePlataforma()) 
									      && val.getEspectaculo().equals(((EspectaculoDto)request.getAttribute(\"espectaculo\")).getNombre())) 
						%>">
				<div class="row">
					<div class="col-2">
						<p>Tu valoracion:</p>
					</div>
					<div class="col">
						<% 
							int val = ((EspectadorDto)session.getAttribute("usuarioLogueado")).getValoraciones().stream()
											.filter(v -> v.getPlataforma().equals( ((EspectaculoDto)request.getAttribute("espectaculo")).getNombrePlataforma()) 
										      && v.getEspectaculo().equals(((EspectaculoDto)request.getAttribute("espectaculo")).getNombre()))
											.findAny().orElse(null).getEstrellas();
							for (int i = 0; i < val; i++) {
						%>
						<i class="fas fa-star fa-2x estrella"></i>
						<%
							}
							for (int j = val; j < 5; j++) {
						%>
						<i class="far fa-star fa-2x estrella"></i>
						<% 
							}
						%>
					</div>
				</div>		
				
				
			</c:if>
			
		</div>
	</div>
	
	<hr>
	<div class="row p-3">
		<div class="container">
			<div class="row">
				<div class="col-2 font-weight-bold">
					<p>Descripción: </p>
				</div>
				<div class="col-10">
					<p>
						${espectaculo.getDescripcion()}
					</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 font-weight-bold">
					<p>Duración: </p>
				</div>
				<div class="col-10">
					<p>${espectaculo.getDuracionHoras()}:${espectaculo.getDuracionMinutos() != 0 ? espectaculo.getDuracionMinutos() : "00"}</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-2 font-weight-bold">
					<p>Cantidad de expectadores: </p>
				</div>
				<div class="col-10">
					<p>Min: ${espectaculo.getMinEspectadores()}</p>
					<p>Max: ${espectaculo.getMaxEspectadores()}</p>
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<a href="${espectaculo.getUrl()}"><p>Link a espectaculo</p></a>
				</div>
			</div>
			
			<div class="row">
				<div class="col-2">
					<p>Organizador: </p>
				</div>
				<div class="col-10">
					<a href="Usuarios?nickname=${espectaculo.getOrganizador().getNickname()}">${espectaculo.getOrganizador().getNombre()} ${espectaculo.getOrganizador().getApellido()}</a>
				</div>
			</div>
		</div>
	</div>
	
	<hr>
	
	<%
		double total = ((EspectaculoDto)request.getAttribute("espectaculo")).getValoraciones().size();
		double unaEstrella =  ((EspectaculoDto)request.getAttribute("espectaculo")).getValoraciones().stream().filter(v -> v.getEstrellas() == 1).count();
		double dosEstrellas =  ((EspectaculoDto)request.getAttribute("espectaculo")).getValoraciones().stream().filter(v -> v.getEstrellas() == 2).count();
		double tresEstrellas =  ((EspectaculoDto)request.getAttribute("espectaculo")).getValoraciones().stream().filter(v -> v.getEstrellas() == 3).count();
		double cuatroEstrellas =  ((EspectaculoDto)request.getAttribute("espectaculo")).getValoraciones().stream().filter(v -> v.getEstrellas() == 4).count();
		double cincoEstrellas =  ((EspectaculoDto)request.getAttribute("espectaculo")).getValoraciones().stream().filter(v -> v.getEstrellas() == 5).count();
		double avg = total > 0 ? (unaEstrella + 2*dosEstrellas + 3*tresEstrellas + 4*cuatroEstrellas + 5*cincoEstrellas) / total : 0;
		int avgRounded = (int) avg;
	%>
	
	<div class="row">
		<c:if test="<%=total > 0%>">
		<div class="col-xs-0 col-md-1"></div>
        <div class="col-xs-12 col-md-3 text-center">
            <h1 class="rating-num">
                <%=String.format("%.2f", avg)%></h1>
            <div class="rating">
                <c:forEach var="i" begin="1" end="<%=avgRounded%>">
					<i class="fas fa-star"></i>
				</c:forEach>
				<c:if test="<%=avgRounded < 5%>">
					<c:forEach var="i" begin="<%=avgRounded + 1%>" end="5">
						<i class="far fa-star"></i>
					</c:forEach>
				</c:if>
            </div>
            <div>
            	<i class="fas fa-users"></i> <%=(int)total%> usuarios valoraron
            </div>
        </div>
        <div class="col-xs-12 col-md-6">
            <div class="row rating-desc">
                <div class="col-xs-3 col-md-3 text-right">
                    <span class="fas fa-star"></span>5
                </div>
                <div class="col-xs-8 col-md-9">
                    <div class="progress progress-striped">
                        <div class="progress-bar bg-success" role="progressbar" aria-valuenow="20"
                            aria-valuemin="0" aria-valuemax="100" style="width: <%= total > 0 ? cincoEstrellas / total * 100 : 0 %>%">
                            <%= total > 0 ? String.format("%.2f", cincoEstrellas / total * 100) : 0 %> %
                        </div>
                    </div>
                </div>
                <!-- end 5 -->
                <div class="col-xs-3 col-md-3 text-right">
                    <span class="fas fa-star"></span>4
                </div>
                <div class="col-xs-8 col-md-9">
                    <div class="progress">
                        <div class="progress-bar" role="progressbar" aria-valuenow="20"
                            aria-valuemin="0" aria-valuemax="100" style="width: <%= total > 0 ? cuatroEstrellas / total * 100 : 0 %>%">
                            <%= total > 0 ? String.format("%.2f", cuatroEstrellas / total * 100) : 0 %> %
                        </div>
                    </div>
                </div>
                <!-- end 4 -->
                <div class="col-xs-3 col-md-3 text-right">
                    <span class="fas fa-star"></span>3
                </div>
                <div class="col-xs-8 col-md-9">
                    <div class="progress">
                        <div class="progress-bar bg-info" role="progressbar" aria-valuenow="20"
                            aria-valuemin="0" aria-valuemax="100" style="width: <%= total > 0 ? tresEstrellas / total * 100 : 0 %>%">
                           <%= total > 0 ? String.format("%.2f", tresEstrellas / total * 100) : 0 %> %
                        </div>
                    </div>
                </div>
                <!-- end 3 -->
                <div class="col-xs-3 col-md-3 text-right">
                    <span class="fas fa-star"></span>2
                </div>
                <div class="col-xs-8 col-md-9">
                    <div class="progress">
                        <div class="progress-bar bg-warning" role="progressbar" aria-valuenow="20"
                            aria-valuemin="0" aria-valuemax="100" style="width: <%= total > 0 ? dosEstrellas / total * 100 : 0 %>%">
                            <%= total > 0 ? String.format("%.2f", dosEstrellas / total * 100) : 0 %> %
                        </div>
                    </div>
                </div>
                <!-- end 2 -->
                <div class="col-xs-3 col-md-3 text-right">
                    <span class="fas fa-star"></span>1
                </div>
                <div class="col-xs-8 col-md-9">
                    <div class="progress">
                        <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="80"
                            aria-valuemin="0" aria-valuemax="100" style="width: <%= total > 0 ? unaEstrella / total * 100 : 0 %>%">
                            <%= total > 0 ? String.format("%.2f", unaEstrella / total * 100) : 0 %> %
                        </div>
                    </div>
                </div>
                <!-- end 1 -->
            </div>
            <!-- end row -->
        </div>
        </c:if>
        <c:if test="<%=total == 0%>">
        <div class="col text-center">
        	<p><i class="far fa-sad-cry"></i> Ningun usuario valoro este espectaculo aun <i class="far fa-sad-cry"></i></p>
        </div>
        </c:if>
    </div>

	<c:if test="${funciones.size()>0}">
	<hr>
	<div class="row p-3">
		<div class="container">
			<h3 class="mb-3">Funciones:</h3>
			
			<div id="carouselFuncionControls" class="carousel slide carousel-multi-item col-12" data-ride="carousel">
				<!--Controls-->
				<a class="carousel-control-prev" href="#carouselFuncionControls" role="button"
				    data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				</a>
				<a class="carousel-control-next" href="#carouselFuncionControls" role="button"
				    data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				</a>
				<!--/.Controls-->
				
				<!--Indicators-->
				<ol class="carousel-indicators">
				    <li data-target="#multi-item-example" data-slide-to="0" class="active"></li>
				    <li data-target="#multi-item-example" data-slide-to="1"></li>
				    <li data-target="#multi-item-example" data-slide-to="2"></li>
				</ol>
				<!--/.Indicators-->
				
				<!--Slides-->
				<div class="carousel-inner" role="listbox">
					
					<c:forEach items="${funciones}" var="func"  varStatus="loop">
					
						<c:if test="${loop.index % 3 == 0 }">
							<div class="carousel-item ${loop.index == 0 ? 'active' : '' }">
								<div class="row">
						</c:if>
									<div class="col-md-4">
				              			<div class="card mb-2 h-100">
				                   			<img class="card-img-top" src="${func.getImagen() != null ? func.getImagen() : 'https://tutaki.org.nz/wp-content/uploads/2019/04/no-image-1.png'  }" alt="Card image cap">
			                    			<div class="card-body">
			                        			<h5 class="card-title">${func.getNombre()}</h5>
			                        			<a href="DetalleFuncion?plataforma=${plataforma}&espectaculo=${espectaculo.getNombre()}&funcion=${func.getNombre()}" class="btn btn-primary fa-pull-right">Ver función</a>
			                        			
			                    			</div>
				                		</div>
				            		</div>
						<c:if test="${loop.index % 3 == 2 || loop.index == (funciones.size() - 1) }">
								</div>
							</div>
						</c:if>
					
					
					
					</c:forEach>
					
				
				
				</div>
			</div>
		</div>

	</div>
	</c:if>
	
	<c:if test="${paquetes.size()>0}">
	<hr>
	<div class="row p-3">
		<div class="container">
			<h3 class="mb-3">Paquetes:</h3>
			
			<div id="carouselPaqueteControls" class="carousel slide carousel-multi-item col-12" data-ride="carousel">
				<!--Controls-->
				<a class="carousel-control-prev" href="#carouselPaqueteControls" role="button"
				    data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				</a>
				<a class="carousel-control-next" href="#carouselPaqueteControls" role="button"
				    data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				</a>
				<!--/.Controls-->
				
				<!--Indicators-->
				<ol class="carousel-indicators">
				    <li data-target="#multi-item-example" data-slide-to="0" class="active"></li>
				    <li data-target="#multi-item-example" data-slide-to="1"></li>
				    <li data-target="#multi-item-example" data-slide-to="2"></li>
				</ol>
				<!--/.Indicators-->
				
				<!--Slides-->
				<div class="carousel-inner" role="listbox">
					
					<c:forEach items="${paquetes}" var="paq"  varStatus="loop">
					
						<c:if test="${loop.index % 3 == 0 }">
							<div class="carousel-item ${loop.index == 0 ? 'active' : '' }">
								<div class="row">
						</c:if>
									<div class="col-md-4">
				              			<div class="card mb-2 h-100">
				                   			<img class="card-img-top" src="${paq.getUrlImagen() != null ? paq.getUrlImagen() : 'https://tutaki.org.nz/wp-content/uploads/2019/04/no-image-1.png'  }" alt="Card image cap">
			                    			<div class="card-body">
			                        			<h5 class="card-title">${paq.getNombre()}</h5>
			                        			<a href="DetallePaquete?paquete=${paq.getNombre()}" class="btn btn-primary fa-pull-right">Ver paquete</a>
			                        			
			                    			</div>
				                		</div>
				            		</div>
						<c:if test="${loop.index % 3 == 2 || loop.index == (paquetes.size() - 1) }">
								</div>
							</div>
						</c:if>
					
					
					
					</c:forEach>
					
				
				
				</div>
			</div>

		
		</div>
		
	</div>
	</c:if>
	
	<c:if test="${espectaculo.getUrlVideo() != null}">
		<hr>
		<h3 class="mb-3">Video del espectaculo:</h3>
		<div>

			<iframe width="560" height="315" src="${espectaculo.getUrlVideo().replace('watch?v=', 'embed/').replaceAll('&.*', '')}" frameborder="0"
				allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
	</c:if>
	
</div>

<t:temp_bot></t:temp_bot>

<script>

	function marcarEstrellas(i) {
		for (var j = 1; j <= i; j++) {
			var e = document.getElementById("estrella" + j);
			e.classList.add("fas");
			e.classList.add("animate__headShake");
			e.classList.add("animate__animated");
			e.classList.remove("far");
		}
	}
	
	function desmarcarEstrellas(i) {
		var estrellas = document.querySelectorAll(".estrella.fas");
		estrellas.forEach(e => {
			e.classList.add("far");
			e.classList.remove("fas");
			e.classList.remove("animate__headShake");
			e.classList.remove("animate__animated");
		})
	}
	
	function valorar(estrellas) {
		document.getElementById("hdnValoracion").value = estrellas;
		document.getElementById("formValoracion").submit();
	}
	
</script>


</html>
