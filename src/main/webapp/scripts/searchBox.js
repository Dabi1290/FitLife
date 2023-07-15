function searchProducts() {
  var searchValue = document.getElementById("searchBox").value;

  if (searchValue.length >= 2) {
    $.get('Ricerca', {"query": searchValue},
            function(resp) { // on sucess
    			displayResults(resp);
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
  } else {
    $("#suggestions").empty();
  }
}

function displayResults(products) {
  $("#suggestions").empty();

  if (products.length > 0) {
	  
	  $.each(products, function(i,prodotto) {
		
		
		
		$("#suggestions").append($('<li />', {html: '<a href="Product?codProd='+prodotto.codice+'"><b>' + prodotto.nome + '</b></a>'}));
		// And then for every band add a list of their albums.
		
		});
  } else {
	   $("#suggestions").append("No results found.");
  }
}

