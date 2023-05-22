
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FitLife</title>
</head>
<body>
    
  <%@ include file="/admin/header.jsp" %>
  
  Admin<br>
  <form action="GestioneProdotti">
  
  <input type="submit" value="Prodotti">
  </form>
    <form action="GestionePromozioni">
  
  <input type="submit" value="Promozioni">
  </form>
  <form action="/FitLife/Logout" method="get">
  <input type="submit" value="Logout">
  </form>
  <a href="">Gestione Prodotti</a>
  <a href="">Gestione Ordine</a>
  <a href="">Gestione Offerte</a>	
  </body>
</html>