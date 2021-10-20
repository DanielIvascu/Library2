package ro.itschool.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.itschool.entity.Book;
import ro.itschool.util.HibernateUtil;


public class BookDao {

	private static Session session;

	private static Transaction transaction;

	public Session openCurrentSession() {
		session = HibernateUtil.getSessionFactory().openSession();
		return session;
	}

	public static Session openCurrentSessionwithTransaction() {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		return session;
	}

	public void closeCurrentSession() {
		session.close();
	}

	public static void closeCurrentSessionwithTransaction() throws HibernateException {
		try {
			transaction.commit();
			session.close();
		} catch (Exception e) {
			session.close();
			throw new HibernateException("transaction was not commited");
		}
	}
	

	// Save Book
	public static void saveBook(Book newBook) {
		session.save(newBook);
	}
	
	
}
