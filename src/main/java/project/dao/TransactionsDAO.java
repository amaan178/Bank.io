package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import project.conn.HibernateUtil;
import project.model.Account;
import project.model.Transactions;

public class TransactionsDAO {
	public String saveTransactions(Transactions transactions) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.getTransaction();
            transaction = session.beginTransaction();
            session.persist(transactions);
            transaction.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Data Saved";
    }
	public List<Transactions> getCustTransaction(int custId) {
		List<Transactions> transactions = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transactions = session.createQuery("FROM Transactions WHERE customerId=" + custId, Transactions.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactions;
	}
	public Double getTotalAmount(int customerId, String type) {
		Double totalAmount = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT SUM(t.transactionAmount) FROM Transactions t " +
                         "WHERE t.customerId = :customerId AND t.transactionType = :transactionType";
            totalAmount = (Double) session.createQuery(hql)
                                          .setParameter("customerId", customerId)
                                          .setParameter("transactionType", type)
                                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return totalAmount != null ? totalAmount : 0.0;
    }
	
	public void deleteTransactions(int accountId) {
	    Session session = null;
	    Transaction tx = null;
	    try {
	        // Open a new session
	        session = HibernateUtil.getSessionFactory().openSession();
	        
	        // Begin a new transaction
	        tx = session.beginTransaction();
	        
	        // Define the HQL delete query
	        String hql = "DELETE FROM Transactions WHERE accountId = :accountId";
	        
	        // Execute the query
	        int result = session.createQuery(hql)
	                            .setParameter("accountId", accountId)
	                            .executeUpdate();
	        
	        // Commit the transaction
	        tx.commit();
	        
	        // Output the number of rows deleted
	        System.out.println("Number of transactions deleted: " + result);
	    } catch (Exception e) {
	        // Rollback transaction in case of an error
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        // Close the session
	        if (session != null) {
	            session.close();
	        }
	    }
	}
}