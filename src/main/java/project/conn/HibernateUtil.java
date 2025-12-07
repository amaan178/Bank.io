package project.conn;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

import project.model.Customer;
import project.model.Transactions;
import project.model.Account;
import project.model.Loan;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		
		try {
			Properties properties = new Properties();
			Configuration configuration = new Configuration();
			properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost/project");
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "root");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            
            configuration.setProperties(properties);
            
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Transactions.class);
            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(Loan.class);
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return sessionFactory;
	}

}
