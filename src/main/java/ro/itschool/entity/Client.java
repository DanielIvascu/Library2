package ro.itschool.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String lastName;
	private String firstName;
	private String borrowedDate;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Book> books1;
	
	
	
	public Client(String lastName, String firstName, String borrowedDate) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.borrowedDate = borrowedDate;
	}

}
