<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.lang.reflect.*" %>

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
     
     
      <%
      		Boolean isvoid=(Boolean)request.getAttribute("void");
      		Class<?> clazz=null;
      		if(!isvoid){
      			List<?> a= (List<?>)request.getAttribute("prodotti");
        		// Generate the elements dynamically
        		Class<?> clazz1 = a.get(0).getClass();
        		int i=0;
        		for (Field field : clazz1.getDeclaredFields()) {
        			if(i>1){
        				field.setAccessible(true);
        	
        		
      %>
      <th>
                		<%=field.getName() %>
      </th>
      <%
        			}	
        		i++;
        		}
      %>
      </tr>
      </thead>
      <tbody>
      <%
    			int j=1;
        		for (Object elements:a) {
      %>
      <tr>
      <form action="GestioneUpdate">
      <%
        			i=0;
        			clazz = elements.getClass();
        			Method m=clazz.getMethod("getCodice");
        			for (Field field : clazz.getDeclaredFields()) {
        				if(i==1){
        					field.setAccessible(true);
        					Object value = field.get(elements);
        					 %>
        				      <input type="hidden" name="<%=field.getName()+String.valueOf(m.invoke(elements))%>" value="<%=value%>">
        				      <%   
        				}
        				if(i>1){
        					field.setAccessible(true);
        					try {
               					Object value = field.get(elements);
      %>
      <td><input type="text" name="<%=field.getName()+String.valueOf(m.invoke(elements))%>" value="<%=value%>"></td>
      <%        
        					}catch (IllegalAccessException e) {
                				e.printStackTrace();
            				}
         				}
        				i++;
        			}
					j++;
					
      %> 
      <input type="hidden" name="tipo" value="<%=clazz.getSimpleName()%>">
      <td><button type="submit" name="updateBtn" value="0<%=m.invoke(elements)%>">Salva</button></td>
      <td><button type="submit" name="updateBtn" value="1<%=m.invoke(elements)%>">Elimina</button></td>
      </form>
      </tr>
      <% 		}// Fine Costruzione Tabella
        	}// Fine If nel caso la tabella non è vuota 
      	
      %><tr><%
      Object elements=request.getAttribute("tipo");
      clazz = elements.getClass();
      int i=0;
      %>
      <th colspan="4">Inserisci</th></tr>
      <tr>
      <%
      for (Field field : clazz.getDeclaredFields()) {
      		if(i>1){
        	field.setAccessible(true);		
      %>
      <th>
            <%=field.getName() %>
      </th>
      <%	}
        	i++;
      }//Fine costruzione Header
      %>
      </tr>
      <form action="Insert"><%
      i=0;
	  for(Field field : clazz.getDeclaredFields() ){
			if(i>1){
      %>
      <td><input type="text" name="<%=field.getName()%>" value=""></td>
      <%
			}
			i++;
      }
      %>
      <input type="hidden" name="tipo" value="<%=clazz.getSimpleName()%>">
      <td><button type="submit" name="Inserisci" value="0">Inserisci</button></td>
      </form>
      		 
      
    </tbody>
  </table>
</body>
</html>
