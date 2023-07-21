<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I miei Ordini</title>
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
<%@ include file="header.jsp" %>

<table id="dynamic-table">
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
                for (OrdineBean ordine : ordini) {
            %>
            
            <tr>
                    <td><%= ordine.getCodice() %></td>
                    <td><%= ordine.getData()%></td>
                    <td><%= ordine.getIsProcessed()%></td>
                    <td><a href="Fattura?ordcode=<%=ordine.getCodice()%>">Fattura</a></td>
                </tr>
                
                
                <% } %>
        </tbody>





</body>
</html>