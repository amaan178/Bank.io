package project.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import project.conn.HibernateUtil;
import project.model.Customer;
import project.model.Loan;

import project.dao.LoanDAO;

@WebServlet("/AddLoan")
public class AddLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html";
       
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        DecimalFormat df = new DecimalFormat("#.00");

        String accountIdStr = request.getParameter("accountId");
        String loanType = request.getParameter("loanType");
        String loanAmountStr = request.getParameter("loanAmount");
        String loanDurationStr = request.getParameter("loanDuration");
        
        int accountId = 0;
        if (accountIdStr != null && !accountIdStr.isEmpty()) {
            accountId = Integer.parseInt(accountIdStr);
        }
        
        int loanDuration = 0;
        if (loanDurationStr != null && !loanDurationStr.isEmpty()) {
        	loanDuration = Integer.parseInt(loanDurationStr);
        }
        
        double loanAmount = 0;
        if (loanAmountStr != null && !loanAmountStr.isEmpty()) {
        	loanAmount = Double.parseDouble(loanAmountStr);
        }
        
        String loanStatus = "Open";
        
        int loanTotalEmi = loanDuration*12;
        int loanPaidEmi = 0;
        int loanRemainEmi = loanTotalEmi;
        
		String loanEmiAmountStr = df.format(loanAmount/(loanTotalEmi));
		double loanEmiAmount = Double.parseDouble(loanEmiAmountStr); // amount
		
		Date loanStartDate = new Date(); //startDate
		
		Date loanEndDate = new Date();
		loanEndDate.setYear(loanStartDate.getYear()+loanDuration); //endDate
		
		Date loanNextEmiDate = new Date();
		loanNextEmiDate.setMonth(loanStartDate.getMonth()+1);
        
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
        
        LoanDAO loanDao = new LoanDAO();
        Loan loan = new Loan(loanAmount, loanEmiAmount, loanType, loanStatus, loanDuration, loanStartDate, loanEndDate, loanTotalEmi, loanPaidEmi, loanRemainEmi, loanNextEmiDate, customerId, accountId);
        
        loanDao.saveLoan(loan);
        
        response.sendRedirect("loans.jsp");
        
    }
}