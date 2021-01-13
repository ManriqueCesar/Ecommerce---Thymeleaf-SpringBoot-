$(document).ready(function () {
  clienteId = 1;
  productos = [];

  cargar_cliente('cbocliente', '0');
  cbo_descripcion('cboDescripcion', '0');

  //cargar productos 
  $.ajax({
    async: false,
    url: wsRoot + '/carrito/detalle/1',
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  }).done(function (data) {
    var producto='';
    $.each(data, function(key, value){
        total= value.cantidad*value.precio;
        producto +='<tr>';
        producto +='<td>'+value.nombre+'</td>';
        producto +='<td>'+value.cantidad+'</td>';
        producto +='<td>'+value.precio+'</td>';
        producto +='<td>'+value.precio*value.cantidad+'</td>';
        producto +='<td value="'+value.id+'"></td>';
        producto +='</tr>';
    });

    $('#tbl-productos').append(producto);
  });


  //obtener email
  $('#cbocliente').on('change', function () {
    let idCliente = $('#cbocliente').val();
    console.log(idCliente);
    $.ajax({
      async: false,
      url: wsRoot + '/clientes',
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).done(function (clientes) {
      for (var x = 0; x < clientes.length; x++) {
        if(idCliente == 0){
          $('#email').text('');
        }
        else if (idCliente == clientes[x].id) {
          $('#email').text(clientes[x].email)
          break;
        }
      }
    }
    )
  });



$(document).on('click', '#btn-registrar', function () {
  var items = [];

  //preparar items (idProducto y cantidad)
  class item {
    constructor(productoId, cantidad) {
      this.productoId = productoId;
      this.cantidad = cantidad;
    }
  };
    $.ajax({
      async: false,
      url: wsRoot + '/carrito/detalle/1',
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).done(function (data) {
      productos = data;
      for (i = 0; i < productos.length; i++) {
        items.push(new item(productos[i].idProducto, productos[i].cantidad));
      } 
    });

    //generar Factura

    productoId = productos.id;
    var request = {};

    request.descripcion = $("#cboDescripcion option:selected").text();
    request.observacion = $("#txtObservacion").val();
    request.idCliente = clienteId;
    request.items = items;
    console.log(request);
    $.ajax({
      url: wsRoot + '/facturas',
      type: 'POST',
      dataType: 'json',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      data: JSON.stringify(request)
    }).done(function () {
      Swal.fire({
        icon: 'success',
        title: 'Producto comprado satisfactoriamente',
        text: 'Lo podrás ver en MisCompras',
      })
    }).fail(function (jqXHR, errorThrown) {
      Swal.fire({
        position: "top-end",
        icon: "error",
        title: "¡Error al registrar productos!",
        showConfirmButton: false,
        timer: 2000,
      });
    });


});
// //limpiando campos
// cargar_cliente('cbocliente', '0');
// cbo_descripcion('cbo-descripcion', '0');
// $("txtObservacion").text("");

});




function cargar_cliente(clienteId, opcionxdefecto) {
  $('#' + clienteId).empty();
  $.ajax({
    async: false,
    url: wsRoot + '/clientes',
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  }).done(function (data) {
    $('#' + clienteId).append('<option></option>');
    for (var x = 0; x < data.length; x++) {
      $('#' + clienteId).append('<option value="' + data[x].id + '">' + data[x].nombre + ' ' + data[x].apellido + '</option>');
    }
    if (opcionxdefecto != null) $('#' + clienteId).val(opcionxdefecto);
  });
}



function cbo_descripcion(cboid, opcionxdefecto) {
  $.getJSON('../../../static/json/descripcion.json', function(data) {
      for (var x = 0; x < data.length; x++) {
          $("#" + cboid).append('<option value="' + data[x].id + '">' + data[x].titulo + '</option>');
      }
      if (opcionxdefecto != null) $('#' + cboid).val(opcionxdefecto).trigger('change');

  });
}