<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir= "/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/busqueda.css">
<t:temp_top></t:temp_top>
<header class="mx-2 my-4">
	<h2>Busqueda</h2>
	<p>Resultados similares para la busqueda: <i>"${query}"</i></p>
</header>
<div class="row justify-content-between bg-light border-top border-bottom px-4 py-2">
	<p class="my-auto">
		Se encontró ${ espectaculos.size() + paquetes.size()} resultados <small class="text-muted my-auto">(De los cuales se muestran: <span id="cantFiltrados"></span>)</small>
	</p>
	<div class="row mr-2">
		<div class="dropdown mr-2">
	  		<button class="btn btn-light border-dark dropdown-toggle" type="button" id="dropdowOrdenar" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    		Orden
	  		</button>
			<div class="dropdown-menu" aria-labelledby="dropdowOrdenar">
				<h6 class="dropdown-header">Tipo de orden</h6>
				<a class="dropdown-item active tipo_sort" href="#" id="orden_alfa">Alfabetico</a>
				<a class="dropdown-item tipo_sort " href="#" id="orden_año">Año</a>
			</div>
		</div>
		<div class="dropdown mr-2">
	  		<button class="btn btn-light border-dark dropdown-toggle" type="button" id="dropdownFiltroCategoria" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    		Filtrar categorias
	  		</button>
			<div class="dropdown-menu" aria-labelledby="dropdownFiltroCategoria">
				<h6 class="dropdown-header">Filtrar por</h6>
				<c:forEach items="${categorias}" var="cats">
					<a class="dropdown-item filtro_categoria" href="#" >${cats.getNombre()}</a>
				</c:forEach>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item active" id="sin_filtro_categoria"href="#" >Sin filtro</a>
			</div>
		</div>
		<div class="dropdown mr-2">
	  		<button class="btn btn-light border-dark dropdown-toggle" type="button" id="dropdownFiltroPlataforma" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    		Filtrar Plataformas
	  		</button>
			<div class="dropdown-menu" aria-labelledby="dropdownFiltroPlataforma">
				<h6 class="dropdown-header">Filtrar por</h6>
				<c:forEach items="${plataformas}" var="plats">
					<a class="dropdown-item filtro_plataforma" href="#" >${plats}</a>
				</c:forEach>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item active" id="sin_filtro_plataforma" href="#" >Sin filtro</a>
			</div>
		</div>
	</div>
</div>


<!-- Paquetes -->
<hr>
<div class="container">
    <div class="list-group" id="resultados_box">
    <script>
    	var espectaculos = [];
    	var paquetes = [];
		
		let categoriasTodas = [
			<c:forEach items="${categorias}" var="cats">
				"${cats.getNombre()}",
			</c:forEach>
		];
		
		let plataformasTodas = [
			<c:forEach items="${plataformas}" var="plats">
				"${plats}",
			</c:forEach>
		];
    </script>
    <c:forEach items="${espectaculos}" var="esp">
    	<script>
    		espectaculos.push({
    			"nombre" : '${esp.getNombre()}',
    			"descripcion" : '${esp.getDescripcion()}',
    			"duracion_horas" : '${esp.getDuracionHoras()}',
    			"duracion_minutos" : '${esp.getDuracionMinutos()}',
    			"costo" : '${esp.getCosto()}',
    			"tipo" : "espectaculo",
    			"fecha_ingreso" : { 
    				"dia" : ${esp.getFechaRegistro().getDay()},
    				"mes" : ${esp.getFechaRegistro().getMonth()},
    				"año" : ${esp.getFechaRegistro().getYear()},
    			},
    			"categorias" : [
    				<c:forEach items="${esp.getCategoriasAsociadas()}" var="cat">
    					"${cat.getNombre()}",
    				</c:forEach>
    			],
    			"plataformas" : [
					'${esp.getNombrePlataforma()}'
				],
    			"query_detalle" : {
    				"plataforma" : '${esp.getNombrePlataforma()}',
    				"espectaculo" : '${esp.getNombre()}'
    			},
    			"urlImagen" : '${ esp.getUrlImagen() != null ? esp.getUrlImagen() : "https://tutaki.org.nz/wp-content/uploads/2019/04/no-image-1.png" }'
    			   	
    		});
    	</script>
	</c:forEach>
	<c:forEach items="${paquetes}" var="paq">
		<script>
    		paquetes.push({
    			"nombre" : '${paq.getNombre()}',
    			"descripcion" : '${paq.getDescripcion()}',
    			"descuento" : '${paq.getDescuento()}',
    			"tipo" : "paquete",
    			"fecha_ingreso" : { 
    				"dia" : ${paq.getFechaRegistro().getDay()},
    				"mes" : ${paq.getFechaRegistro().getMonth()},
    				"año" : ${paq.getFechaRegistro().getYear()},
    			},
    			"categorias" : [
    				<c:forEach items="${paq.getCategorias()}" var="cat">
    					"${cat.getNombre()}",
    				</c:forEach>
    			],
    			"query_detalle" : {
    				"paquete" : '${paq.getNombre()}'
    			},
				"plataformas" : [
					<c:forEach items="${paq.getPlataformas()}" var="plat">
						"${plat}",
					</c:forEach>
				],
				"urlImagen" : '${ paq.getUrlImagen() != null ? paq.getUrlImagen() : "https://tutaki.org.nz/wp-content/uploads/2019/04/no-image-1.png" }'
			   		
    				
    				
    		});
    	</script>
	</c:forEach>
    
    </div>
</div>
    


<t:temp_bot></t:temp_bot>


<script src="${pageContext.request.contextPath}/js/busqueda.js"></script>



</html>