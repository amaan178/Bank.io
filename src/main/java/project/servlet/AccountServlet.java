package project.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.AccountDAO;
import project.model.Account;

@WebServlet("/accountServlet")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String bankName = request.getParameter("bankName");
        String accountType = request.getParameter("accountType");
        String accountNumber = request.getParameter("accountNumber");
        String balanceAmountStr = request.getParameter("balanceAmount");

        double balanceAmount = 0.0;
        if (balanceAmountStr != null && !balanceAmountStr.isEmpty()) {
            balanceAmount = Double.parseDouble(balanceAmountStr);
        }

        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");

        Account account = new Account();
        account.setCustomerId(customerId);  
        account.setBankName(bankName);
        account.setAccountType(accountType);
        account.setAccountNumber(accountNumber);
        account.setAccountBalance(balanceAmount);

        AccountDAO accountDAO = new AccountDAO();
        accountDAO.saveAccount(account);

        response.sendRedirect("accounts.jsp");
    }
}
