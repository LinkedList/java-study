package martin.models.seeders;

import martin.models.entities.Book;
import martin.models.managers.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author martin
 */
@Component
public class BooksSeeder {
	
	@Autowired
	private BookManager bookManager;

	public void seed() {
		Book book = new Book();
		book.setTitle("Kniha");
		book.setDescription("Popis knihy");
		bookManager.saveOrUpdate(book);

		book = new Book();
		book.setTitle("Jina kniha");
		book.setDescription("Popis jine knihy");
		bookManager.saveOrUpdate(book);
	}
}
