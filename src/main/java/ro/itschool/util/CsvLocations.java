package ro.itschool.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import org.hibernate.Session;
import com.opencsv.bean.CsvToBeanBuilder;
import ro.itschool.entity.Author;
import ro.itschool.entity.Book;
import ro.itschool.entity.Client;
import ro.itschool.entity.Publishing;

public class CsvLocations {

	public final static String CSV_LOCATION_Author = "C:\\Users\\Alina Ivascu\\Downloads\\Library\\author.csv";
	public final static String CSV_LOCATION_Book = "C:\\Users\\Alina Ivascu\\Downloads\\Library\\book.csv";
	public final static String CSV_LOCATION_Publishing = "C:\\Users\\Alina Ivascu\\Downloads\\Library\\publishing.csv";
	public final static String CSV_LOCATION_Client = "C:\\Users\\Alina Ivascu\\Downloads\\Library\\client.csv";

	public static List<Author> getAllAuthors() throws IllegalStateException, FileNotFoundException {
		List<Author> authors = new CsvToBeanBuilder<Author>(new FileReader(CSV_LOCATION_Author)).withType(Author.class)
				.build().parse();
		authors.forEach(System.out::println);
		Session session = HibernateUtil.getSessionFactory().openSession();
		for (Author author2 : authors) {
			session.save(author2);
		}
		session.close();
		return authors;
	}

	public static List<Book> getAllBooks() throws IllegalStateException, FileNotFoundException {
		List<Book> book = new CsvToBeanBuilder<Book>(new FileReader(CSV_LOCATION_Book)).withType(Book.class).build()
				.parse();
		book.forEach(System.out::println);
		Session session = HibernateUtil.getSessionFactory().openSession();
		for (Book book2 : book) {
			session.save(book2);
		}
		session.close();
		return book;
	}

	public static List<Publishing> getAllPublishings() throws IllegalStateException, FileNotFoundException {
		List<Publishing> publishing = new CsvToBeanBuilder<Publishing>(new FileReader(CSV_LOCATION_Publishing))
				.withType(Publishing.class).build().parse();
		publishing.forEach(System.out::println);
		Session session = HibernateUtil.getSessionFactory().openSession();
		for (Publishing publishing2 : publishing) {
			session.save(publishing2);
		}
		session.close();
		return publishing;
	}

	public static List<Client> getAllClients() throws IllegalStateException, FileNotFoundException {
		List<Client> client = new CsvToBeanBuilder<Client>(new FileReader(CSV_LOCATION_Client)).withType(Client.class)
				.build().parse();
		client.forEach(System.out::println);
		Session session = HibernateUtil.getSessionFactory().openSession();
		for (Client client2 : client) {
			session.save(client2);
		}
		session.close();
		return client;
	}
}