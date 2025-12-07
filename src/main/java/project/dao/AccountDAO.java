package project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import project.conn.HibernateUtil;
import project.model.Account;

public class AccountDAO {

    public String saveAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(account);
            transaction.commit();
            return "Data Saved";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Failed to Save Data";
        }
    }

    public List<Account> getCustAccount(int custId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Account where customerId = :custId", Account.class)
                          .setParameter("custId", custId)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAccountNumberById(int accountId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Account account = session.get(Account.class, accountId);
            return account != null ? account.getAccountNumber() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteAccount(int accountId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, accountId);
            if (account != null) {
                session.delete(account);
                transaction.commit();
                return "Account Deleted Successfully";
            } else {
                return "Account Not Found";
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Failed to Delete Account";
        }
    }
    public boolean updateAccountBalance(int accountId, double newBalance) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, accountId);
            if (account != null) {
                account.setAccountBalance(newBalance);
                session.update(account);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccountById(int accountId) {
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            account = session.get(Account.class, accountId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public double getTotalBalance(int customerId) {
        double totalBalance = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT SUM(accountBalance) FROM Account WHERE customerId = :customerId";
            totalBalance = (Double) session.createQuery(hql)
                                           .setParameter("customerId", customerId)
                                           .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalBalance;
    }

    public long getTotalAccounts(int customerId) {
        long totalAccounts = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT COUNT(*) FROM Account WHERE customerId = :customerId";
            totalAccounts = (Long) session.createQuery(hql)
                                          .setParameter("customerId", customerId)
                                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalAccounts;
    }
    
    public Account getLoanAccountBalance2(int accountId) {
        Account account = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Account> query = session.createQuery("FROM Account WHERE accountId = :accountId", Account.class);
            query.setParameter("accountId", accountId);

            // Fetch the result
            account = query.uniqueResult();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }
    
    public String setLoanAccountBalance(int accountId, double newBalance) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.getTransaction();
			transaction = session.beginTransaction();
			Account account = (Account) session.get(Account.class, accountId);
			account.setAccountBalance(newBalance);
			session.merge(account);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Data Updated";
	}
}
