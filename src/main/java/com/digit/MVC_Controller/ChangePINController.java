package com.digit.MVC_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.MVC_Model.BankApp;

@WebServlet("/changePIN")
public class ChangePINController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		int old_password = Integer.parseInt(req.getParameter("op"));
		int new_password = Integer.parseInt(req.getParameter("np"));
		int confirmNew_pass = Integer.parseInt(req.getParameter("cp"));
		
		HttpSession session = req.getSession();
		BankApp curBankApp = new BankApp();
		int accNo = (int) session.getAttribute("acc_no");
		curBankApp.setAcc_no(accNo);
		
		boolean changePasswordStatus = curBankApp.ChangePassword(new_password, confirmNew_pass);
		if(changePasswordStatus) {
			session.setAttribute("pin", curBankApp.getPin());
			resp.sendRedirect("/BankingApplication-Servlet-MVC/PinChangeSuccess.jsp");
		}
		else {
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Failed.html");
		}
	}
}
