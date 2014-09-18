package martin.models.dao.impl;

import java.util.List;
import martin.models.dao.UserDao;
import martin.models.entities.User;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

        @Autowired
	private SessionFactory sessFactory;

	@Override
	public List<User> findAll() {
		final Session session = sessFactory.openSession();

		try {

			Query usersQuery = session.createQuery("from User user order by user.id");
			List<User> users = (List<User>) usersQuery.list();

			return users;
		} finally {
			session.close();
		}
	}

	@Override
	public User findById(Long id) {
		final Session session = sessFactory.openSession();
		User user = null;

		try {
			user = (User) session.get(User.class, id);

			return user;
		} finally {
			session.close();
		}
	}

	@Override
	public void saveOrUpdate(User user) {
		final Session session = sessFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();

			try {

				session.saveOrUpdate(user);
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

				User user = (User) session.get(User.class, id);
				
				session.delete(user);
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
	public User findByIdWithDetails(Long id) {
		final Session session = sessFactory.openSession();
		User user = null;

		try {
			user = (User) session.get(User.class, id);
			Hibernate.initialize(user.getBooks());
			Hibernate.initialize(user.getAccounts());

			return user;
		} finally {
			session.close();
		}
	}

}
