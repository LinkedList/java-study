package martin.models.commandobjects;

import martin.models.entities.Book;
import martin.models.entities.User;

/**
 *
 * @author martin
 */
public class UserFavouritesCommandObject {

	User user;

	Book book;

	public UserFavouritesCommandObject() {
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
