$( document ).ready(function() {
	$("#textInputH").on('keyup', function () {
		$("#durEspectaculoH").value = $(this).value;
	});
	$("#durEspectaculoH").on('keyup', function () {
		$("#textInputH").value = $(this).value;
	});
});

function updateTextInputH(val) {
	document.getElementById('textInputH').value=val; 
}
function updateTextInputM(val) {
	document.getElementById('textInputM').value=val; 
}
function updateTextInputEmax(val) {
	document.getElementById('cantEMax').value=val; 
}
function updateTextInputEmin(val) {
	document.getElementById('cantEMin').value=val; 
}