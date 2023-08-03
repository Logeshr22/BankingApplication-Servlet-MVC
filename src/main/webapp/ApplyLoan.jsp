<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta charset="ISO-8859-1">
<title>Apply Loan</title>
</head>
<body>
	<div class="formContainer">
		<div class="list">
			<div class="header">
				<h1>Apply Loan</h1>
			</div>
			<br>
			<div>
				<table>
					<tr>
						<td><h3>> </h3></td>
						<td><h3>Home Loan</h3></td>
						<td><h3>> </h3></td>
						<td><h3>Education Loan</h3></td>
					</tr>
					<tr>
						<td><h3>> </h3></td>
						<td><h3>Car Loan</h3></td>
						<td><h3>> </h3></td>
						<td><h3>Personal Loan</h3></td>
					</tr>
				</table>
			</div>
			<form method="post" action="Loan">
				<select name="loan" id="loan">
					<option value="Home Loan">Home Loan</option>
					<option value="Education Loan">Education Loan</option>
					<option value="Car Loan">Car Loan</option>
					<option value="Personal Loan">Personal Loan</option>
				</select>
				<div class="button">
					<input id="LoginButton" type="submit" value="Apply Loan">
				</div>
			</form>
		</div>
	</div>
</body>
</html>