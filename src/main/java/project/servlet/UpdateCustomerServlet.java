package project.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.CustomerDAO;
import project.model.Customer;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the customer ID from the session
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve form parameters
        String customerIncome = request.getParameter("customerIncome");
        String customerAgeStr = request.getParameter("customerAge");

        int customerAge = 0;
        try {
            customerAge = Integer.parseInt(customerAgeStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("profile.jsp?error=Invalid age format");
            return;
        }
        customer.setCustomerIncomce(Double.parseDouble(customerIncome));
        customer.setCustomerAge(customerAge);
        
        CustomerDAO customerDAO = new CustomerDAO();
        boolean updateSuccess = customerDAO.updateCustomer(customer);

        if (updateSuccess) {
            session.setAttribute("customer", customer);
            response.sendRedirect("profile.jsp?success=Profile updated successfully");
        } else {
            response.sendRedirect("profile.jsp?error=Failed to update profile");
        }
    }
}
