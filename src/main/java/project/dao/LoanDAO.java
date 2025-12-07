package project.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import project.conn.HibernateUtil;
import project.model.Loan;

public class LoanDAO {
	public String saveLoan(Loan loan) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.getTransaction();
            transaction = session.beginTransaction();
            session.persist(loan);
            transaction.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Data Saved";
    }
	
	public List<Loan> getCustLoan(int custId) {
		List<Loan> loans = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			loans = session.createQuery("from Loan where customerId=" + custId, Loan.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loans;
	}
	
	public String updateLoan(int loanId, int newLoanPaidEmi, int newLoanRemainEmi, Date newLoanLastEmiDate, Date newLoanNextEmiDate, String loanStatus) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.getTransaction();
			transaction = session.beginTransaction();
			Loan loan = (Loan) session.get(Loan.class, loanId);
			loan.setLoanPaidEmi(newLoanPaidEmi);
			loan.setLoanRemainEmi(newLoanRemainEmi);
			loan.setLoanLastEmiDate(newLoanLastEmiDate);
			loan.setLoanNextEmiDate(newLoanNextEmiDate);
			loan.setLoanStatus(loanStatus);
			session.merge(loan);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Data Updated";
	}
	
	public long getTotalLoans(int customerId) {
        long totalLoans = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT COUNT(*) FROM Loan WHERE customerId = :customerId AND loanStatus = :loanStatus";
            totalLoans = (Long) session.createQuery(hql)
                                          .setParameter("customerId", customerId)
                                          .setParameter("loanStatus", "Open")
                                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalLoans;
    }
	
	public void deleteLoans(int accountId) {
	    Session session = null;
	    Transaction tx = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();
	        
	        String hql = "DELETE FROM Loan WHERE accountId = :accountId";
	        int result = session.createQuery(hql)
	                            .setParameter("accountId", accountId)
	                            .executeUpdate();
	        
	        tx.commit();
	        System.out.println("Number of records deleted: " + result);
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
}