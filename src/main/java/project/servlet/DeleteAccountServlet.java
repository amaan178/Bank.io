package project.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import project.dao.*;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountIdStr = request.getParameter("accountId");

        try {
            int accountId = Integer.parseInt(accountIdStr);
            AccountDAO accountDao = new AccountDAO();
            TransactionsDAO transactionsDao = new TransactionsDAO();
            LoanDAO loanDao = new LoanDAO();
            
            transactionsDao.deleteTransactions(accountId);
            loanDao.deleteLoans(accountId);
            String result = accountDao.deleteAccount(accountId);

            if ("Account Deleted Successfully".equals(result)) {
                response.sendRedirect("accounts.jsp?success=" + result); 
            } else {
                response.sendRedirect("accounts.jsp?error=" + result); 
            }
        } catch (Exception e) {
            response.sendRedirect("accountOverview.jsp?error=Failed to delete account.");
        }
    }
}
