<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="view.css">
    <title>View</title>
</head>
<body>
	<header>
		<h3><%= (String) request.getAttribute("login")%></h3>
		<a href="login.jsp">Deconnexion</a>
	</header>
    <div class="box">
        <div class="wallet dash">
            <a href="Wallet?login=<%= (String) request.getAttribute("login") %>"><h2 class="title">Wallet</h2></a>
            <h1 class="value"><%= (String) request.getAttribute("w_value")+" Ar" %></h1>
        </div>
        <div class="bank dash">
            <a href="Bank?login=<%= (String) request.getAttribute("login") %>"><h2 class="title">Bank</h2></a>
            <h1 class="value"><%= (String) request.getAttribute("b_value")+" Ar" %></h1>
        </div>
        <div class="saving dash">
            <a href="Saving?login=<%= (String) request.getAttribute("login") %>"><h2 class="title">Saving</h2></a>
            <h1 class="value"><%= (String) request.getAttribute("s_value")+" Ar" %></h1>
        </div>
    </div>
    <div class="total">
        <h1 class="title">Total : </h1>
        <h1 class="value">
	        <% 
	        	int w = Integer.parseInt((String) request.getAttribute("w_value"));
		        int b = Integer.parseInt((String) request.getAttribute("b_value"));
		        int s = Integer.parseInt((String) request.getAttribute("s_value"));
		        out.println((w+b+s)+" Ar");
	        %>
        </h1>
    </div>
    <div class="box transit">
        <div class="expenses dash">
            <h2 class="title">Today's expenses: </h2>
            <h1 class="value"><%= (String) request.getAttribute("t_expenses")+" Ar" %></h1>
        </div>
        <div class="income dash">
            <h2 class="title">Today's income: </h2>
            <h1 class="value"><%= "+ " + (String) request.getAttribute("t_income")+" Ar" %></h1>
        </div>
    </div>
</body>
</html>