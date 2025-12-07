package project.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import project.conn.HibernateUtil;
import project.model.Customer;

public class CustomerDAO {
    
    public String saveCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
            return "Data Saved";
        } catch (Exception e) {
            e.printStackTrace();
            return "Data Save Failed";
        }
    }

    public Customer getCustomerByEmailAndPassword(String email, String password) {
        Customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Customer WHERE customerEmail = :email AND customerPass = :password";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            customer = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
    
    public boolean updateCustomer(Customer customer) {
        boolean success = false;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return success;
    }
}
