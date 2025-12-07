package project.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import project.dao.AccountDAO;
import project.dao.TransactionsDAO;
import project.model.Transactions;
import project.model.Account;
import project.model.Customer;

@WebServlet("/transactionServlet")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html";
    private TransactionsDAO transactionDao = new TransactionsDAO();
    private AccountDAO accountDao = new AccountDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp?error=You need to login first.");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");

        String transactionType = request.getParameter("transactionType");
        String accountIdStr = request.getParameter("accountId");
        String transactionAmountStr = request.getParameter("transactionAmount");
        String description = request.getParameter("description");

        try {
            // Input validation
            if (transactionType == null || transactionType.isEmpty() ||
                accountIdStr == null || accountIdStr.isEmpty() ||
                transactionAmountStr == null || transactionAmountStr.isEmpty()) {
                
                response.sendRedirect("transaction.jsp?error=All fields are required.");
                return;
            }

            int accountId = Integer.parseInt(accountIdStr);
            double transactionAmount;

            // Validate transaction amount
            try {
                transactionAmount = Double.parseDouble(transactionAmountStr);
                if (transactionAmount <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("transaction.jsp?error=Invalid transaction amount.");
                return;
            }

            Account account = accountDao.getAccountById(accountId);
            if (account == null) {
                response.sendRedirect("transaction.jsp?error=Account not found.");
                return;
            }

            double newBalance = account.getAccountBalance();
            if (transactionType.equalsIgnoreCase("Deposit")) {
                newBalance += transactionAmount;
            } else if (transactionType.equalsIgnoreCase("Withdraw")) {
                if (newBalance >= transactionAmount) {
                    newBalance -= transactionAmount;
                } else {
                    response.sendRedirect("transaction.jsp?error=Insufficient balance.");
                    return;
                }
            } else {
                response.sendRedirect("transaction.jsp?error=Invalid transaction type.");
                return;
            }

            // Update the account balance
            boolean updateSuccess = accountDao.updateAccountBalance(accountId, newBalance);
            if (!updateSuccess) {
                response.sendRedirect("transaction.jsp?error=Failed to update account balance.");
                return;
            }

            // Create and save the transaction
            Transactions transaction = new Transactions();
            transaction.setTransactionType(transactionType);
            transaction.setAccountId(accountId);
            transaction.setTransactionAmount(transactionAmount);
            transaction.setTransactionDesc(description);
            transaction.setCustomerId(customer.getCustomerId());

            transactionDao.saveTransactions(transaction);

            response.sendRedirect("transactions.jsp");
        } catch (Exception e) {
            response.sendRedirect("transaction.jsp?error=" + e.getMessage());
        }
    }
}
