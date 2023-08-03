<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.digit.MVC_Model.Loan"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta charset="ISO-8859-1">
<title>Loan Details</title>
</head>
<body>
	<div class="formContainer">
		<div class="list">
			<h1 class="header">Applied Loan Details</h1>
			<%
			session = request.getSession();
			Loan curLoan = (Loan) session.getAttribute("curLoanObject");
			%>
			<table class="displayTable">
				<tr>
					<td>
						<h3 class="header">
							<%
							out.println("\nLoan ID  ");
							%>
						</h3>
					</td>
					<td>
						<h3 class="header">
							<%
							out.println(curLoan.getLoanID());
							%>
						</h3>
					</td>
				</tr>
								<tr>
					<td>
						<h3 class="header">
							<%
							out.println("\nLoan Type  ");
							%>
						</h3>
					</td>
					<td>
						<h3 class="header">
							<%
							out.println(curLoan.getLoanType());
							%>
						</h3>
					</td>
				</tr>				
				<tr>
					<td>
						<h3 class="header">
							<%
							out.println("\nTenure  ");
							%>
						</h3>
					</td>
					<td>
						<h3 class="header">
							<%
							out.println(curLoan.getTenure());
							%>
						</h3>
					</td>
				</tr>				<tr>
					<td>
						<h3 class="header">
							<%
							out.println("\nInterest  ");
							%>
						</h3>
					</td>
					<td>
						<h3 class="header">
							<%
							out.println(curLoan.getInterest());
							%>
						</h3>
					</td>
				</tr>				<tr>
					<td>
						<h3 class="header">
							<%
							out.println("\nDescription  ");
							%>
						</h3>
					</td>
					<td>
						<h3 class="header">
							<%
							out.println(curLoan.getDescription());
							%>
						</h3>
					</td>
				</tr>
			</table>
			<a class="LinkButton" href="Home.jsp">Return to Home</a>
		</div>
	</div>
</body>
</html>