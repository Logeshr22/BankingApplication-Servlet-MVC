<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.digit.MVC_Model.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="app.css">
<meta charset="ISO-8859-1">
<title>Transaction History</title>
</head>
<body>
	<%
    session = request.getSession();
    if (session == null) {
        response.sendRedirect("/BankingApplication-Servlet-MVC/Home.jsp");
        return;
    }
    BankApp curBankUser = (BankApp) session.getAttribute("bankapp");
    if (curBankUser == null) {
        response.sendRedirect("/BankingApplication-Servlet-MVC/Home.jsp");
        return;
    }
    String isListGenerated = (String) session.getAttribute("isListGenerated");
    if (isListGenerated == null) {
        response.sendRedirect("/BankingApplication-Servlet-MVC/TransactionHistory");
    } else {
        session.removeAttribute("isListGenerated");
    }
    ArrayList<Transfer> arrayList = (ArrayList<Transfer>) session.getAttribute("ALL_TRANSACTIONS");
    if (arrayList == null) {
        return;
    }
    %>
	<%
    if (arrayList.size() == 0) {
    %>
	<h1 align="center">No Transactions Made Yet!</h1>
	<%
    return;
    }
    %>
	<div class="formContainer">
		<h1 align="center">All Transactions</h1>
		<br>
		<table>
			<tr>
				<th>Transaction ID</th>
				<th>Customer ID</th>
				<th>Sender Bank Name</th>
				<th>Sender Bank IFSC Code</th>
				<th>Sender Account Number</th>
				<th>Receiver IFSC</th>
				<th>Receiver Account Number</th>
				<th>Amount of Transfer</th>
			</tr>
			<%
	for (Transfer curTransaction : arrayList) {
		String trTypeClass = curTransaction.getSender_accno() == curBankUser.getAcc_no() ? "red" : "green";
	%>
			<tr class="<%=trTypeClass%>">
				<td><%=curTransaction.getCust_id()%></td>
				<td><%=curTransaction.getBank_name()%></td>
				<td><%=curTransaction.getIFSC()%></td>
				<td><%=curTransaction.getSender_accno()%></td>
				<td><%=curTransaction.getReceiver_IFSC()%></td>
				<td><%=curTransaction.getReceiver_accno()%></td>
				<td><%=curTransaction.getAmount()%></td>
				<td><%=curTransaction.getTransaction_id()%></td>
				<%
			}
			%>
			</tr>
		</table>
		</div>
</body>
</html>