<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
 <span>
    
    <a href="">Home</a>
    <label>Cerca</label>
    <input type="text" name="cerca" placeholder="Proteine">
    <%
    Boolean isLogged=true; //Controllo per verificare se l'utente è registrato, dovrà essere rimpiazzato!!
    if(!isLogged){ %>
    <a href="">Login</a>
    <a href="">Register</a>
    <%}if(isLogged){%>
    <a href="">Account</a>
    <%} %>
    <a href="">Carrello</a>
   </span>
</body>
</html>