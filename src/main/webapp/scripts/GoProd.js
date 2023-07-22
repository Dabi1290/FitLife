function GoOnProduct(product){
	window.location.href="Product?codProd="+product;
}


function AddToCart(product){
	
	$.get('AggiungiProdotto', {"query": product});
            
	
}