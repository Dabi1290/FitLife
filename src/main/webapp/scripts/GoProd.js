function GoOnProduct(product){
	window.location.href="Product?codProd="+product;
}


function AddToCart(product){
	let qty=$('#qty-number').text();
	$.get('AggiungiProdotto', {"query": product,"qty":qty});
            
	
}

function AddToCartGuest(product){
	let qty=$('#qty-number').text();
	$.get('AggiungiProdottoGuest', {"query": product,"qty":qty});
            
	
}

function Minus(){
	let qty=parseInt($('#qty-number').text());
	if(qty>1){
	let prec = parseInt($('#qty-number').text());
    let newValue = prec -1;
    $('#qty-number').text(newValue);
    }
	
}

function Plus(product){
	let qty=$('#qty-number').text();
	$.get('NumberOfProductPlus', {"query": product,"qty":qty},function() {
  	let prec = parseInt($('#qty-number').text());

    // Increment the value by 1
    let newValue = prec + 1;

    // Update the text attribute with the new value
    $('#qty-number').text(newValue);
}).fail(function() {
  
});
}