package martin.models.dao;

import java.util.List;
import martin.models.entities.Account;

/**
 *
 * @author martin
 */
public interface AccountDao {
	
	public List<Account> findAll();

	public void delete(Long id);

	public void saveOrUpdate(Account account);

	public Account findById(Long id);

	public Account findByIdWithUser(Long id);
}
