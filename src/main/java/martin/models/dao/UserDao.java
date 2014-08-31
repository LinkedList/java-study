package martin.models.dao;

import martin.models.entities.User;
import java.util.List;

public interface UserDao {

	 public List<User> findAll();
	 
	 public User findById(Long id);
	 
	 public void saveOrUpdate(User user);
	 
	 public void delete(Long id);

}
