<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
        .form-inline {
            display: inline-block;
            
        }
        .forms{
        display: flex;
  		gap: 15%;
        }
    </style>
</head>
<body>
	<div class="forms">
    <form class="form-inline" action="GestioneProdotti" method="post">
        <input type="submit" value="Prodotti">
    </form>

    <form class="form-inline" action="GestioneOrdine" method="post">
        <input type="hidden" value="1" name="OrdType">
        <input type="submit" value="Ordini">
    </form>

    <form class="form-inline" action="GestionePromozioni" method="post">
        <input type="submit" value="Promozioni">
    </form>

    <form class="form-inline" action="GestioneOrdine" method="post">
        <input type="hidden" value="0" name="OrdType">
        <input type="submit" value="Storico">
    </form>

    <form class="form-inline" action="/FitLife/Logout" method="get">
        <input type="submit" value="Logout">
    </form>
  </div>

</body>
</html>