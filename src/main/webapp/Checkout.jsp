<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<link rel="stylesheet" href="style/checkout.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="principale">
<div class="informazioni">

<label for="nome">Nome</label>
<input type="text" id="nome">
<label for="cognome">Cognome</label>
<input type="text" id="cognome">
<label for="indirizzo">Indirizzo</label>
<input type="text" id="indirizzo">
<label for="telefono">Telefono</label>
<input type="text" id="telefono">


 </div>

<div class="informazioni" id="pagamento">

<label for="carta">Numero Carta</label>
<input type="text" id="carta">
<label for="cvv">Cvv</label>
<input type="text" id="cvv">


</div>
</div>


<input type="button" value="Finalizza">
</body>
</html>