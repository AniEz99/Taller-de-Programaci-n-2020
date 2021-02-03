<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir= "/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

<t:temp_top></t:temp_top>

<div class="container p-3 my-3 ">
	<h1>Crear Espectáculo</h1>
    <hr>
	<form method="post" action="AltaEspectaculo" enctype="multipart/form-data">
		<div class="form-group">
			<div class="form-row">
    			<div class="form-group col-md-6">
     				<label for="nombre">Nombre</label>
    				<input type="text" class="form-control" id="nombre" name="nombreEspectaculo" required>
   				</div>
    			<div class="form-group col-md-6">
     				<label for="plataforma">Plataforma</label>
     				<select class="form-control selectpicker" name="plataforma" id="plataforma" data-style="btn btn-outline-primary" required>
					<c:forEach items="${plataformas}" var="plat">
						<option value="${plat.getNombre()}" >${plat.getNombre()}</option>
					</c:forEach>
					</select>
  				</div>
  			</div>
  		</div>
  		
	  	<div class="form-row">
	  		<div class="form-group col-6">	
	  			<label for="desEspectaculo">Descripción del Espectáculo: </label>
	  			<textarea type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="desEspectaculo" name="desEspectaculo" required></textarea>
	  		</div>
	  		<div class="form-group col-3">
	  			<label for="durEspectaculo">Duración:</label>
	  			<div class="input-group input-group-sm">
	  				<div class="input-group-prepend">
          				<div class="input-group-text">Hs: </div>
        			</div>
        			<input class="form-control form-control-sm" type="number" id="durEspectaculoH" name="durEspectaculoH" min="0" max="10" value="0" required>
	  			</div>
	  			
	  			<div class="input-group input-group-sm mt-2">
	  				<div class="input-group-prepend ">
          				<div class="input-group-text">Min: </div>
        			</div>
        			<input type="number" class ="form-control form-control-sm" id="durEspectaculoM" name="durEspectaculoM" min="0" max="60" value="0" required>
	  			</div>
	  				  			
	  		</div>
	  		<div class="form-group col-3">
	  			<label for="cantEsp">Cantidad de Espectadores:</label>
	  			
	  			<div class="input-group input-group-sm">
	  				<div class="input-group-prepend">
          				<div class="input-group-text">Mín: </div>
        			</div>
        			<input type="number" class="form-control form-control-sm" id="cantEspMin" name="cantEspMin" min="0" max="2000" value="0" required>
	  			</div>
	  			
	  			<div class="input-group input-group-sm mt-2">
	  				<div class="input-group-prepend ">
          				<div class="input-group-text">Máx: </div>
        			</div>
        			<input type="number" class="form-control form-control-sm" id="cantEspMax" name="cantEspMax" min="0" max="2000" value="0" required>
	  			</div>
	  		</div>
	  	</div>
	  	
	  	<div class="form-row">
	  		<div class="form-group col-md-9">	
	  			<label for="urlEspectaculo">Ingrese la URL asociada: </label>
	  			<input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="urlEspectaculo" name="urlEspectaculo" required>
	  		</div>
		  	<div class="form-group col-md-3">
		  		<label for="costoEspectaculo">Ingrese el costo: </label>
		  		<div class="input-group">
		  			<div class="input-group-prepend">
		  				<div class="input-group-text">$ </div>
		  			</div>
		  			<input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" min="0" id="costoEspectaculo" name="costoEspectaculo" required>
		  		</div>
	  		</div>
        </div>
        
        <div class="form-row">
	  		<div class="form-group col-md-9">	
	  			<label for="urlEspectaculo">Ingrese la descripcion del premio a sortear: </label>
	  			<input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="desPremio" name="desPremio" required>
	  		</div>
		  	<div class="form-group col-md-3">
		  		<label for="costoEspectaculo">Cantidad de premios: </label>
		  		<div class="input-group">
		  			<div class="input-group-prepend">
		  				<div class="input-group-text">$ </div>
		  			</div>
		  			<input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" min="0" value="0" id="cantPremio" name="cantPremio" required>
		  		</div>
	  		</div>
        </div>
        
        <div class="form-row">
	  		<div class="form-group col-md-9">	
	  			<label for="urlEspectaculo">Ingrese la URL del video: </label>
	  			<input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" id="urlVideo" name="urlVideo" required>
	  		</div>
        </div>
        
        <div class="form-row">
        	<div class="form-group col-md-4">
        		<label for="categorias" >Seleccione las categorías asociadas:</label><br>
		  		<select class="selectpicker" id="categorias" multiple data-live-search="true" multiple data-style="btn btn-outline-primary" data-size="3" name="categorias" required>
	  			<c:forEach items="${categorias}" var="cats">
					<option value="${cats.getNombre()}" >${cats.getNombre()}</option>
				</c:forEach>
           		</select>
        	</div>
        	<div class="form-group  col-md-8">
        		<label for="imagen" >Seleccione una imagen:</label>
        		<div class="input-group">
	           		<div class="input-group-prepend">
	              		<span class="input-group-text">Imagen (opcional)</span>
	            	</div>
	            	<div class="custom-file">
	              		<input type="file" class="custom-file-input" name="imagen" id="imagen">
	              		<label class="custom-file-label" for="imagen">Elegir foto</label>
	            	</div>
            	</div>
         	</div>
		</div>
		<div class="form-group">
			<button id="btnComprar" type="submit" class="btn btn btn-outline-success">Crear</button>
		</div>
	</form>
</div>   
      

<t:temp_bot></t:temp_bot>
<script src="${pageContext.request.contextPath}/js/AltaEspectaculo.js"></script>
</html>