<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.lang.reflect.Field" %>

<!DOCTYPE html>
<html>
<head>
  <title>Gestione</title>
  <style>
    table {
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid black;
      padding: 8px;
    }
  </style>
</head>
<body>
  <table>
    <thead>
      <tr>
     
      <%-- JSP code to generate table rows dynamically --%>
      <%
      	List<?> a= (List<?>)request.getSession().getAttribute("prodotti");
        // Generate the elements dynamically
        Class<?> clazz1 = a.get(0).getClass();
        int i=0;
        for (Field field : clazz1.getDeclaredFields()) {
        	if(i!=0){
        	field.setAccessible(true);
        	
        		
                %>
                <th>
                <%=field.getName() %>
                </th>
                <%
        	
            
        }
        	i++;}
        %>
           
      </tr>
    </thead>
    <tbody>
    <%
  
        for (Object elements:a) {
        	%>
        	<tr>
        <%
        i=0;
        Class<?> clazz = elements.getClass();
        for (Field field : clazz.getDeclaredFields()) {
        	if(i!=0){
        	field.setAccessible(true);
        	try {
               
                Object value = field.get(elements);
                %>
                <td contenteditable="true" id="<%=i%>">
                <%=value %>
                </td>
        <%        
        	}catch (IllegalAccessException e) {
                e.printStackTrace();
            }
         
        
        }
        	i++;
        
        }
	
       %> 
       <td><button onclick="window.location.href = 'index.jsp';">Salva</button></td>
        	<td><button onclick="window.location.href = 'index.jsp';">Elimina</button></td>
       </tr>
      <% } %>
    </tbody>
  </table>
</body>
</html>
