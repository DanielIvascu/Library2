package ro.itschool.entity;

import java.util.Set;
import javax.persistence.CascadeType;
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
@ToString(exclude = "books")
@NoArgsConstructor
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String lastName;
	private String firstName;
	private String Country;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Book> books;
	
	

	public Author(String lastName, String firstName, String country) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		Country = country;
	}




}
