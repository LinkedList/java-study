package martin.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author martin
 */
@Entity
@Table(name="books")
public class Book {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="title")
	@NotEmpty
	private String title;
	
	@Column(name="description")
	private String description;

	public Book() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", title=" + title + ", description=" + description + '}';
	}
}