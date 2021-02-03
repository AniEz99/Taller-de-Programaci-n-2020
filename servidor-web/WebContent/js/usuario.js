$('#switch').click( function() {
    if ($("#switch").is(":checked")) {
        $('#lawaston').show();
        $('#pimpinela').hide();
    } else {
        $('#lawaston').hide();
        $('#pimpinela').show();
    } 
});