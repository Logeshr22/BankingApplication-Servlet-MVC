<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta charset="ISO-8859-1">
<title>Pin Change Success</title>
</head>
<body>
	<div class="formContainer">
		<div class="list">

			<h3 class="header">
				<%
				session = request.getSession();
				String name = (String) session.getAttribute("customer_name");
				out.println(name + ", Your PIN changed Successfully.");
				%>
			</h3>
			<br> <a class="LinkButton" href="Home.jsp">Return to Home</a>
		</div>
	</div>
</body>
</html>