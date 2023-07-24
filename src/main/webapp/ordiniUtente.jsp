<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>I miei Ordini</title>
<link rel="stylesheet" href="style/tabOrdini.css">

</head>


<body>
<%@ include file="header.jsp" %>

<table id="dynamic-table">
<caption>I miei Ordini</caption>
        <thead>
            <tr>
                <th>Codice Ordine</th>
                <th>Data</th>
                <th>Stato</th>
                <th>Fattura</th>
            </tr>
        </thead>
        
        <tbody id="table-body">
        
        	<%
                List<OrdineBean> ordini= (List<OrdineBean>) request.getAttribute("ordini");
        		String status;
                for (OrdineBean ordine : ordini) {
                	if(ordine.getIsProcessed())status="Spedito";
                	else status="In Preparazione";
            %>
            
            <tr>
                    <td><%= ordine.getCodice() %></td>
                    <td><%= ordine.getData()%></td>
                    <td><%=status%></td>
                    <td><a href="Fattura?ordcode=<%=ordine.getCodice()%>">Fattura</a></td>
                </tr>
                
                
                <% } %>
        </tbody>




</table>
</body>
</html>