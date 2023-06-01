<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.lang.reflect.*"%>

<!DOCTYPE html>
<html>
<head>
<title>Gestione</title>
<style>
table {
	border-collapse: collapse;
	position: absolute;
  	top: 30%;
  	
}

th, td {
	border: 1px solid black;
	padding: 8px;
}
</style>
</head>
<body>


<div style="padding-bottom:30px;">
<%@ include file="/admin/header.jsp" %>
</div>
<% 
List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<%=error %> <br>		
	<%
	}
}
%>	
	
	
	<table>
		<thead>
			<tr>


				<%
				boolean ord=true;
				int lowerbound; //0 mostra anche il codice 1 non lo mostra
				int columns; 
				int i=0;
				String readonly="readonly";
				String ordType=(String)request.getSession().getAttribute("ordType");
				String btn1="Salva";
				String btn2="Elimina";
      			Boolean isvoid=(Boolean)request.getAttribute("void"); //isvoid dice se ci sono o non ci sono record nel database
      			Class<?> clazz=null;
      			Object element=request.getAttribute("tipo");
      	      	clazz = element.getClass();
				
      	     	
      	      	
      	      	if(clazz.getSimpleName().equals("OrdineBean")==true){ 
      	      		btn1="Processa"; lowerbound=2;  // se si tratta di un ordine Cambio il valore del bottone dell'ordine da Salva(default) a Processa
      	      		if(ordType.equals("1")==true){
      	      			ord=true;
      	      		}
      	      		else ord=false;
      	      	
      	      	} 
      	      	
      	      	else if(clazz.getSimpleName().equals("PromozioniBean")==true) lowerbound=0; // se si tratta di una promozione mostro il codice
      	      	
            	else {lowerbound=1;readonly="";} //altrimenti non lo mostro
            	
            	
            	 
      			if(!isvoid){     //se NON è vuoto allora stampo i prodotti con i relativi header Finisce a riga 94
      				
      			List<?> a= (List<?>)request.getAttribute("prodotti");
        		
        		Class<?> clazz1 = a.get(0).getClass();
        		
        		for (Field field : clazz1.getDeclaredFields()) { //Stampa gli header finisce a riga 76
        			if(i>lowerbound){
        				field.setAccessible(true);
        	
        		
      %>
				<th><%=field.getName() %></th>
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
        		for (Object elements:a) {  // stampa gli elementi dell'array a nella tabella finisce a riga 131
      %>
			<tr>
				<form action="GestioneUpdate" method="post">
					<%
        			i=0;
        			clazz = elements.getClass();
        			Method m=clazz.getMethod("getCodice");
        			for (Field field : clazz.getDeclaredFields()) {
        				if(i==1){
        					field.setAccessible(true);
        					Object value = field.get(elements);
        					 %>
					<input type="hidden"
						name="<%=field.getName()+String.valueOf(m.invoke(elements))%>"
						value="<%=value%>">
					<%   
        				}
        				if(i>lowerbound){
        					field.setAccessible(true);
        					try {
               					Object value = field.get(elements);
      %>
					<td><input type="text"
						name="<%=field.getName()+String.valueOf(m.invoke(elements))%>"
						value="<%=value%>" <%=readonly %>></td>
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
					<%if(lowerbound!=0 && ord){ %>
					<td><button type="submit" name="updateBtn"
							value="0<%=m.invoke(elements)%>"><%=btn1 %></button></td>
							<%} %>
					<td><button type="submit" name="updateBtn"
							value="1<%=m.invoke(elements)%>"><%=btn2 %></button></td>
				</form>
			</tr>
			<% 		}// Fine Costruzione Tabella
        	}// Fine If nel caso la tabella non è vuota 
      if(clazz.getSimpleName().equals("OrdineBean")==false){	//crea la tabella per l'inserimento
      %><tr>
				<%
      
      
      
				i=clazz.getDeclaredFields().length;
				
      %>
				<th colspan="<%=i-lowerbound-1%>">Inserisci</th>
			</tr>
			<tr>
				<%i=0;
      for (Field field : clazz.getDeclaredFields()) {
      		if(i>lowerbound){
        	field.setAccessible(true);		
      %>
				<th><%=field.getName() %></th>
				<%	}
        	i++;
      }//Fine costruzione Header
      %>
			</tr>
			<form action="Insert" method="post">
				<%
      i=0;
	  for(Field field : clazz.getDeclaredFields() ){
			if(i>lowerbound){
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
	<%
	}%>

		</tbody>
	</table>
</body>
</html>
