<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!doctype html>
<html lang="es">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
  <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
  <title>coronatickets.uy</title>

</head>

<body>
	
    <div class="container-fluid">
        <div class="row no-gutter">
          <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
          <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
              <div class="container">
                <div class="row">
                  <div class="col-md-9 col-lg-8 mx-auto">
                    <h3 class="login-heading mb-4">Bienvenido de nuevo!</h3>
                    <form action="/servidor-web/Autenticacion" method="POST">
                      <div class="form-label-group">
                        <input type="text" id="inputEmail" class="form-control" placeholder="eMail or Nickname" required autofocus name="usuario">
                        <label for="inputEmail">Usuario o correo</label>
                      </div>
      
                      <div class="form-label-group">
                        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required name="contrasenia">
                        <label for="inputPassword">Contraseña</label>
                      </div>
      
                      <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">INGRESA</button>
                      <div class="text-center small">
                        Todavía no estas registrado?
                        <a href="#" data-toggle="modal" data-target="#registro_modal">Registrate aqui.</a></div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>


      <div class="modal fade" id="registro_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <form class="modal-content" action="/servidor-web/Usuarios" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="modificarDatos" value="false">  
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Registrarse</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputNickname">Nickname:</label>
                            <input required type="text" class="form-control" id="inputNickname" placeholder="Nickname" 
                            	required name="nickname">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputMail">Mail:</label>
                            <input required type="email" class="form-control" id="inputMail" placeholder="Mail" 
                            	required name="mail">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputContra">Contraseña:</label>
                            <input required type="password" class="form-control" id="inputContra" placeholder="Contraseña" 
                            	required name="contrasenia">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputConfContra">Confirmación de contraseña:</label>
                            <input required type="password" class="form-control" id="inputConfContra" placeholder="Contraseña"
                            	required name="confcontrasenia">
                        </div>

                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputNombre">Nombre:</label>
                            <input required type="text" class="form-control" id="inputNombre" placeholder="Nombre"
                            	required name="nombre">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputApellido">Apellido:</label>
                            <input required type="text" class="form-control" id="inputApellido" placeholder="Apellido"
                            	required name="apellido">
                        </div>
                        <div class="form-group col-md-6">
                        	<label for="inputApellido">Tipo de usuario:</label>
                        	<br>
	                        <input type="radio" id="tipoEspectador" name="tipousuario" value="espectador" required name="espectador" checked> 
	                    		Espectador /
	    					<input type="radio" id="tipoArtista" name="tipousuario" value="artista" required name="artista">
								Artista <br>
						</div>
                    </div>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputFecha">Fecha de nacimiento:</label>
                        </div>
                        <input required id="inputFecha" class="form-control" type="date" name="bday" max="3000-12-31" min="1000-01-01" class="form-control"
                            required name="fechanac">
                    </div>
                    
                    <div class="form-row input-artista d-none">
                    	<div class="form-group col-12">
                    		<label for="inputDescripcion">Descripcion:</label>
                            <textarea  type="text" class="form-control" id="inputDescripcion" placeholder="Descripcion"
                            	required name="inputDescripcion"></textarea>
                    	</div>
                    </div>
                    
                    <div class="form-row input-artista d-none">
                    	<div class="form-group col-12">
                    		<label for="inputBiografia">Biografia:</label>
                            <textarea  type="text" class="form-control" id="inputBiografia" placeholder="Biografia"
                            	required name="inputBiografia"></textarea>
                    	</div>
                    </div>
                    
                    <div class="form-row input-artista d-none">
                    	<div class="form-group col-12">
                    		<label for="inputSitio">Sitio Web:</label>
                            <input type="text" class="form-control" id="inputSitio" placeholder="Sitio"
                            	required name="inputSitio">
                    	</div>
                    </div>
                    
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Foto de usuario*</span>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="customFile"
                            	required name="imagen">
                            <label class="custom-file-label" for="customFile">Elegir foto</label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    <button type="submit" class="btn btn-primary">Aceptar</button>
                </div>
            </form>
        </div>
    </div>
      

</body>

<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Script del modal registro -->
<script src="${pageContext.request.contextPath}/js/registro.js"></script>