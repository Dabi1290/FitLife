<%@page import="model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>FitLife</title>
<link rel="stylesheet" href="style/userArea.css">
<script type="text/javascript" src="scripts/userArea.js"></script>
<script type="text/javascript" src="scripts/jquery-3.7.0.min.js"></script>
</head>
<body>
<div class="go-back">
<a href="ciao"><img src="images/arrow.png" alt="Home"></a>


</div>
<%
UserBean bean= (UserBean) request.getAttribute("utente");

%>
<div class="main">

<div class="inputGroup ordini" onclick="MyOrder()">
		<input type="button" id="ordini"  value="I miei Ordini" >
		
	</div>	


<form action="UpdateUser" method="post" >
	<div class="inputGroup">
		<label for="email1">Nome</label>
		<input type="text" id="email" name="Nome"class="camp" value="<%=bean.getNome() %>"  readonly/>
		<span class="indicator"></span>
	</div>
	<div class="inputGroup">
		<label for="password">Cognome</label>
		<input type="text" id="cognome" name="Cognome"class="camp" value="<%=bean.getCognome() %>" readonly/>
	</div>
	<div class="inputGroup">
		<label for="telefono">Telefono</label>
		<input type="text" id="telefono" name="Telefono"class="camp"value="<%=bean.getTelefono() %>" readonly/>
		<span class="indicator"></span>
	</div>
	<div class="inputGroup">
		<label for="Indirizzo">Indirizzo</label>
		<input type="text" id="Indirizzo" name="indirizzo" class="camp" value="<%=bean.getIndirizzo() %>" readonly/>
		<span class="indicator"></span>
	</div>
	<div class="inputGroup">
		<label for="email">Email</label>
		<input type="text" id="email" name="Email"class="camp" value="<%=bean.getEmail() %>" readonly/>
		<span class="indicator"></span>
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

	<div class="inputGroup">
		<input type="button" id="modifica" onclick="FreeFields()" value="Modifica" >
		<input type="hidden" id="ciao" value="1">
	</div>	
	
	
	
</form>

<form action="PassReset" class="reset">
<div class="inputGroup">
		<label for="password">Password</label>
		<input type="text" id="password" name="Password" />
		<span class="indicator"></span>
	</div>
<div class="inputGroup">
		<input type="button" id="modifica" onclick="ResetPasswd()" value="Reset Password" >
		
	</div>	

</form>

</div>





</body>
</html>