package martin.models.commandobjects;

import javax.validation.Valid;
import martin.models.entities.Book;

/**
 *
 * @author martin
 */
public class BookCommandObject {

	@Valid
	private Book book;

	public BookCommandObject() {}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
