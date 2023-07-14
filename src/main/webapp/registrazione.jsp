<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Pagina di Registrazione</title>
  <link href="" rel="stylesheet"><link rel="stylesheet" href="style/registrazione.css">
  <link rel="stylesheet" href="style/style.css">
</head>
<body>
<div class="principale">
<div class="row"><div class="left-side"><div class="icon"><a href="index.jsp"><img alt="#" src="images/arrow.png"></a></div></div><img class="logo" alt="#" src="images/logo.png"></div> 
<form action="Register" method="post">
	<div class="inputGroup inputGroup1">
		<label for="email1">Nome</label>
		<input type="text" id="email" name="username"class="email" maxlength="256" placeholder="email@dominio.com"/>
		<span class="indicator"></span>
	</div>
	<div class="inputGroup inputGroup2">
		<label for="password">Cognome</label>
		<input type="password" id="password" name="password"class="password" />
	</div>
	<div class="inputGroup inputGroup3">
		<label for="password">E-Mail</label>
		<input type="password" id="password" name="password"class="password" />
	</div>
	<div class="inputGroup inputGroup4">
		<label for="password">Password</label>
		<input type="password" id="password" name="password"class="password" />
	</div>

	<div class="inputGroup inputGroup3">
		<button id="login">Login</button>
	</div>	
</form>
</div>

</body>
</html>