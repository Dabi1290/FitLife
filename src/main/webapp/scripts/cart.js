jQuery(document).ready(function($){
	
	let $L = 90,
		
		
		
		$lateral_cart = $('#cd-cart'),
		$shadow_layer = $('#cd-shadow-layer');

	$shadow_layer.on('click', function(){
		$lateral_cart.removeClass('speed-in');
		
		$shadow_layer.removeClass('is-visible');
		$('body').removeClass('overflow-hidden');
	});

	
	$(window).on('resize', function(){
		
		
		if( $(window).width() >= $L) {
			$lateral_cart.removeClass('speed-in');
			$shadow_layer.removeClass('is-visible');
			$('body').removeClass('overflow-hidden');
		}

	});
});

function toggle_panel_visibility ($lateral_panel, $background_layer, $body) {
	if( $lateral_panel.hasClass('speed-in') ) {
		$lateral_panel.removeClass('speed-in');
		$background_layer.removeClass('is-visible');
		$body.removeClass('overflow-hidden');
	} else {
		$lateral_panel.addClass('speed-in');
		$background_layer.addClass('is-visible');
		$body.addClass('overflow-hidden');
	}
}



function searchCart(userCode,isCart) {
	


  if (userCode==-1 || userCode>=0) {
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
	let totale=0;
  if (products.length > 0) {
	  let i=0;
	  $.each(products, function(i,prodotto) {
		
		
		totale+=prodotto.prezzo*prodotto.quantita;
		$("#cart").append($('<li />', { id: 'row' + i,html: '<div class="line-cart">'+prodotto.nome+'<span class="qty"><img class="sign" src="images/minus.png" onclick="MinusCart('+i+','+prodotto.codice+')"><label id="qty-number" >'+prodotto.quantita+'</label><img class="sign"src="images/plus.png"onclick="PlusCart('+prodotto.codice+','+i+')"></span></div><a href="Product?codProd='+prodotto.codice+'"><div class="cd-price">'+prodotto.prezzo+'€</div></a><div class="cd-item-remove cd-img-replace" onclick="DeleteFromCart('+i+','+prodotto.codice+')">Remove</div>'}));
		i+=0;
		
		});
  }
  $('#totale').text(totale+'€');
  let $lateral_cart = $('#cd-cart');
		let $shadow_layer = $('#cd-shadow-layer');
	
	
	toggle_panel_visibility($lateral_cart, $shadow_layer, $('body'));
}





function DeleteFromCart(index,codice){
	
    $.get('RimuoviProdotto', {"query": codice},
            function(resp) { // on sucess
    			if(resp){
					$('#row'+index).remove();
					 let Prices = $("#cart div.cd-price");
					 let quantity= $("#cart span.cd-qty")
					 let totalPrice = calculateTotal(Prices, quantity);
					 $('#totale').text(totalPrice+'€');
					 
					 
					 
				}
				
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
    
      
  
}

function EmptyCart(){
	 $.get('SvuotaCarrello',
            function(resp) { // on sucess
    			if(resp){
					 $('#cart').empty();
					 $('#totale').text('0€');
					 
				}
				
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
    
}
function parseQuantity(qtyString) {
        return parseInt(qtyString.replace("x", ""));
    }
    
    
    function calculateTotal(prices, quantities) {
        let total = 0;
        prices.each(function(index) {
            let price = parseFloat($(this).text());
            let quantity = parseQuantity(quantities.eq(index).text());
            total += price * quantity;
        });
        return total;
    }



function MinusCart(i,cod){
	
	let qty=parseInt($('#row'+i).find('label').text());
	if(qty>1){
	let prec = parseInt($('#qty-number').text());
    let newValue = prec -1;
    $('#qty-number').text(newValue);
    }
    if(qty==1){
		
		DeleteFromCart(i,cod)
	}
	UpdateProducts(cod,qty-1);
	let Prices = $("#cart div.cd-price");
	let quantity= $("#cart label")
	let totalPrice = calculateTotal(Prices, quantity);
	$('#totale').text(totalPrice+'€');
}

function PlusCart(product,i){
	let qty=$('#row'+i).find('label').text();
	$.get('NumberOfProductPlus', {"query": product,"qty":qty},function() {
  	let prec = parseInt(qty);

    // Increment the value by 1
    let newValue = prec + 1;

    // Update the text attribute with the new value
    $('#row'+i).find('label').text(newValue)
    UpdateProducts(product,prec+1);
     let Prices = $("#cart div.cd-price");
	 let quantity= $("#cart label")
	 let totalPrice = calculateTotal(Prices, quantity);
	 $('#totale').text(totalPrice+'€');
}).fail(function() {
  
});
}


function UpdateProducts(prod,qty){
	
	$.get('AggiornaQuantita', {"query": prod,"qty":qty});
}


