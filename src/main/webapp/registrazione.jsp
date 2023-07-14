<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Pagina di Registrazione</title>
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700" rel="stylesheet"><link rel="stylesheet"  href="css/registrazione.css">


</head>
<body>
<div class = "registrazione">
	
	<form action="Login" method="post">
	<div class="logo">
		<img src="images/logo.png" alt="Logo" id="logo">
	</div>
	<div class="inputGroup inputGroup1">
		<label for="nome">Nome</label>
		<input type="text" id="nome" name="nome"class="nome" placeholder="Mario"/>
	
	
		<label for="Cognome">Cognome</label>
		<input type="text" id="Cognome" name="Cognome"class="Cognome" placeholder="Rossi"/>
	</div>
	<div class="inputGroup inputGroup2">
		<label for="email">Email</label>
		<input type="text" id="email" name="email"class="email" maxlength="256" placeholder="email@host.com"/>
		<span class="indicator"></span>
	</div>
	<div class="inputGroup inputGroup3">
		<label for="password">Password</label>
		<input type="password" id="password" name="password"class="password" placeholder="******"/>
		<p>Le password devono essere composte almeno da 6 caratteri e lettera maiuscola.</p>
	</div>
	<div class="inputGroup inputGroup4">
		<label for="Telefono">Telefono</label>
		<input type="tel" id="Telefono" name="Telefono"class="Telefono" />
	</div>
	
	<div class="spedizione">
		<fieldset>
			<legend>Spedizione</legend>
				<div class="inputGroup inputGroup5">
					<label for="indirizzo">Indirizzo</label>
					<input type="text" id="indirizzo" name="indirizzo"class="indirizzo" maxlength="256" placeholder="Via e n° civico"/>
					<input type="text" id="indirizzo2" name="indirizzo2"class="indirizzo" maxlength="256" placeholder="Scala, piano, interno(facoltativo)"/>
				</div>
		
				<div class="inputGroup inputGroup6">
					<label for="città">Città</label>
					<input type="text" id="citta" name="citta"class="citta" maxlength="256"/>
				</div>
		
				<div class="inputGroup inputGroup7">
					<label for="cap">Cap</label>
					<input type="text" id="cap" name="cap"class="cap" maxlength="256"/>
				</div>
		
				<div class="inputGroup inputGroup8">
					<label for="provincia">Provincia</label>
					<input type="text" id="provincia" name="provincia"class="provincia" maxlength="256"/>
				</div>
	
		</fieldset>
	</div>
	
	<% 
List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<%=error %> <br>		
	<%
	}
}
%>

	<div class="inputGroup inputGroup5">
		<a href="login.jsp" class="loginLink">Ho già un account</a>
		<button id="registrazione">Registrati</button>
	</div>	
	
	
</form>
</div>
<!-- partial -->
  <script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/1.20.3/TweenMax.min.js'></script>
<script  src="./JAVASCRIPT/login.js"></script>

</body>
</html>