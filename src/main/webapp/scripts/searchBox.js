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
    $("#searchResults").empty();
  }
}

function displayResults(products) {
  $("#searchResults").empty();

  if (products.length > 0) {
	  
	  $.each(products, function(i,prodotto) {
		
		
		
		$("#searchResults").append(prodotto.nome + " </br>");
		// And then for every band add a list of their albums.
		
		});
  } else {
	   $("#searchResults").append("No results found.");
  }
}
