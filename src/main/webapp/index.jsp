<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.sql.Blob"%>
	<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.Base64" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="scripts/GoProd.js"></script>

<title>FitLife</title>

</head>
<body>
	<%@ include file="header.jsp"%>
	
	
	<div class="STitle">
		<h2>Novità</h2> <!-- pippo -->
	 </div>
	 <div class="boxes">
	 
	 <% 
	 List<ProductBean> product= (List<ProductBean>)request.getAttribute("products");
	 
	 
	 for(ProductBean prodotto: product){
		 
		%>
		

		<div class="box" onclick="GoOnProduct(<%=prodotto.getCodice() %>)">

			<div class="box-image">
			
			<%
			
			 Blob blob=prodotto.getImmagine();
			try (InputStream inputStream = blob.getBinaryStream()) {
	                // Read the Blob data into a byte array
	                byte[] imageData = new byte[(int) blob.length()];
	                inputStream.read(imageData);
	                String encodedImage = Base64.getEncoder().encodeToString(imageData);
	               out.print("<img alt=\"#\" src=\"data:image/jpeg;base64," + encodedImage + "\" /></td>");
			} catch (Exception e) {
	            	%><img src="images/wallpaper.jpg" alt="#"><%
	            }
			%>

			</div>
			<div class="box-text">

				<p><%=prodotto.getNome() %></p>
				<p>Da <%=prodotto.getPrezzo() %>€</p>

			</div>




		</div>
		
		
		
		
		<% 
	 }
	 %>
	

	</div>
</body>
</html>