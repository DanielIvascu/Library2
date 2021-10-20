package ro.itschool.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Publishing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String publishingName;
	private String email;

	public Publishing(String publishingName, String email) {
		super();
		this.publishingName = publishingName;
		this.email = email;
	}

}
