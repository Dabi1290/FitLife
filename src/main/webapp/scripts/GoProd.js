function GoOnProduct(product){
	window.location.href="Product?codProd="+product;
}


function AddToCart(product){
	
	$.get('AggiungiProdotto', {"query": product});
            
	
}

function AddToCartGuest(product){
	
	$.get('AggiungiProdottoGuest', {"query": product});
            
	
}

