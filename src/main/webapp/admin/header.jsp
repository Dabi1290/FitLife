<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../CSS/header.css" rel="stylesheet">
</head>
<body>
<div class="principale">
    <a href="">Home</a>
    <div class="ricerca">
    <input type="text" name="cerca" placeholder="Proteine">
    <input type="button" name="cercaButton" value="Cerca"> 
    </div>
    <%
    Boolean isLogged=true; //Controllo per verificare se l'utente è registrato, dovrà essere rimpiazzato!!
    if(!isLogged){ %>
    <a href="">Login</a>
    <a href="">Register</a>
    <%}if(isLogged){%>
    <a href="">Account</a>
    <%} %>
    <a href="">Carrello</a>
</div>
</body>
</html>