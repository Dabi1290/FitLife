<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Pagina di Registrazione</title>
  <link href="" rel="stylesheet"><link rel="stylesheet" href="style/registrazione.css">
  <link rel="stylesheet" href="style/style.css">
  <script  src="./scripts/registrazione.js"></script>
</head>
<body>
<div class="principale">
<div class="row"><div class="left-side"><div class="icon"><a href="index.jsp"><img alt="#" src="images/arrow.png" class="arrow"></a></div></div><img class="logo" alt="#" src="images/logo.png"></div> 
<form action="Register" method="post" class="registrazioneForm">
	<div class="inputGroup inputGroup1">
		<label for="email1">Nome</label>
		<input type="text" id="nome" name="username"class="email" maxlength="256" placeholder="Nome" onchange="rightName()">
			<p class="errore" id="errore1"></p>
		<span class="indicator"></span>
	</div>
	<div class="inputGroup inputGroup2" >
		<label for="password">Cognome</label>
		<input type="text" id="cognome" name="password"class="password" placeholder="Cognome" onchange="rightSurname()"/>
			<p class="errore" id="errore2"></p>
	</div>
	<div class="inputGroup inputGroup3">
		<label for="password">E-Mail</label>
		<input type="text" id="email" name="password"class="password" placeholder="nome@server.dominio" onchange="rightEmail()"/>
			<p class="errore" id="errore3"></p>
	</div>
	<div class="inputGroup inputGroup4">
		<label for="password">Password</label>
		<input type="password" id="password" name="password"class="password" placeholder="Password" onchange="rightPassword()"/>
			<p class="errore" id="errore4"></p>
	</div>

	<div class="inputGroup inputGroup3">
		<button id="login">Register</button>
	</div>	
</form>
</div>

</body>
</html>