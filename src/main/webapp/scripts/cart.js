jQuery(document).ready(function($){
	
	var $L = 90,
		
		$cart_trigger = $('#cd-cart-trigger'),
		
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
	
	
  console.log(userCode);

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
	var totale=0;
  if (products.length > 0) {
	  var i=0;
	  $.each(products, function(i,prodotto) {
		
		
		totale+=prodotto.prezzo*prodotto.quantita;
		$("#cart").append($('<li />', { id: 'row' + i,html: '<a href="Product?codProd='+prodotto.codice+'"><span class="cd-qty">'+prodotto.quantita+'x</span>'+prodotto.nome+'<div class="cd-price">'+prodotto.prezzo+'€</div></a><div class="cd-item-remove cd-img-replace" onclick="DeleteFromCart('+i+','+prodotto.codice+')">Remove</div>'}));
		i+=0;
		
		});
  }
  $('#totale').text(totale+'€');
  $lateral_cart = $('#cd-cart');
		$shadow_layer = $('#cd-shadow-layer');
	
	
	toggle_panel_visibility($lateral_cart, $shadow_layer, $('body'));
}





function DeleteFromCart(index,codice){
	
    $.get('RimuoviProdotto', {"query": codice},
            function(resp) { // on sucess
    			if(resp){
					$('#row'+index).remove();
					 var Prices = $("#cart div.cd-price");
					 var quantity= $("#cart span.cd-qty")
					 console.log(Prices);
					 var totalPrice = calculateTotal(Prices, quantity);
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
        var total = 0;
        prices.each(function(index) {
            var price = parseFloat($(this).text());
            var quantity = parseQuantity(quantities.eq(index).text());
            total += price * quantity;
        });
        return total;
    }



