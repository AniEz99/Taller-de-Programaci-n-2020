let cat_sel = [];
let plat_sel = [];

let sin_filtro_cat = true;
let sin_filtro_plat = true;
 

/*
	Ordena y renderiza los resultados por orden alfabetico
*/
let ordenAlfa = function(resultados) {
	// Ordena
    resultados.sort(function(a, b) {
        var nombA = a.nombre.toUpperCase();
        var nombB = b.nombre.toUpperCase();
        if (nombA < nombB) {
            return -1;
        }
        if (nombA > nombB) {
            return 1;
        }
        return 0;
    });
    return resultados;
};

/*
	Ordena los resultados por orden de año y los retorna
*/
let ordenAño = function(resultados) {
	//Ordena
	resultados.sort(function(a, b) {
        if (a.fecha_ingreso.año < b.fecha_ingreso.año) {
            return 1;
        }
        if (a.fecha_ingreso.año > b.fecha_ingreso.año) {
            return -1;
        }
        if (a.fecha_ingreso.mes < b.fecha_ingreso.mes) {
            return 1;
        }
        if (a.fecha_ingreso.mes > b.fecha_ingreso.mes) {
            return -1;
        }
        if (a.fecha_ingreso.dia < b.fecha_ingreso.dia) {
            return 1;
        }
        if (a.fecha_ingreso.dia > b.fecha_ingreso.dia) {
            return -1;
        }  
        return 0;
    });
    return resultados;
};

let funcion_seleccionada = ordenAlfa;

/*
	Funciones de filtrado
*/
function filtroCat(r) {
	var tiene = false
	$.each(r.categorias, function(index, cat) {
		if (cat_sel.includes(cat)) 
			tiene = true;
	});
	return tiene;
}

function filtroPlat(r) {
	var tiene = false;
	$.each(r.plataformas, function(index, plat) {
		if (plat_sel.includes(plat))
			tiene = true;
	});
	return tiene;
}


/*
	Renderiza lista de resultados (tomando en cuenta los filtros)
*/
function renderizar(resultados) {
    // Limpia lista actual
    $(resultados_box).empty();
    // Renderiza (tomar en cuenta los filtros)
    var cantidad = 0;
    $.each(resultados, function(index,r) {
    	if ( (sin_filtro_cat || filtroCat(r)) && (sin_filtro_plat || filtroPlat(r)) )  {
	   		$(resultados_box).append(renderBloque(r));
	   		cantidad++;
	   	}
    });
    $("#cantFiltrados").html(cantidad);
}


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
   	return tira;
}


$( document ).ready(function() {
	cat_sel = categoriasTodas;
	plat_sel = plataformasTodas;
    resultados = espectaculos.concat(paquetes);
    // Se ordenan alfabeticamente por defecto
	renderizar(ordenAlfa(resultados));
    
    $("#orden_alfa").click(function() {
		funcion_seleccionada = ordenAlfa;
  		renderizar(funcion_seleccionada(resultados));
  		$(".tipo_sort").removeClass("active");
  		$(this).addClass("active");
	});
	
	$("#orden_año").click(function() {
		funcion_seleccionada = ordenAño;
		renderizar(funcion_seleccionada(resultados));
  		$(".tipo_sort").removeClass("active");
  		$(this).addClass("active");
	});
	
	$(".filtro_categoria").click( function() {
		var categoria = $(this).html();
		if ( $("#sin_filtro_categoria").hasClass("active") ){
			$("#sin_filtro_categoria").removeClass("active");
			cat_sel = [];
			cat_sel.push(categoria);
			$(this).addClass("active");
			sin_filtro_cat = false;
		} else {
			if ($(this).hasClass("active")) {
				cat_sel = $.grep(cat_sel, function(value) {
					return value != categoria;
				});
				sin_filtro_cat = false;
				$(this).removeClass("active");
				// Si no queda ninguno activo pasa a sin filtro
				if (!$(".filtro_categoria").hasClass("active") ){
					$("#sin_filtro_categoria").addClass("active");
					cat_sel = categoriasTodas;
					sin_filtro_cat = true;
					renderizar(funcion_seleccionada(resultados));
				}					
			} else {
				cat_sel.push(categoria);
				$(this).addClass("active");	
			}
		}
		renderizar(funcion_seleccionada(resultados));
	});
	
	$(".filtro_plataforma").click( function() {
		var plataforma = $(this).html();
		if ($("#sin_filtro_plataforma").hasClass("active") ){
			$("#sin_filtro_plataforma").removeClass("active");
			plat_sel = [];
			plat_sel.push(plataforma);
			$(this).addClass("active");
			sin_filtro_plat = false;
		} else {
			if ($(this).hasClass("active")) {
				plat_sel = $.grep(plat_sel, function(value) {
					return value != plataforma;
				});
				sin_filtro_plat = false;
				$(this).removeClass("active");
				// Si no queda ninguno activo pasa a sin filtro
				if (!$(".filtro_plataforma").hasClass("active") ){
					$("#sin_filtro_plataforma").addClass("active");
					plat_sel = plataformasTodas;
					sin_filtro_plat = true;
					renderizar(funcion_seleccionada(resultados));
				}			
			} else {
				plat_sel.push(plataforma);
				$(this).addClass("active");	
			}
		}
		renderizar(funcion_seleccionada(resultados));
	});
	
	$("#sin_filtro_categoria").click( function() {
		$(".filtro_categoria").removeClass("active");
		$(this).addClass("active");
		cat_sel = categoriasTodas;
		sin_filtro_cat = true;
		renderizar(funcion_seleccionada(resultados));
	});
	
	$("#sin_filtro_plataforma").click( function() {
		$(".filtro_plataforma").removeClass("active");
		$(this).addClass("active");
		plat_sel = plataformasTodas;
		sin_filtro_plat = true;
		renderizar(funcion_seleccionada(resultados));
	});
	
    
});
