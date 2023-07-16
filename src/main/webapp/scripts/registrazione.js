function rightName(){
	let nome=" ";
	nome=document.getElementById("nome").value;
	if(nome.length<=2)
		document.getElementById("errore1").innerHTML = "Il nome non può essere inferiore a 3 caratteri";
	else 
		document.getElementById("errore1").innerHTML = "";
}

function rightSurname(){
	let nome=" ";
	nome=document.getElementById("cognome").value;
	if(nome.length<=2)
		document.getElementById("errore2").innerHTML = "Il cognome non può essere inferiore a 3 caratteri";
	else 
		document.getElementById("errore2").innerHTML = "";
}

function rightEmail(){
	let nome=" ";
	nome=document.getElementById("email").value;
	var pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!pattern.test(nome))
    	document.getElementById("errore3").innerHTML = "email non valida";
   	else
   		document.getElementById("errore3").innerHTML = "";
}

function rightPassword(){
	let nome=" ";
	nome=document.getElementById("password").value;
	if(nome.length<=6)
		document.getElementById("errore4").innerHTML = "La password non può essere inferiore a 6 caratteri";
	else 
		document.getElementById("errore4").innerHTML = "";
}
