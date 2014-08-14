package martin.models;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessFactory;

	public SessionFactory getSessFactory() {
		return sessFactory;
	}

	public void setSessFactory(SessionFactory sessFactory) {
		this.sessFactory = sessFactory;
	}

	@Override
	public List<User> findAll() {
		final Session session = sessFactory.openSession();

		try {

			Query usersQuery = session.createQuery("from User user");
			List<User> users = (List<User>) usersQuery.list();

			return users;
		} finally {
			session.close();
		}
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) {
		final Session session = sessFactory.openSession();
		try {
			final Transaction transaction = session.beginTransaction();

			try {

				session.save(user);
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
		// TODO Auto-generated method stub

	}

}
