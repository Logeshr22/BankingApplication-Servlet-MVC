package com.digit.MVC_Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.MVC_Model.Loan;

@WebServlet("/Loan")
public class LoanController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Loan loan = new Loan();
		HttpSession session = req.getSession();

		String loanType = req.getParameter("loan");
		System.out.println(loanType);
		loan.setLoanType(loanType);

		System.out.println("Prev: " + loan.getLoanID());
		boolean loanStatus = loan.LoanApply();
		System.out.println("After: " + loan.getLoanID());		
				
		if (loanStatus) {
			System.out.println(loan.getDescription());
			session.setAttribute("curLoanObject", loan);
			resp.sendRedirect("/BankingApplication-Servlet-MVC/LoanDetails.jsp");
		} else {
			resp.sendRedirect("/BankingApplication-Servlet-MVC/Failed.html");
		}
	}
}