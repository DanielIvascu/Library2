package ro.itschool.main;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ro.itschool.dao.BookDao;
import ro.itschool.entity.Author;
import ro.itschool.entity.Book;
import ro.itschool.entity.Client;
import ro.itschool.entity.Publishing;
import ro.itschool.service.BookService;
import ro.itschool.util.CsvLocations;
import ro.itschool.util.HibernateUtil;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws IllegalStateException, FileNotFoundException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> books = CsvLocations.getAllBooks();
		List<Author> authors = CsvLocations.getAllAuthors();
		List<Client> clients = CsvLocations.getAllClients();
		List<Publishing> publishings = CsvLocations.getAllPublishings();

		primaryStage.setTitle("Library2");
		GridPane gridPane = new GridPane();
		gridPane.setMinSize(300, 300);
		Button button1 = new Button("List of Authors");
		button1.setOnAction(x -> {
			try {
				popUpStageListOfAuthors(primaryStage, books, authors);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});

		// BUTTON AddBookToAuthor
		ListView<Author> listView = new ListView<Author>();
		
		Label addBookLabel = new Label("New Book:");
		TextArea addBookTextField = new TextArea();
		addBookTextField.setMaxWidth(300);
		addBookTextField.setMaxHeight(100);
		Button addBookButton = new Button("Add Book");
		addBookButton.setOnAction(e -> {
			addBookToAuthor(addBookTextField.getText());
			primaryStage.close();
			try {
				popUpStageListOfAuthors(primaryStage, books, authors);
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});

		gridPane.add(button1, 0, 0);
		gridPane.add(addBookButton, 0, 1);
		gridPane.add(addBookLabel, 0, 2);
		gridPane.add(addBookTextField, 0, 3);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		Scene scene = new Scene(gridPane);
		primaryStage.setScene(scene);
		primaryStage.show();
		session.close();
	}

	private void addBookToAuthor(String title) {
		Book book = new Book();
		book.setTitle(title);
		book.setIdAuthor(5);
		book.setISBN(4389);
		book.addBookToAuthor(book);
		BookService ps = new BookService();
		ps.saveBook(book);
	}

	protected void popUpStageListOfAuthors(Stage primaryStage, List<Book> books, List<Author> authors)
			throws IllegalStateException, FileNotFoundException {
		Stage stage = new Stage();
		GridPane grid = new GridPane();
		grid.setMinSize(500, 500);
		grid.setPadding(new Insets(10, 10, 10, 10));
		ListView<Author> listView = new ListView<Author>();
		listView.setMinSize(450, 450);
		listView.setPrefHeight(authors.size() * 24 + 2);
		listView.setMaxSize(560, 560);
		for (Author auth : authors) {
			listView.getItems().add(auth);
		}
		listView.setOnMouseClicked(me -> {
			bookStage(listView.getSelectionModel().getSelectedItem(), primaryStage, books);
		});
		grid.add(listView, 0, 0);
		Scene scene = new Scene(grid);
		stage.setTitle("List of Authors");
		stage.setScene(scene);
		stage.show();
	}

	public void bookStage(Author author, Stage stage, List<Book> books) {
		String str = "";
		for (Book book : books) {
			if (book.getIdAuthor() == author.getId())
				str = str.concat(book.getTitle() + "\n");
		}
		Text usernameText = new Text(str);
		GridPane gridPane = new GridPane();
		gridPane.setMinSize(200, 200);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(usernameText, 0, 0);
		Scene scene = new Scene(gridPane);
		stage.setTitle("Books");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {

		// RELATION ONE TO MANY -> CLIENT_BOOK

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session1.beginTransaction();
		Book book1 = new Book(5, " The Count of Monte Cristo", 12254, null);
		Book book2 = new Book(5, " The Three Musketeers", 2105, null);
		Book book3 = new Book(5, "Twenty Years After", 12233, null);
		Client client = new Client("Ivascu", "Daniel", "2017-01-20");
		Set<Book> books1 = new HashSet<>();
		books1.add(book1);
		books1.add(book2);
		books1.add(book3);
		client.setBooks1(books1);

		session1.save(client);
		transaction.commit();
		session1.close();

		launch(args);

	}
}
