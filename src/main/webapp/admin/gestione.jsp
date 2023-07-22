<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.lang.reflect.*"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.Base64" %>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Gestione</title>
<link rel="stylesheet" href="../style/gestione.css">
<script type="text/javascript" src="../scripts/gestione.js"></script>
</head>
<body>


	<div style="padding-bottom: 30px;">
		<%@ include file="/admin/header.html"%>
	</div>
	<% 
List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
	<%=error %>
	<br>
	<%
	}
}
%>
	<div id="container">
		<span> <input type="radio" name="option" id="option1"
			onchange="mostra()"> <label for="option1">Data</label>
		</span> <br> <span> <input type="radio" name="option"
			id="option2" onchange="mostra()"> <label for="option2">Cliente</label>
		</span> <br> <span id="cliente"> <input type="text" id="text"
			name="text">
			<button onclick="handleRadioChange()">Filtra</button>
		</span> <span id="data"> da: <input type="date" id="text1" name="text">
			a: <input type="date" id="text2" name="text">
			<button onclick="handleRadioChange()">Filtra</button>
		</span>
	</div>

	<table>
		<caption>Tabella di Gestione</caption>
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
      	      	Boolean isPromoz=false;
				
      	     	
      	      	
      	      	if(clazz.getSimpleName().equals("OrdineBean")==true){ 
      	      		btn1="Processa"; lowerbound=2;  // se si tratta di un ordine Cambio il valore del bottone dell'ordine da Salva(default) a Processa
      	      		if(ordType.equals("1")==true){
      	      			ord=true;
      	      		}
      	      		else ord=false;
      	      	
      	      	} 
      	      	
      	      	else if(clazz.getSimpleName().equals("PromozioniBean")==true) {lowerbound=0;
      	      			isPromoz=true;
      	      	
      	      %>

				<script>Filtering();</script>
				<%
      	      	
      	      	
      	      	
      	      	
      	      	} // se si tratta di una promozione mostro il codice
      	      	
            	else {lowerbound=1;readonly="";
            	
            	 %>

				<script>Filtering();</script>
				<%
            	
            	} //altrimenti non lo mostro
            	
            	
            	 
      			if(!isvoid){     //se NON è vuoto allora stampo i prodotti con i relativi header
      				
      			List<?> a= (List<?>)request.getAttribute("prodotti");
        		
        		Class<?> clazz1 = a.get(0).getClass();
        		
        		for (Field field : clazz1.getDeclaredFields()) { //Stampa gli header 
        			if(i>lowerbound){
        				field.setAccessible(true);
        				if(field.getName().equals("immagine") && !isPromoz){
        					%>
        					<th><%=field.getName() %></th>
        					<%
        				}
        		
      			%>
				<th><%=field.getName() %></th>
				<%
        			}	
        		i++;
        		}
      %>
			</tr>
		</thead> <!-- fine stampa header -->



		<tbody>
			<%
    			int j=1;
        		for (Object elements:a) {  // stampa gli elementi dell'array a nella tabella finisce a riga 131
      %>
			<tr>
				<form action="GestioneUpdate" method="post" enctype="multipart/form-data">
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
     							if(field.getName().equals("immagine")){
     								Blob blob=(Blob)value;
     								try (InputStream inputStream = blob.getBinaryStream()) {
     					                // Read the Blob data into a byte array
     					                byte[] imageData = new byte[(int) blob.length()];
     					                inputStream.read(imageData);
     					                String encodedImage = Base64.getEncoder().encodeToString(imageData);
     					               out.print("<td><img src=\"data:image/jpeg;base64," + encodedImage + "\" /></td>");
     					                
     					            } catch (Exception e) {
     					            	%><td><img src="../images/wallpaper.jpg" alt="#"></td><%
     					            }
     								
     								
     								
     								if(!isPromoz){
     								%><td><input type="file"
     										name="<%=field.getName()+String.valueOf(m.invoke(elements))%>"
     										value="<%=value%>" <%=readonly %>></td>
     							
     							<%
     							}}
     							else{
               					
               					%>
					<td><input type="text"
						name="<%=field.getName()+String.valueOf(m.invoke(elements))%>"
						value="<%=value%>" <%=readonly %>></td>
					<%     }   
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
			<form action="Insert" method="post" enctype="multipart/form-data">
				<%
      i=0;
	  for(Field field : clazz.getDeclaredFields() ){
			if(i>lowerbound){
				
				
				if(field.getName().equals("immagine")){
					%>
					
					
				
				
							
				<td><input type="file" name="<%=field.getName()%>" value=""></td>
				<%
				
				}
				else{
      %>
				<td><input type="text" name="<%=field.getName()%>" value=""></td>
				<%
			}}
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
