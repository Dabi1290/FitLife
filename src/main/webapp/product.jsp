<%@ page import="model.*"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style/products.css">
<title>FitLife</title>
<script type="text/javascript" src="scripts/GoProd.js"></script>
</head>
<body>
<% ProductBean product = (ProductBean)request.getAttribute("prodotto");%>
<%@ include file="header.jsp" %>
<main>
  <div class="container">
    <div class="grid second-nav">
      <div class="column-xs-12">
        <nav>
          <ol class="breadcrumb-list">
          </ol>
        </nav>
      </div>
    </div>
    <div class="grid product">
      <div class="column-xs-12 column-md-7">
        <div class="product-gallery">
          <div class="product-image">
          <br>
          <% Blob blob=product.getImmagine();
     								try (InputStream inputStream = blob.getBinaryStream()) {
     					                // Read the Blob data into a byte array
     					                byte[] imageData = new byte[(int) blob.length()];
     					                inputStream.read(imageData);
     					                String encodedImage = Base64.getEncoder().encodeToString(imageData);
     					               out.print("<img src=\"data:image/jpeg;base64," + encodedImage + "\" />");
     					                
     					            } catch (Exception e) {
     					            	%><img class="active" src="images/wallpaper.jpg" alt="#"><%
     					            }
     								%>
          </div>
          <ul class="image-list">
          </ul>
        </div>
      </div>
      <div class="column-xs-12 column-md-5">
        <h1><%=product.getNome() %></h1>
        <h2><%=product.getPrezzo()+"&euro;" %></h2>
        <div class="description">
          <p><%=product.getDescrizione() %></p>
        </div>
        <div class="buttons">
        <%
 			Integer ciccio=(Integer)request.getSession().getAttribute("userCode");       
        	if(ciccio==null){
        		%>
        		
        		<button class="add-to-cart" onclick="AddToCartGuest(<%=product.getCodice() %>)">Add To Cart</button>
        		
        		<%
        	}
        	else{
        		%>
        		
        		
        		<button class="add-to-cart" onclick="AddToCart(<%=product.getCodice() %>)">Add To Cart</button>
        		<%
        		
        		
        	}
        %>
        
        <div class="qty">
        <img class="sign"src="images/minus.png" alt="-"onclick="Minus()">
        <label id="qty-number" >1</label>
        <img class="sign"src="images/plus.png" alt="+"onclick="Plus(<%=product.getCodice() %>)">
        </div>
        
        </div>
        
      </div>
    </div>
  </div>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>