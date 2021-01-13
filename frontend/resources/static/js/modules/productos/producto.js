$(document).ready(function () {


  $("#cantidad").val(1);

 // idProducto = obtenerIdUrl();

  idProducto = 4

  //carga de productos por id
  $.ajax({
    url: wsRoot + '/productos/' + idProducto,
    type: 'GET',
    dataType: 'json',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  }).done(function (data) {
    console.log(data);
    $("#stock").text(data.cantidad);
    $("#nombre").text(data.nombre);
    $("#precio").text(data.precio);

  }).fail(function (jqXHR, textStatus, errorThrown) {

  })


});

  $(document).on('click', '#qtyUp', function () {
    
    var getCantidad= $("#cantidad").val();
    var getStock =$("#stock").text();
    console.log(getStock);
    if (getCantidad < getStock*1){
    getCantidad = getCantidad*1 + 1;
    $("#cantidad").val(getCantidad);
    }
    console.log(getCantidad);

  });


  $(document).on('click', '#qtyDown', function () {
    var getCantidad= $("#cantidad").val();
    if (getCantidad > 1) { 
    getCantidad = getCantidad*1 - 1;
    $("#cantidad").val(getCantidad);
  }
  });

  //agregar al carrito
  $(document).on('click', '#carritoAdd', function () {
        //idProducto = obtenerIdUrl();
        var idProducto = 4
        var temp = false;
        let idCliente = 1;

        $.ajax({
          url: wsRoot + '/carrito/detalle/' + idCliente,
          type: 'GET',
          dataType: 'json',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        }).done(function (data) {
            carritos = [];
            carritos = data;
            console.log(data);
            console.log(data.length);
            for (i = 0; i < data.length; i++) {
              console.log(data.length);
              if (idProducto == carritos[i].idProducto) {
                console.log(carritos[i].idProducto);
                  temp = true;
                break;
              }
            }
            if (data.cantidad === 0) {
  
              Swal.fire({
                position: "top-end",
                icon: "error",
                title: "¡Error al agregar al carrito!",
                showConfirmButton: false,
                timer: 2000,
              });
  
            }
            else {
              console.log(" HAY PRODUCTO")
            }
  
            if (temp == false) {
              request = {};
              request.idCliente = 1;
              request.idProducto = idProducto;
              request.cantidad = $("#cantidad").val();
  
              //agregar al carrito

              $.ajax({
                url: wsRoot + '/carrito/detalle',
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
                  title: 'Agregado al carrito',
                  text: 'Lo podrás ver en MiCarrito',
                })
              }).fail(function (jqXHR, textStatus, errorThrown) {
                Swal.fire({
                  position: "top-end",
                  icon: "error",
                  title: "¡Error al agregar al carrito!",
                  showConfirmButton: false,
                  timer: 2000,
                });
              });

            } else {
              Swal.fire({
                title: 'El producto ya se encuentra en tu Carrito',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ver mi carrito',
                cancelButtonText: 'Entendido'
              }).then((result) => {
                if (result.isConfirmed) {
                  window.location.href = "carrito/"+idCliente+".html";
                }
              })
            }
          }
          );
      });



 //get id in url
 function obtenerIdUrl(){
  url = "http://localhost/Borrar/resources/templates/modules/productos/producto/1.html"
  let id = url.substring(
    url.lastIndexOf("/") + 1,
    url.lastIndexOf("."));
    return id;
}