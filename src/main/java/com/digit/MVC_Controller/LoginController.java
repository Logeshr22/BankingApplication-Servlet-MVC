package com.digit.MVC_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.MVC_Model.BankApp;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		BankApp curBankApp = new BankApp();
		int customer_id = Integer.parseInt(req.getParameter("customer_id"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		curBankApp.setCustomer_id(customer_id);
		curBankApp.setPin(pin);
		
		boolean loginStatus = curBankApp.login();
		if (loginStatus) {
			System.out.println(curBankApp.getAcc_no());
			session.setAttribute("curBankApp", curBankApp);
			session.setAttribute("acc_no", curBankApp.getAcc_no());
			session.setAttribute("customer_name", curBankApp.getCustomer_name());
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Home.jsp");
		} else {
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Failed.html");
		}
	}
}
