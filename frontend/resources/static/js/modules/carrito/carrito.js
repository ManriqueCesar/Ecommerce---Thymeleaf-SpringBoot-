$(document).ready(function () {
    $("#cantidad").val(1);
    let idCliente = 1;
    var total = 0;
    //carga de productos por id
    $.ajax({
      url: wsRoot + '/carrito/detalle/' + idCliente,
      type: 'GET',
      dataType: 'json',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).done(function (data) {
      console.log(data);

      var dataCarrito='';
    $.each(data, function(key, value){
        total= value.cantidad*value.precio;
        dataCarrito +='<tr>';
        dataCarrito +='<td>'+value.nombre+'</td>';
        dataCarrito +='<td>'+value.precio+'</td>';
        dataCarrito +='<td>'+value.cantidad+'</td>';
        dataCarrito +='<td value="'+value.id+'"> <a class="fa fa-trash" title="Eliminar Producto" id="btn-eliminarCarrito"></a></td>';
        dataCarrito +='</tr>';
    });
        $('#tbl-carrito').append(dataCarrito);
        console.log(total);
        $('#total').text(total);
    }).fail(function (jqXHR, textStatus, errorThrown) {
  
    })
  
  
  });

  $(document).on('click', '#btn-eliminarCarrito', function () {
    var currentRow =$(this).closest("td")
    var idCarrito = currentRow.attr('value');
    console.log(idCarrito);
    $.ajax({
        url: wsRoot + 'carrito/detalle/' + idCarrito,
        type: 'DELETE',
        dataType: 'json'
      }).done(function () {   
        $(currentRow).closest('tr').fadeOut(1500, function () { 
            Swal.fire({
                position: 'top-end',
                icon: "info",
                title: "Producto eliminado del carrito",
                showConfirmButton: false,
                timer: 1500
              });
            });
  });
});

function updateTotal(){
    
}
