
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FitLife</title>
</head>
<body>
    
  <%@ include file="/admin/header.jsp" %>
  
  Admin
  <form action="/FitLife/Logout" method="get">
  <input type="submit" value="Logout">
  </form>
  <%@ include file="/admin/footer.jsp" %>
  </body>
</html>