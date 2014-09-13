package martin.models.managers.impl;

import java.util.List;
import martin.models.dao.BookDao;
import martin.models.entities.Book;
import martin.models.managers.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("bookManager")
public class BookManagerImpl implements BookManager {

	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public void delete(Long id) {
		bookDao.delete(id);
	}

	@Override
	public void saveOrUpdate(Book book) {
		bookDao.saveOrUpdate(book);
	}

	@Override
	public Book findById(Long id) {
		return bookDao.findById(id);
	}

	@Override
	public Book findByIdWithUser(Long id) {
		return bookDao.findByIdWithUser(id);
	}
	
}
