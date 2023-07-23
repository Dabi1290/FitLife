<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="model.UserBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<link rel="stylesheet" href="style/checkout.css">
<script type="text/javascript" src="scripts/registrazione.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>



<%UserBean user= (UserBean) request.getAttribute("user"); %>
<div class="principale">
<div class="informazioni">

<label for="nome">Nome</label>
<input type="text" id="nome" value="<%=user.getNome()%>">
<p class="errore" id="errore1"></p>
<label for="cognome">Cognome</label>
<input type="text" id="cognome" value="<%=user.getCognome()%>">
<p class="errore" id="errore2"></p>
<label for="indirizzo">Indirizzo</label>
<input type="text" id="indirizzo" value="<%=user.getIndirizzo()%>" placeholder="Via pippo 6">
<p class="errore" id="errore5"></p>
<label for="telefono">Telefono</label>
<input type="text" id="telefono" value="<%=user.getTelefono()%>">
<p class="errore" id="errore6"></p>


 </div>

<div class="informazioni" id="pagamento">

<label for="carta">Numero Carta</label>
<input type="text" id="carta">
<p class="errore" id="errore7"></p>
<label for="cvv">Cvv</label>
<input type="text" id="cvv">
<p class="errore" id="errore8"></p>
<label for="promozione">Promozione</label>
<input type="text" id="promozione">
<p class="errore" id="errore9"></p>


</div>
</div>


<input type="button" value="Finalizza" onclick="Ordina()">
</body>
</html>