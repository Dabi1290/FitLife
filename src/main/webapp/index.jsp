
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FitLife</title>
</head>
<body>
    
  <%@ include file="/header.jsp" %>
  
  <%= request.getSession().getAttribute("user") %>
  <%@ include file="/footer.jsp" %>
  </body>
</html>