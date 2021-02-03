<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir= "/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

<t:temp_top></t:temp_top>


<div class="container p-3 my-3">
	<h1>Crear paquete</h4>
    <hr>
	<form method="post" action="AltaPaquete" enctype="multipart/form-data">
		<div class="form-group row">
			<label for="nombre" class="col-sm-2 col-form-label">Nombre:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre del paquete" required>
			</div>
		</div>
		<div class="form-group row">
			<label for="descripcion" class="col-sm-2 col-form-label">Descripción:</label>
			<div class="col-sm-10">
				<textarea type="text" class="form-control rounded-0" rows="5" id="descripcion"
				  placeholder="Descripcion del paquete" name="descripcion" required></textarea>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="fechaInicio">Fecha inicio:</label>
				<input type="date" class="form-control datepicker" id="fechaInicio" name="fechaInicio" required>
			</div>
			<div class="form-group col-md-6">
				<label for="fechaFin">Fecha fin:</label>
				<input type="date" class="form-control datepicker" id="fechaFin" name="fechaFin" required>
			</div>
		</div>
		<div class="form-row">
			<div class="col-sm-6 input-group">
				<div class="input-group-prepend">
					<label class="input-group-text" for="descuento">Descuento:</label>
				</div>
				<input type="number" class="form-control" id="descuento" name="descuento" placeholder="0" max="100" min="0" required>
				<div class="input-group-append">
					<div class="input-group-text">%</div>
				</div>
			</div>
			<div class="col-6">
				<div class="custom-file">
					<input type="file" class="custom-file-input" id="imagen" name="imagen">
					<label class="custom-file-label" for="imagen">Elegí una imagen</label>
				</div>
			</div>
		</div>
		<hr>
        <button class="btn btn-outline-success" type="submit" >Crear</button>
	</form>
</div>



      


<t:temp_bot></t:temp_bot>
</html>