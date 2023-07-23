function MyOrder(){
	window.location.href = 'OrdiniCliente';
}
function FreeFields(){
	if($('#ciao').val()==='1'){
		$(".camp").prop("readonly", false);
		$('#modifica').val('Salva');
		$('#ciao').val('0');
	}
	else{
		
		document.querySelector("form").submit();
	}
}

function ResetPasswd(){
	
	document.querySelectorAll("form")[1].submit();
}

