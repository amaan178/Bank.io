package project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import project.model.*;

@WebServlet("/EarningsServlet")
public class EarningsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp?error=You need to login first.");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        double totalEarningsAnnual = customer.getCustomerIncomce();
        double totalEarningsMonthly = totalEarningsAnnual / 12.0;

        double[] monthlyEarnings = new double[12];
        for (int i = 0; i < 12; i++) {
            monthlyEarnings[i] = totalEarningsMonthly;
        }
        
        for (int i = 0; i < 12; i++) {
        	System.out.println(monthlyEarnings[i]);
        }

        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(monthlyEarnings));
    }
}
