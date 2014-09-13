package martin.models.dao;

import java.util.List;
import martin.models.entities.Book;

/**
 *
 * @author martin
 */
public interface BookDao {

	public List<Book> findAll();

	public Book findById(Long id);

	public Book findByIdWithUser(Long id);

	public void saveOrUpdate(Book book);

	public void delete(Long id);

}
