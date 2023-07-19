function FreeFields(){
	if($('#ciao').val()==='1'){
		$(".camp").prop("readonly", false);
		$('#modifica').val('Salva');
		$('#ciao').val('0');
	}
	else{
		/*$(".camp").prop("readonly", true);
		$('#modifica').val('Modifica');
		$('#ciao').val('1')*/
		document.querySelector("form").submit();
	}
	

	
}