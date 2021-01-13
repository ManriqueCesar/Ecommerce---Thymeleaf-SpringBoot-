$(document).ready(function () {
  $('#modal-login').modal('toggle');

})


$(document).on('click', '#btn-login', function () {
  $("#content").LoadingOverlay("show", {
    background  : "rgba(0, 0, 0, 0.4)"
});



  $('#alerta').removeClass();
  // let loadingDiv = $('<div>').addClass('fa-2x').prependTo('#content');
  // let loadingSpin = $('<i>').addClass('fa fa-spinner fa-spin fa-2x').prependTo(loadingDiv);
  console.log($('#txt-user').val());
  console.log($('#txt-password').val());

  var request = {};
  var usuario = $('#txt-user').val();
  var password = $('#txt-password').val();

  request.usuario = usuario;
  request.password = password;

  $.ajax({
      url: wsRoot + 'auth',
      type: 'POST',
      dataType: 'json',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      data: JSON.stringify(request)
    }).done(function(data, textStatus, jqXHR) {
      $("#content").LoadingOverlay("hide", true);
      $('#modal-login').modal('hide');
        var status = jqXHR.status;   
        console.log(status);
        if (status == 200){ 
          Swal.fire({
            icon: "success",
            title: "Bienvenido",
            showConfirmButton: false,
            timer: 1500
          });

        }else{
          Swal.fire({
            icon: "alert",
            title: "Ocurrio un error, intente nuevamente",
            showConfirmButton: false,
            timer: 1500
          });
      }
    }).fail(function (jqXHR, textStatus, errorThrown) {
      $("#content").LoadingOverlay("hide", true);
    
        Swal.fire({
          icon: "error",
          title: "Usuario o contrasena incorrectos",
          showConfirmButton: false,
          timer: 1500
        });
      
    });

});