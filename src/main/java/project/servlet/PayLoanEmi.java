package project.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.Transaction;

import project.conn.HibernateUtil;
import project.model.Customer;
import project.model.Loan;
import project.model.Account;
import project.model.Transactions;

import project.dao.LoanDAO;
import project.dao.AccountDAO;
import project.dao.TransactionsDAO;

@WebServlet("/PayLoanEmi")
public class PayLoanEmi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        String loanIdStr = request.getParameter("loanId");
        String loanPaidEmiStr = request.getParameter("loanPaidEmi");
        String loanRemainEmiStr = request.getParameter("loanRemainEmi");
        String loanNextEmiDateStr = request.getParameter("loanNextEmiDate");
        String accountIdStr = request.getParameter("accountId");
        String loanEmiAmountStr = request.getParameter("loanEmiAmount");
        
        int loanId = 0;
        if (loanIdStr != null && !loanIdStr.isEmpty()) {
        	loanId = Integer.parseInt(loanIdStr);
        }
        
        int loanPaidEmi = 0;
        if (loanPaidEmiStr != null && !loanPaidEmiStr.isEmpty()) {
        	loanPaidEmi = Integer.parseInt(loanPaidEmiStr);
        }
        
        int loanRemainEmi = 0;
        if (loanRemainEmiStr != null && !loanRemainEmiStr.isEmpty()) {
        	loanRemainEmi = Integer.parseInt(loanRemainEmiStr);
        }
        
        int accountId = 0;
        if (accountIdStr != null && !accountIdStr.isEmpty()) {
        	accountId = Integer.parseInt(accountIdStr);
        }
        
        double loanEmiAmount = 0.0;
        if (loanEmiAmountStr != null && !loanEmiAmountStr.isEmpty()) {
        	loanEmiAmount = Double.parseDouble(loanEmiAmountStr);
        }

        Date loanNextEmiDate = null;
        
        try {
            if (loanNextEmiDateStr != null && !loanNextEmiDateStr.isEmpty()) {
            	loanNextEmiDate = DATE_FORMAT.parse(loanNextEmiDateStr);
            }
        } catch (ParseException e) {
            // Handle the exception if parsing fails
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
            return;
        }
        
        int newLoanPaidEmi = loanPaidEmi+1;
        int newLoanRemainEmi = loanRemainEmi-1;
        Date newLoanLastEmiDate = new Date();
        Date newLoanNextEmiDate = null;
        
        if (loanNextEmiDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(loanNextEmiDate);
            calendar.add(Calendar.MONTH, 1); // Move to the next month
            newLoanNextEmiDate = calendar.getTime();
        }
        String loanStatus;
        if (loanRemainEmi == 1) {
        	loanStatus = "Closed";
        } else {
        	loanStatus = "Open";
        }
       
        HttpSession session = request.getSession();
        int customerId = (int) session.getAttribute("customerId");
        String transactionType = "Loan EMI";
        double transactionAmount = loanEmiAmount;
        String transactionDesc = "EMI Deduction";
        
        
        LoanDAO loanDao = new LoanDAO();
        AccountDAO accountDao = new AccountDAO();
        TransactionsDAO transactionsDao = new TransactionsDAO();
        Account account = (Account) accountDao.getLoanAccountBalance2(accountId);
        Transactions transaction = new Transactions(accountId, customerId,transactionType,transactionAmount,transactionDesc);
        
        if(account.getAccountBalance()>loanEmiAmount) {
        	loanDao.updateLoan(loanId, newLoanPaidEmi, newLoanRemainEmi, newLoanLastEmiDate, newLoanNextEmiDate, loanStatus);
        	accountDao.setLoanAccountBalance(accountId, account.getAccountBalance()-loanEmiAmount);
        	transactionsDao.saveTransactions(transaction);
        	response.sendRedirect("loans.jsp");
        } else {
        	response.sendRedirect("loans.jsp?status=error");
        }
        
        
        
    }
}