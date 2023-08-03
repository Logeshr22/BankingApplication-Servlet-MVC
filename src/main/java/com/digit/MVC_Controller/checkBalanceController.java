package com.digit.MVC_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.MVC_Model.BankApp;

@WebServlet("/checkBalance")
public class checkBalanceController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		BankApp curBankApp = new BankApp();
		HttpSession session = req.getSession();
					
		int acc_no = (int) session.getAttribute("acc_no");
		curBankApp.setAcc_no(acc_no);
				
		boolean checkBalanceStatus = curBankApp.checkBalance();
		if(checkBalanceStatus) {
			session.setAttribute("balance", curBankApp.getBalance());
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Balance.jsp");
		}
		else {
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Failed.html");
		}
	}
}
