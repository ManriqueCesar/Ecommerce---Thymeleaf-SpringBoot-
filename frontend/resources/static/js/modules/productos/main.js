$(document).ready(function () {
  console.log(window.location.href); 
  $.ajax({
    url: wsRoot + '/productos/',
    type: 'GET',
    dataType: 'json',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  }).done(function (data) {
    console.log(data);

    $(function () {
      let firstRow= $('<div>').addClass('card').prependTo('#productList');
      let row = $('<div>').addClass('row').prependTo(firstRow);
            let col = $('<div>').addClass('card-columns').appendTo(row);
            createCardsFor(col);
        
    })
    function createCardsFor($container) {
      for (let i = 0; i < data.length; i++) {
          let card = $('<div>').addClass('card').css({
              'margin': '10px',
              'box-shadow': '0 0 6px rgba(0,0,0, .2)'
          }).appendTo($container);
          let cardHeader = $('<div>').addClass('card-header').appendTo(card);
          // let cardImage = $('<img>').attr({
          //     alt: "alt",
          //     src: "img/mercado.png"
          // }).appendTo(cardHeader);
 
          let cardBody = $('<div>').addClass('card-body').appendTo(card);
          let bodyTitle = $('<h5>').text(data[i].nombre).addClass('text-center').appendTo(cardBody);
          let bodyText = $('<p>').text('Lorem Ipsum Dolor...').addClass('text-center').appendTo(cardBody); 
          let pText =$('<p>').appendTo(bodyText);
          i
          let stock = $('<div>').addClass('nostock m-1').text('Producto Agotado').appendTo(bodyText);
   
           
          let button = $('<button>').addClass('btn btn-sm btn-outline-success ')
          .text('Ver mas')
          .on('click', goProductDetail)
          .attr('id', data[i].id)
          .appendTo(bodyText);
  }
      }


  }).fail(function(jqXHR, textStatus, errorThrown){

        })
    
    
});

function goProductDetail() {
  var id = $(this).attr("id");
  var controller= "/productos/producto/";
  window.location.href = controller+id+ ".html";
}



