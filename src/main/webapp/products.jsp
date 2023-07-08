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
</head>
<body>

<%@ include file="header.html" %>


<div class="container-products">
<div class="boxes-3">

<%
List<ProductBean> prodotti= (List<ProductBean>)request.getAttribute("prodotti");

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


%>




</div>
<div class="filtri"></div>

</div>

</body>
</html>