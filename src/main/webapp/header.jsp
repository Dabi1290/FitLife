<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">




<meta name="viewport" content="width=device-width, initial-scale=1">


<title>FitLife</title>

<link rel="stylesheet" href="style/style.css">
<link rel="stylesheet" href="style/cart.css">
<script src="scripts/searchBox.js"></script>
<script type="text/javascript" src="scripts/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="scripts/cart.js"></script>

</head>
<body>
	<div class="row">
  <img src="images/logo.png" alt="Logo" class="logo">
  <div class="right-side">
  <%
  Integer userCode; 
  Boolean isCart;
  try{
  userCode= (int) session.getAttribute("userCode");
  isCart=true;
  }
  catch(Exception e){
	userCode=-1; 
	isCart=false;
  }
  
  %>
  
<%--  id="cd-cart-trigger" --%>
  
  <a href="#" class="icon"><img src="images/cart.png"  onclick="searchCart(<%=userCode%>,<%=isCart%>)" alt="#"></a>
 
  <%Boolean isUser= (Boolean)request.getSession().getAttribute("isUser");
  if(isUser==null || !isUser){
  
  %>
  <a href="login.jsp" class="icon"><img src="images/user.png" alt="#"></a>
  <%}
  else{
  
  %>
  <a href="userArea" class="icon"><img src="images/user.png" alt="#"></a>
  <a href="Logout" class="icon"><img alt="#" src="images/logout.png"></a>
  <%} %>
  
  </div>
</div>
	<nav>

		<div class="navbar">
			<div class="prov">
			<a href="index.jsp">Home</a> <a href="about.jsp">About</a> <a
				href="Prodotti">Products</a> <a href="Categories">Categories</a>
			<a href="contact.html">Contact us</a>
			
			</div>
			<div class="search-box">
				<input type="text" id="searchBox" placeholder="Search..." onkeyup="searchProducts()">
				
				
			</div>
			
			
		</div>
		
		
		
		
		<div id="cd-shadow-layer"></div>




	<div id="cd-cart">
		<h2>Carrello</h2>
		<ul class="cd-cart-items " id="cart">
		
		</ul> <!-- cd-cart-items -->

		<div class="cd-cart-total">
			<p >Total <span id="totale"></span></p>
		</div> <!-- cd-cart-total -->

		<a href="FinalizzaOrdine" class="checkout-btn">Checkout</a>
		<div class="checkout-btn" id="svuota" onclick="EmptyCart()">Svuota Carrello</div>
		
		
	</div>
	
	
		<ul id="suggestions"></ul>
	</nav>


</body>
</html>