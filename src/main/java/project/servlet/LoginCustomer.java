package project.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import project.dao.CustomerDAO;
import project.model.Customer;

@WebServlet("/LoginCustomer")
public class LoginCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html";
    CustomerDAO customerDao = new CustomerDAO();

    public void init() throws ServletException {
        // Initialization logic if needed
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

        String custEmail = request.getParameter("customerEmail");
        String custPass = request.getParameter("customerPassword");

        try {
            // Basic input validation
            if (custEmail == null || custEmail.isEmpty() || custPass == null || custPass.isEmpty()) {
                response.sendRedirect("login.jsp?error=Email and Password are required.");
                return;
            }

            Customer customer = customerDao.getCustomerByEmailAndPassword(custEmail, custPass);

            if (customer != null) {
                // Login successful, store customer in session
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                session.setAttribute("customerId", customer.getCustomerId());
                response.sendRedirect("index.jsp");
            } else {
                // Login failed, redirect back to login page with an error message
                response.sendRedirect("login.jsp?error=Invalid email or password.");
            }
        } catch (Exception ex) {
            response.sendRedirect("login.jsp?error=" + ex.getMessage());
        }
    }
}
