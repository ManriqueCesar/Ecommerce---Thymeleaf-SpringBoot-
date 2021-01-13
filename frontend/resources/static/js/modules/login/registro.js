$(document).ready(function () {


  })
  
  
  $(document).on('click', '#btn-register', function () {

    $("#register-content").LoadingOverlay("show", {
        background  : "rgba(0, 0, 0, 0.1)"
    });

    var anio = $('#fnac-year').val();
    var mes  = $('#fnac-month').val();
    var dia  = $('#fnac-day').val();
    var fnac = anio.concat('-').concat(mes).concat('-').concat(dia);

  
    var request = {};
    var nombre =    $('#txt-nombre').val();
    var apellido =  $('#txt-apellido').val();
    var dni =       $('#txt-dni').val();
    var fnac =      fnac;
    var celular =   $('#txt-celular').val();
    var email =     $('#txt-email').val();
    var usuario =   $('#txt-user').val();
    var password =  $('#txt-password').val();

    request.nombre= nombre;
    request.apellido= apellido;
    request.fnac= fnac;
    request.dni= dni;
    request.email= email;
    request.celular = celular;
    request.usuario = usuario;
    request.password = password;
    request.rol = "cliente";

    
    $.ajax({
        url: wsRoot + 'usuario/signUp',
        type: 'POST',
        dataType: 'json',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        data: JSON.stringify(request)
      }).done(function(data, textStatus, jqXHR) {
        $("#register-content").LoadingOverlay("hide", true);
          var status = jqXHR.status;   
          console.log(status);

            $('#txt-nombre').val('');
            $('#txt-apellido').val('');
            $('#txt-dni').val(''); 
            $('#txt-fnac').val('');
            $('#txt-email').val('');
            $('#txt-celular').val('');
            $('#txt-user').val('');
            $('#txt-password').val('');
            Swal.fire({
                icon: "success",
                title: "Usuario creado correctamente",
                showConfirmButton: false,
                timer: 1500
              });
        
      }).fail(function (jqXHR, textStatus, errorThrown) {
        $("#register-content").LoadingOverlay("hide", true);
        console.log(errorThrown);
        console.log(jqXHR);
        console.log(textStatus);
        Swal.fire({
            icon: "error",
            title: "Error al crear usuario",
            text: jqXHR.responseJSON.message,
            showConfirmButton: false,
            timer: 1500
          });

      });
  
  
  
    $('#alerta').removeClass();

  

  
  
  
  });