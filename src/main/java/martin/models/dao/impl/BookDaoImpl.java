package martin.models.dao.impl;

import java.util.List;
import martin.models.dao.BookDao;
import martin.models.entities.Book;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("bookDao")
public class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessFactory;

	@Override
	public List<Book> findAll() {
		final Session session = sessFactory.openSession();

		try {

			Query booksQuery = session.createQuery("from Book book order by book.id");
			List<Book> books = (List<Book>) booksQuery.list();

			return books;
		} finally {
			session.close();
		}
	}

	@Override
	public Book findById(Long id) {
		final Session session = sessFactory.openSession();
		Book book = null;

		try {
			book = (Book) session.get(Book.class, id);

			return book;
		} finally {
			session.close();
		}
	}

	@Override
	public void saveOrUpdate(Book book) {
		final Session session = sessFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();

			try {

				session.saveOrUpdate(book);
				transaction.commit();

			} catch (Exception e) {
				e.printStackTrace();
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Long id) {
		final Session session = sessFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();

			try {
				Book book = (Book) session.get(Book.class, id);

				session.delete(book);
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
				transaction.rollback();
			}

		} finally {
			session.close();
		}
	}

	@Override
	public Book findByIdWithUser(Long id) {
		final Session session = sessFactory.openSession();
		Book book = null;

		try {
			book = (Book) session.get(Book.class, id);

			Hibernate.initialize(book.getUser());

			return book;
		} finally {
			session.close();
		}
	}
	
}
