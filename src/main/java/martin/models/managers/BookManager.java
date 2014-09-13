package martin.models.managers;

import java.util.List;
import martin.models.entities.Book;

/**
 *
 * @author martin
 */
public interface BookManager {

	public List<Book> findAll();

	public void delete(Long id);

	public void saveOrUpdate(Book book);

	public Book findById(Long id);

	public Book findByIdWithUser(Long id);
}
