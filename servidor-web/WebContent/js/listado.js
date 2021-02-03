/*
	Renderiza o un espectaculo o un paquete
*/
function renderBloque(r) {
	tira = "";
	if (r.tipo == "espectaculo") {
		var tira =  "<a href=DetalleEspectaculo?" + $.param(r.query_detalle) + " class='list-group-item list-group-item-action flex-column align-items-start resEspectaculo'>";
			tira += 	"<div class='row'>";
			tira += 		"<img src='" + r.urlImagen + "' class='col-3' alt=''>";
			tira += 		"<div class='col-9 right'>";
			tira += 			"<div class='d-flex w-100 justify-content-between'>";
			tira += 				"<h5 class='mb-1'>"+ r.nombre + "</h5>";
			tira += 				"<small>Duración " + r.duracion_horas + ":" + r.duracion_minutos + "</small>";
			tira += 			"</div>";
			tira += 			"<p class='mb-1'>" + r.descripcion + "</p>";
			tira += 			"<small class='text-muted'>Precio: $" + r.costo + "</small><br>";
			tira +=				"<small class='text-muted'>Fecha Ingreso: " + r.fecha_ingreso.dia + "/" + r.fecha_ingreso.mes + "/" + r.fecha_ingreso.año + "</small>";
			tira += 		"</div>";
			tira += 	"</div>";
			tira += "</a>";
   	}
   	if (r.tipo == "paquete") {
		console.log(r.tipo);
		var tira =  "<a href='DetallePaquete?" + $.param(r.query_detalle) + "'class='list-group-item list-group-item-action flex-column align-items-start resPaquete'>";
			tira += 	"<div class='row'>";
			tira += 		"<img src=' " + r.urlImagen + " ' class='col-3' alt=''>";
			tira += 		"<div class='col-9 right'>";
			tira += 			"<div class='d-flex w-100 justify-content-between'>";
			tira += 				"<h5 class='mb-1'>"+ r.nombre + "</h5>";
			tira += 				"<small>Descuento: %" + r.descuento + "</small>";
			tira += 			"</div>";
			tira += 			"<p class='mb-1'>" + r.descripcion + "</p>";
			tira +=				"<small class='text-muted'>Fecha Ingreso: " + r.fecha_ingreso.dia + "/" + r.fecha_ingreso.mes + "/" + r.fecha_ingreso.año + "</small>";
			tira += 		"</div>";
			tira += 	"</div>";
			tira += "</a>";
   	}
   	if (r.tipo == "artista") {
		console.log(r.tipo);
		var tira =  "<a href='Usuarios?" + $.param(r.query_detalle) + "'class='list-group-item list-group-item-action flex-column align-items-start resArtista'>";
			tira += 	"<div class='row'>";
			tira += 		"<img src=' " + r.urlImagen + " ' class='col-3' alt=''>";
			tira += 		"<div class='col-9 right'>";
			tira += 			"<div class='d-flex w-100 justify-content-between'>";
			tira += 				"<h5 class='mb-1'>"+ r.nombre + " " + r.apellido + "</h5>";
			tira += 				"<small>Nacimiento: " + r.fecha_nacimiento.dia + "/" + r.fecha_nacimiento.mes + "/" + r.fecha_nacimiento.año + "</small>";
			tira += 			"</div>";
			tira += 		"</div>";
			tira += 	"</div>";
			tira += "</a>";
   	}
   	if (r.tipo == "espectador") {
		console.log(r.tipo);
		var tira =  "<a href='Usuarios?" + $.param(r.query_detalle) + "'class='list-group-item list-group-item-action flex-column align-items-start resEspectador'>";
			tira += 	"<div class='row'>";
			tira += 		"<img src=' " + r.urlImagen + " ' class='col-3' alt=''>";
			tira += 		"<div class='col-9 right'>";
			tira += 			"<div class='d-flex w-100 justify-content-between'>";
			tira += 				"<h5 class='mb-1'>"+ r.nombre + " " + r.apellido + "</h5>";
			tira += 				"<small>Nacimiento: " + r.fecha_nacimiento.dia + "/" + r.fecha_nacimiento.mes + "/" + r.fecha_nacimiento.año + "</small>";
			tira += 			"</div>";
			tira += 		"</div>";
			tira += 	"</div>";
			tira += "</a>";
   	}
   	return tira;
}

function renderizar(listado) {
	$.each(listado, function(index, paq) {
		$(resultados_box).append(renderBloque(paq));
	});
}

$( document ).ready(function() {
	renderizar(lista);
});
