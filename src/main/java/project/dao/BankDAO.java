package project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import project.conn.HibernateUtil;

import project.model.Customer;
import project.model.Transactions;
import project.model.Account;
import project.model.Loan;

public class BankDAO {
	public String saveCustomer(Customer customer) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.getTransaction();
            transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Data Saved";
    }
	
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
	
	public String saveAccount(Account account) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.getTransaction();
            transaction = session.beginTransaction();
            session.persist(account);
            transaction.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Data Saved";
    }
	
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
}
