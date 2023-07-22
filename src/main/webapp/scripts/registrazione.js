



function rightName(){
	
	let nome=document.getElementById("nome").value;
	
	if(nome.trim().length<=2){
		document.getElementById("errore1").innerHTML = "Il nome non può essere inferiore a 3 caratteri";
		return false;}
	else{
		document.getElementById("errore1").innerHTML = "";
		return true;}
}

function rightSurname(){
	
	let nome=document.getElementById("cognome").value;
	
	if(nome.trim().length<=2){
		document.getElementById("errore2").innerHTML = "Il cognome non può essere inferiore a 3 caratteri";
		return false;}
	else {
		document.getElementById("errore2").innerHTML = "";
		return true;}
}

function rightEmail(){
	let nome=" ";
	nome=document.getElementById("email").value;
	var pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!pattern.test(nome)){
    	document.getElementById("errore3").innerHTML = "email non valida";
    	return false;}
   	else{
   		document.getElementById("errore3").innerHTML = "";
   		return true;}
}

function rightPassword(){
	let nome=" ";
	nome=document.getElementById("password").value;
	if(nome.trim().length<6){
		document.getElementById("errore4").innerHTML = "La password non può essere inferiore a 7 caratteri";
		return false;}
	else {
		document.getElementById("errore4").innerHTML = "";
		return true;}
		
}

function Check() {
  const isEmailValid = rightEmail();
  const isNameValid = rightName();
  const isPasswordValid = rightPassword();
  const isSurnameValid = rightSurname();

  if (isEmailValid && isNameValid && isPasswordValid && isSurnameValid) {
    document.querySelector("form").submit();
  } else {
    return;
  }
}





function rightIndirizzo(){
	
	let nome=document.getElementById("indirizzo").value;
	var pattern=/^(Via|Strada|Viale|Vicolo|Corso|Piazza|Largo|Borgo|Piazzale|Lungomare|Corte)\s+[\w\s]+\s+\d+$/;

	if( !pattern.test(nome)){
		document.getElementById("errore5").innerHTML = "L'indirizzo non è nel formato giusto";
		return false;}
	else{
		document.getElementById("errore5").innerHTML = "";
		return true;}
}

function rightTelefono(){
	
	let nome=document.getElementById("telefono").value;
	
	if(nome.trim().length<=9){
		document.getElementById("errore6").innerHTML = "Il telefono non è corretto";
		return false;}
	else {
		document.getElementById("errore6").innerHTML = "";
		return true;}
}


function rightCarta(){
	
	let nome=document.getElementById("carta").value;
	var pattern=/^\d{16}$/;
	if(!pattern.test(nome)){
		document.getElementById("errore7").innerHTML = "Carta errata";
		return false;}
	else {
		document.getElementById("errore7").innerHTML = "";
		return true;}
}
function rightCvv(){
	
	let nome=document.getElementById("cvv").value;
	var pattern=/^\d{3}$/;
	if(!pattern.test(nome)){
		document.getElementById("errore8").innerHTML = "Cvv errato";
		return false;}
	else {
		document.getElementById("errore8").innerHTML = "";
		return true;}
}

function rightPromozione(){
	
	let nome=document.getElementById("promozione").value;
	
	if(nome.trim().length>=5){
		document.getElementById("errore9").innerHTML = "CodicePromozione errato";
		return false;}
	else {
		document.getElementById("errore9").innerHTML = "";
		return true;}
}


function Ordina() {
  const isNameValid = rightName();
  const isSurnameValid = rightSurname();
  const isTelefonoValid = rightTelefono();
  const isIndirizzoValid = rightIndirizzo();
  const isCartaValid = rightCarta();
   const isCvvValid = rightCvv();
  const isPromoValid = rightPromozione();

  if (isNameValid && isSurnameValid && isTelefonoValid && isIndirizzoValid && isCartaValid && isCvvValid && isPromoValid) {
   
    var nome = document.getElementById("nome").value;
    var cognome = document.getElementById("cognome").value;
    var indirizzo = document.getElementById("indirizzo").value;
    var telefono = document.getElementById("telefono").value;
    var carta = document.getElementById("carta").value;
    var cvv = document.getElementById("cvv").value;
    var promozione = document.getElementById("promozione").value;

    // Create the data object with the input values
    var data = {
      nome: nome,
      cognome: cognome,
      indirizzo: indirizzo,
      telefono: telefono,
      carta: carta,
      cvv: cvv,
      promozione: promozione
    };

    // Perform the POST request using fetch API
    fetch('PostCheckout', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    }).then(function(response) {
      window.location.href = "OrdiniCliente";
    }).catch(function(error) {
      // Handle errors here if needed
    });
  
    
  
  
  
  } else {
    return;
  }
}

