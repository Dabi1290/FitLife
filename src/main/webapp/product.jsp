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
<title>Product Page</title>
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
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item"><a href="#">Household Plants</a></li>
            <li class="breadcrumb-item active">Bonsai</li>
          </ol>
        </nav>
      </div>
    </div>
    <div class="grid product">
      <div class="column-xs-12 column-md-7">
        <div class="product-gallery">
          <div class="product-image">
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
        <h2>$19.99</h2>
        <div class="description">
          <p>The purposes of bonsai are primarily contemplation for the viewer, and the pleasant exercise of effort and ingenuity for the grower.</p>
          <p>By contrast with other plant cultivation practices, bonsai is not intended for production of food or for medicine. Instead, bonsai practice focuses on long-term cultivation and shaping of one or more small trees growing in a container.</p>
        </div>
        <button class="add-to-cart">Add To Cart</button>
      </div>
    </div>
  </div>
</main>
</body>
</html>