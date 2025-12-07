package project.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import project.conn.HibernateUtil;
import project.model.Customer;

import java.util.regex.Pattern;
import org.hibernate.query.Query;

@WebServlet("/RegisterCustomer")
public class RegisterCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html";
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        String custName = request.getParameter("customerName");
        String custEmail = request.getParameter("customerEmail");
        String custPass = request.getParameter("customerPassword");

        // Validate input fields
        if (custName == null || custName.isEmpty() || 
            custEmail == null || custEmail.isEmpty() || 
            custPass == null || custPass.isEmpty()) {
            out.println("Error: All fields are required.");
            return;
        }
        
        // Validate email format
        if (!EMAIL_PATTERN.matcher(custEmail).matches()) {
            out.println("Error: Invalid email format.");
            return;
        }
        
        // Validate password strength
        if (custPass.length() < 8) {
            out.println("Error: Password must be at least 8 characters long.");
            return;
        }

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Create a Customer object and save it to the database
            Customer customer = new Customer(custName, custEmail, custPass);
            session.save(customer);

            transaction.commit();
            response.sendRedirect("login.jsp");
        } catch (Exception ex) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            out.println("Error: " + ex.getMessage());
        } finally {
            out.close();
        }
    }
}
