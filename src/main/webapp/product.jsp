<%@ page import="model.*"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style/products.css">
<title>Product Page</title>
</head>
<body id="mainColor">
<%@ include file="./header.html" %>
<% ProductBean product = (ProductBean)request.getAttribute("prodotto");%>
<nav class="flex-nav">
  <div class="container">
    <div class="grid menu">
      <div class="column-xs-8 column-md-6">
        <p id="highlight">FitLife</p>
      </div>
      <div class="column-xs-4 column-md-6">
        <a href="#" class="toggle-nav">Menu <i class="ion-navicon-round"></i></a>
        <ul>
          <li class="nav-item"><a href="#">Products</a></li>
          <li class="nav-item"><a href="#">About</a></li>
          <li class="nav-item"><a href="#">My Account</a></li>
          <li class="nav-item"><a href="#">Cart (0)</a></li>
        </ul>
      </div>
    </div>
  </div>
</nav>
<main>
  <div class="container">
    <div class="grid second-nav">
      <div class="column-xs-12">
        <nav>
          <ol class="breadcrumb-list">
            <li class="breadcrumb-item active"><%=product.getNome() %></li>
          </ol>
        </nav>
      </div>
    </div>
    <div class="grid product">
      <div class="column-xs-12 column-md-7">
        <div class="product-gallery">
          <div class="product-image">
          <%							Blob blob=product.getImmagine(); 
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
        <h2><%=product.getPrezzo()+"$" %></h2>
        <div class="description">
          <p><%=product.getDescrizione() %></p>
        </div>
        <button class="add-to-cart">Add To Cart</button>
      </div>
    </div>
  </div>
</main>
</body>
</html>