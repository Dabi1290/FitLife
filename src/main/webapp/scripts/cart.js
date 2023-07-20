function searchCart(userCode,isCart) {
  console.log(userCode);

  if (userCode!=-1 && userCode>=0) {
    $.get('Carrello', {"query": userCode},
            function(resp) { // on sucess
    			showCart(resp);
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
  } else {
    $("#cart").empty();
  }
}

function showCart(products) {
  $("#cart").empty();

  if (products.length > 0) {
	  
	  $.each(products, function(i,prodotto) {
		
		
		
		$("#cart").append($('<li />', {html: '<a href="Product?codProd='+prodotto.codice+'"><b>' + prodotto.nome + '</b></a>'}));
		// And then for every band add a list of their albums.
		
		});
  } else {
	   $("#cart").append("No results found.");
  }
}
