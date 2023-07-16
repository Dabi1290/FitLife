<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*"%>
    <%@ page import="java.util.List"%>
    <%@ page import="java.sql.Blob"%>
    <%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="style/all.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="header.html" %>

<div class="container">
	<% List<CategoriaBean> categorie= (List<CategoriaBean>)request.getAttribute("categorie");
	if (categorie!=null){
		for(CategoriaBean cat:categorie){
			
		%>
  <a href="Prodotti?categoria=<%=cat.getIdCategoria()%>">
  <div class="wrapper">
    <div class="card">
    <h1><%=cat.getNomeCategoria() %></h1>
    <i class="fal fa-arrow-right"></i>
    
    </div>
   
  </div>
  </a>
  <%}} %>
</div>
</body>
</html>