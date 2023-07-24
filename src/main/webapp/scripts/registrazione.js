



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
	
	let nome=document.getElementById("email").value;
	let pattern =/^[^\s@]{1,32}@[^\s@]{1,32}\.[^\s@]{1,32}$/;
    if(!pattern.test(nome)){
    	document.getElementById("errore3").innerHTML = "email non valida";
    	return false;}
   	else{
   		document.getElementById("errore3").innerHTML = "";
   		return true;}
}

function rightPassword(){
	
	let nome=document.getElementById("password").value;
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
	let pattern=/^(Via|Strada|Viale|Vicolo|Corso|Piazza|Largo|Borgo|Piazzale|Lungomare|Corte)\s+[a-zA-Z\d\s]{1,50}\s+\d+$/


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
	let pattern=/^\d{16}$/;
	if(!pattern.test(nome)){
		document.getElementById("errore7").innerHTML = "Carta errata";
		return false;}
	else {
		document.getElementById("errore7").innerHTML = "";
		return true;}
}
function rightCvv(){
	
	let nome=document.getElementById("cvv").value;
	let pattern=/^\d{3}$/;
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
   
    let nome = document.getElementById("nome").value;
    let cognome = document.getElementById("cognome").value;
    let indirizzo = document.getElementById("indirizzo").value;
    let telefono = document.getElementById("telefono").value;
    let carta = document.getElementById("carta").value;
    let cvv = document.getElementById("cvv").value;
    let promozione = document.getElementById("promozione").value;

    // Create the data object with the input values
    let data = {
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
       if (response.ok) {
    window.location.href = "OrdiniCliente"; 
  } else {
    throw new Error('Network response was not ok'); // Induce an error to trigger the catch block
  }
    }).catch(function(error) {
      window.location.href = "Checkout";
    });
  
    
  
  
  
  } else {
    return;
  }
}

