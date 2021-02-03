<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir= "/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/busqueda.css">
<t:temp_top></t:temp_top>
<script>
	let lista = [];
</script>
<c:forEach items="${paquetes}" var="paq">
	<script>
   		lista.push({
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
<c:forEach items="${espectaculos}" var="esp">
   	<script>
   		lista.push({
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
<c:forEach items="${artistas}" var="art">
   	<script>
   		lista.push({
   			"nombre" : '${art.getNombre()}',
   			"apellido" : '${art.getApellido()}',
   			"fecha_nacimiento" : { 
   				"dia" : ${art.getFechaNacimiento().getDay()},
   				"mes" : ${art.getFechaNacimiento().getMonth()},
   				"año" : ${art.getFechaNacimiento().getYear()}
   			},
   			"tipo" : "artista",
   			"query_detalle" : {
   				"nickname" : '${art.getNickname()}'
   			},
   			"urlImagen" : '${ art.getUrlImagen() != null ? art.getUrlImagen() : "https://tutaki.org.nz/wp-content/uploads/2019/04/no-image-1.png" }'
   		});
   	</script>
</c:forEach>
<c:forEach items="${espectadores}" var="esp">
   	<script>
   		lista.push({
   			"nombre" : '${esp.getNombre()}',
   			"apellido" : '${esp.getApellido()}',
   			"fecha_nacimiento" : { 
   				"dia" : ${esp.getFechaNacimiento().getDay()},
   				"mes" : ${esp.getFechaNacimiento().getMonth()},
   				"año" : ${esp.getFechaNacimiento().getYear()}
   			},
   			"tipo" : "espectador",
   			"query_detalle" : {
   				"nickname" : '${esp.getNickname()}'
   			},
   			"urlImagen" : '${ esp.getUrlImagen() != null ? esp.getUrlImagen() : "https://tutaki.org.nz/wp-content/uploads/2019/04/no-image-1.png" }'	
   		});
   	</script>
</c:forEach>


<header class="mx-2 my-4">
	<c:if test = "${param.tipoLista == 'plataformas'}">
		<h2>Listado de paquetes y espectáculos de ${param.plataforma}</h2>
	</c:if>
	<c:if test = "${param.tipoLista == 'categorias'}">
		<h2>Listado de paquetes y espectáculos de ${param.categoria}</h2>
	</c:if>
	<c:if test = "${param.tipoLista == 'paquetes'}">
		<h2>Listado de paquetes</h2>
	</c:if>
	<c:if test = "${param.tipoLista == 'usuarios'}">
		<h2>Listado de usuarios</h2>
	</c:if>
	<hr>
</header>

<div class="container">
    <div class="list-group" id="resultados_box">
    	
    </div>
</div>



<t:temp_bot></t:temp_bot>
<script src="${pageContext.request.contextPath}/js/listado.js"></script>
</html>