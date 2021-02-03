/*
    Validador de contraseñas
*/
$('#inputContra, #inputConfContra').on('keyup', function () {
    var contra = $('#inputContra');
    var confirmacion = $('#inputConfContra');
    if (contra.val() == confirmacion.val() && contra.val() != '' && confirmacion.val() != '') {
        contra.removeClass('is-invalid');
        contra.addClass('is-valid');
        confirmacion.removeClass('is-invalid');
        confirmacion.addClass('is-valid');
    } else {
        contra.addClass('is-invalid');
        contra.removeClass('is-valid');
        confirmacion.addClass('is-invalid');
        confirmacion.removeClass('is-valid');
    }
});

/*
	Switch artista - espectador
*/
$("#tipoEspectador").click(function() {
	$(".input-artista").addClass("d-none");
	$(".input-artista").find('input, textarea').removeAttr('required');
});

$("#tipoArtista").click(function() {
	$(".input-artista").removeClass('d-none');
	$(".input-artista").find('input, textarea').addAttr('required');
});