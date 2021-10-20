package ro.itschool.service;


import org.hibernate.HibernateException;

import lombok.extern.log4j.Log4j;
import ro.itschool.dao.BookDao;
import ro.itschool.entity.Book;

@Log4j
public class BookService {

	// Save Book
	public void saveBook(Book book) {
		BookDao.openCurrentSessionwithTransaction();
		BookDao.saveBook(book);
		try {
			BookDao.closeCurrentSessionwithTransaction();
		} catch (HibernateException e) {
			log.error("Save Book cannot be executed");
		}
	}
}
