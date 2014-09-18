package martin.models.managers.impl;

import java.util.List;
import martin.models.dao.AccountDao;
import martin.models.entities.Account;
import martin.models.managers.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service("accountManager")
public class AccountManagerImpl implements AccountManager {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}

	@Override
	public void delete(Long id) {
		accountDao.delete(id);
	}

	@Override
	public void saveOrUpdate(Account account) {
		accountDao.saveOrUpdate(account);
	}

	@Override
	public Account findById(Long id) {
		return accountDao.findById(id);
	}

	@Override
	public Account findByIdWithUser(Long id) {
		return accountDao.findByIdWithUser(id);
	}
	
}
