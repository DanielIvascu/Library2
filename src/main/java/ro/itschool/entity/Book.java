package ro.itschool.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public int idAuthor;
	public String title;
	public long ISBN;

	@ManyToMany
	private Set<Author> authors;

	public Book(int idAuthor, String title, long iSBN, Set<Author> authors) {
		super();
		this.idAuthor = idAuthor;
		this.title = title;
		ISBN = iSBN;
		this.authors = authors;
	}

	public void addBookToAuthor(Book book) {

	}
}
