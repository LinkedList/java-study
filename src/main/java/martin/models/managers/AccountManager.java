package martin.models.managers;

import java.util.List;
import martin.models.entities.Account;

/**
 *
 * @author martin
 */
public interface AccountManager {

	public List<Account> findAll();

	public void delete(Long id);

	public void saveOrUpdate(Account account);

	public Account findById(Long id);

	public Account findByIdWithUser(Long id);
}
