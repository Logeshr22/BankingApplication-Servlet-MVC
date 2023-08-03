package com.digit.MVC_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digit.MVC_Model.BankApp;

@WebServlet("/Register")
public class RegisterController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BankApp bankApp = new BankApp();
		bankApp.setBank_id(Integer.parseInt(req.getParameter("bank_id")));
		bankApp.setBank_name(req.getParameter("bank_name"));
		bankApp.setIfsc_code(req.getParameter("ifsc"));
		bankApp.setAcc_no(Integer.parseInt(req.getParameter("acc_no")));
		bankApp.setPin(Integer.parseInt(req.getParameter("pin")));
		bankApp.setCustomer_id(Integer.parseInt(req.getParameter("customer_id")));
		bankApp.setCustomer_name(req.getParameter("customer_name"));
		bankApp.setBalance(Integer.parseInt(req.getParameter("balance")));
		bankApp.setEmail(req.getParameter("email"));
		bankApp.setPhone(Long.parseLong(req.getParameter("phone")));
		
		boolean b = bankApp.Register();
		if(b==true) {
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Success.html");
		}
		else {
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Failed.html");
		}
	}
}
