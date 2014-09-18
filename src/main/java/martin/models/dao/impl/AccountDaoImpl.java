package martin.models.dao.impl;

import java.util.List;
import martin.models.dao.AccountDao;
import martin.models.entities.Account;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessFactory;

	@Override
	public List<Account> findAll() {
		final Session session = sessFactory.openSession();

		try {
			Query accountsQuery = session.createQuery("from Account account order by account.id");
			List<Account> accounts = (List<Account>) accountsQuery.list();

			return accounts;
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
				Account account = (Account) session.get(Account.class, id);

				session.delete(account);
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
	public void saveOrUpdate(Account account) {
		final Session session = sessFactory.openSession();

		try {
			final Transaction transaction = session.beginTransaction();

			try {
				session.saveOrUpdate(account);
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
	public Account findById(Long id) {
		final Session session = sessFactory.openSession();

		Account account = null;

		try {
			account = (Account) session.get(Account.class, id);

			return account;
		} finally {
			session.close();
		}
	}

	@Override
	public Account findByIdWithUser(Long id) {
		final Session session = sessFactory.openSession();

		Account account = null;

		try {
			account = (Account) session.get(Account.class, id);

			Hibernate.initialize(account.getUser());

			return account;
		} finally {
			session.close();
		}
	}
	
}
