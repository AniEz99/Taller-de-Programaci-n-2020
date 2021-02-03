<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- tag lib importa la carpeta de tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!doctype html>
<html lang="en">
	
	<t:temp_top></t:temp_top>
	
		<header class="mx-2 my-4">
			<h1>
			    ¡Bienvenido a CoronaTickets!
			    <!-- getServletContext().getInitParameter("dirServidorCentral")  -->
			</h1>
			<i>Quedate en casa viendo espectaculos por internet</i>
		</header>
		<!--Carousel Espectaculos-->
		<div class="container col-12 mx-2">
		    <h4 class="mt-2">Los mejores espectaculos del mes</h4>
		    <div id="carouselEspectaculoControls" class="carousel slide carousel-multi-item"
		        data-ride="carousel">
		        <!--Controls-->
		        <a class="carousel-control-prev" href="#carouselEspectaculoControls" role="button"
		            data-slide="prev">
		            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		            <span class="sr-only">Previous</span>
		        </a>
		        <a class="carousel-control-next" href="#carouselEspectaculoControls" role="button"
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
		
		            <!--First slide-->
		            <div class="carousel-item active">
		
		                <div class="row">
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <div class="card-body">
		                                <h5 class="card-title">Los Village Volvieron</h5>
		                                <p class="card-text">Espectáculo de retorno de
		                                    los Village People.</p>
		                                <a href="DetalleEspectaculo?plataforma=Instagram%20Live&espectaculo=Los%20Village%20Volvieron" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <div class="card-body">
		                                <h5 class="card-title">Memphis
		                                    Blues World</h5>
		                                <p class="card-text">Espectáculo promoviendo
		                                    álbum Memphis Blues.</p>
		                                <a href="/servidor-web/DetalleEspectaculo?plataforma=Twitter%20Live&espectaculo=Memphis%20Blues%20World" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		
		
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <div class="card-body">
		                                <h5 class="card-title">Global Spirit</h4>
		                                    <p class="card-text">Espectáculo donde se
		                                        presenta el álbum Spirit.</p>
		                                    <a href="/servidor-web/DetalleEspectaculo?plataforma=Facebook%20Watch&espectaculo=Global%20Spirit" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		
		            </div>
		            <!--/.First slide-->
		
		            <!--Second slide-->
		            <div class="carousel-item">
		
		                <div class="row">
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <div class="card-body">
		                                <h5 class="card-title">Los Village Volvieron</h5>
		                                <p class="card-text">Espectáculo de retorno de
		                                    los Village People.</p>
		                                <a href="DetalleEspectaculo?plataforma=Instagram%20Live&espectaculo=Los%20Village%20Volvieron" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <div class="card-body">
		                                <h5 class="card-title">Memphis
		                                    Blues World</h5>
		                                <p class="card-text">Espectáculo promoviendo
		                                    álbum Memphis Blues.</p>
		                                <a href="/servidor-web/DetalleEspectaculo?plataforma=Twitter%20Live&espectaculo=Memphis%20Blues%20World" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		
		
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <div class="card-body">
		                                <h5 class="card-title">Global Spirit</h4>
		                                    <p class="card-text">Espectáculo donde se
		                                        presenta el álbum Spirit.</p>
		                                    <a href="/servidor-web/DetalleEspectaculo?plataforma=Facebook%20Watch&espectaculo=Global%20Spirit" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		
		            </div>
		            <!--/.Second slide-->
		
		
		        </div>
		        <!--/.Slides-->
		
		    </div>
		</div>
		<!--/.Carousel Espectaculos-->
		<hr>
		<!--Carousel Paquetes-->
		<div class="container col-12 mx-2">
		    <h4 class="mt-2">Los mejores paquetes del mes</h4>
		    <div id="carouselPaqueteControls" class="carousel slide carousel-multi-item" data-ride="carousel">
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
		
		            <!--First slide-->
		            <div class="carousel-item active">
		
		                <div class="row">
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <img class="card-img-top" src="https://bit.ly/3l9Vihe" alt="Card image cap">
		
		                            <div class="card-body">
		                                <h5 class="card-title">Paquete de
		                                    Bandas</h5>
		                                <p class="card-text">Paquete de bandas musicales.</p>
		                                <a href="/servidor-web/DetallePaquete?paquete=Paquete%20de%20Bandas" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <img class="card-img-top" src="https://bit.ly/2HSF4e0" alt="Card image cap">
		                            <div class="card-body">
		                                <h5 class="card-title">Paquete
		                                    Solistas</h5>
		                                <p class="card-text">Paquete de solistas.</p>
		                                <a href="/servidor-web/DetallePaquete?paquete=Paquete%20Solistas" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		
		
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <img class="card-img-top" src="https://bit.ly/3ndBhIw" alt="Card image cap">
		
		                            <div class="card-body">
		                                <h5 class="card-title">Paquete
		                                    Latino</h4>
		                                    <p class="card-text">Paquete de espectáculos
		                                        latinos. </p>
		                                    <a href="/servidor-web/DetallePaquete?paquete=Paquete%20Latino" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		
		            </div>
		            <!--/.First slide-->
		
		
		            <!--Second slide-->
		            <div class="carousel-item">
		
		                <div class="row">
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <img class="card-img-top" src="https://bit.ly/3l9Vihe" alt="Card image cap">
		
		                            <div class="card-body">
		                                <h5 class="card-title">Paquete de
		                                    Bandas</h5>
		                                <p class="card-text">Paquete de bandas musicales.</p>
		                                <a href="/servidor-web/DetallePaquete?paquete=Paquete%20de%20Bandas" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <img class="card-img-top" src="https://bit.ly/2HSF4e0" alt="Card image cap">
		                            <div class="card-body">
		                                <h5 class="card-title">Paquete
		                                    Solistas</h5>
		                                <p class="card-text">Paquete de solistas.</p>
		                                <a href="/servidor-web/DetallePaquete?paquete=Paquete%20Solistas" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		
		
		                    <div class="col-md-4">
		                        <div class="card mb-2 h-100">
		                            <img class="card-img-top" src="https://bit.ly/3ndBhIw" alt="Card image cap">
		
		                            <div class="card-body">
		                                <h5 class="card-title">Paquete
		                                    Latino</h4>
		                                    <p class="card-text">Paquete de espectáculos
		                                        latinos. </p>
		                                    <a href="/servidor-web/DetallePaquete?paquete=Paquete%20Latino" class="btn btn-primary fa-pull-right">Ver info</a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		
		            </div>
		            <!--/.Second slide-->
		
		
		        </div>
		        <!--/.Slides-->
		
		    </div>
		</div>
		<!--/.Carousel Paquetes-->
		
	<t:temp_bot></t:temp_bot>

</html>