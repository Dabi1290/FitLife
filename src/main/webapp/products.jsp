<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="model.*"%>
    <%@ page import="java.util.List"%>
	<%@ page import="java.sql.Blob"%>
	<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>FitLife</title>
<link rel="stylesheet" href="style/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.1/nouislider.min.css" />


</head>
<body>

<%@ include file="header.html" %>


<div class="container-products">
<div class="boxes-3">

<%
List<ProductBean> prodotti= (List<ProductBean>)request.getAttribute("prodotti");
List<CategoriaBean> categorie= (List<CategoriaBean>)request.getAttribute("categorie");
if(prodotti!=null){
for(ProductBean product: prodotti){
	%>
	
	<div class="box">

			<div class="box-image">
				<%
				Blob blob=product.getImmagine();
				try (InputStream inputStream = blob.getBinaryStream()) {
		                // Read the Blob data into a byte array
		                byte[] imageData = new byte[(int) blob.length()];
		                inputStream.read(imageData);
		                String encodedImage = Base64.getEncoder().encodeToString(imageData);
		               out.print("<td><img src=\"data:image/jpeg;base64," + encodedImage + "\" /></td>");
				} catch (Exception e) {
		            	%><img src="images/wallpaper.jpg" alt="#"><%
		            }
				
				%>

			</div>
			<div class="box-text">

				<p><%=product.getNome() %></p>
				<p>Da <%=product.getPrezzo()%> €</p>

			</div>




		</div>
		<%
	
}
}

%>




</div>





  <form class="filtri" action="Prodotti" method="get" id="filterForm">
<div class="price-range">
<div id="price-range-slider"></div>
<span id="price-range"></span>
</div>
  <div class="categories-filter">

  
  <%
  if(categorie!=null){
  	for(CategoriaBean categoria : categorie){
  		%>
  	
    <input type="radio"  class="radios"id="<%=categoria.getIdCategoria()%>-option" value="<%=categoria.getIdCategoria()%>" name="categoria">
    <label for="<%=categoria.getIdCategoria()%>-option"><%=categoria.getNomeCategoria() %></label>

  	 <div class="check"></div>
  	
  	<%
  	}
  }
  
  
  %>

	<input type="submit" value="Filtra" class="radios" onclick="filterFormSubmit()">



  <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.1/nouislider.min.js"></script>
  <script src="style/slider.js"></script>
</div>
</form>  


</div>

</body>
</html>