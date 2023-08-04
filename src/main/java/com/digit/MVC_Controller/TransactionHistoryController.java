package com.digit.MVC_Controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.digit.MVC_Model.BankApp;
import com.digit.MVC_Model.Transfer;

@WebServlet("/TransactionHistory")
public class TransactionHistoryController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession curSession = req.getSession();
        BankApp curBankUser = (BankApp) curSession.getAttribute("bankapp");
        if (curBankUser == null) {
            curSession.setAttribute("ERROR_MSG", "Invalid Customer! Session Invalid!");
            resp.sendRedirect("/BankingApplication-Servlet-MVC/Failed.html");
            return;
        }
        Transfer curTransaction = new Transfer();
        ArrayList<Transfer> receiveTransactionsList = curTransaction.viewAllTransactions(curBankUser.getAcc_no());
        curSession.setAttribute("ALL_TRANSACTIONS", receiveTransactionsList);
        curSession.setAttribute("isListGenerated", "true");
        resp.sendRedirect("/BankingApplication-Servlet-MVC/TransactionHistory.jsp");
    }
}