package martin.models.managers;

import java.util.List;
import martin.models.entities.User;

/**
 *
 * @author martin
 */
public interface UserManager {

	public List<User> findAll();
	public void delete(Long id);
	public void saveOrUpdate(User user);
	public User findById(Long id);
}
