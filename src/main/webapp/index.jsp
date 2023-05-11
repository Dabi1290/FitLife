
<%@page import="database.UserDao"%>
<%@page import="database.UserBean"%>
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
  
  <%
  UserDao pippo=new UserDao();
  UserBean pluto = new UserBean();
  pluto = pippo.doRetrieveByKey(1);
  %>
  <%= pluto.getName() %>
  
  <%@ include file="/footer.jsp" %>
  </body>
</html>