



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


