package com.digit.MVC_Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.digit.MVC_Model.BankApp;
import com.digit.MVC_Model.Transfer;

@WebServlet("/Transfer")
public class TransferController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		BankApp bankapp = (BankApp) session.getAttribute("curBankApp");
		int pin = bankapp.getPin();
		Transfer t = new Transfer();
		t.setCust_id(Integer.parseInt(req.getParameter("customer_id")));
		t.setBank_name(req.getParameter("bank_name"));
		t.setIFSC(req.getParameter("ifsc"));
		t.setSender_accno(Integer.parseInt(req.getParameter("acc_no")));
		t.setReceiver_IFSC(req.getParameter("receiver_ifsc"));
		t.setReceiver_accno(Integer.parseInt(req.getParameter("receiver_accno")));
		t.setAmount(Integer.parseInt(req.getParameter("amount")));
		t.setPin(pin);
		boolean b = t.transferAmount();
		if (b == true) {
			resp.sendRedirect("/BankingApplication-Servlet-MVC/TransferSuccess.jsp");
		}
		else {
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Failed.html");
		}
	}
}
