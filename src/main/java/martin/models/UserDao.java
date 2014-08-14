package martin.models;

import java.util.List;

public interface UserDao {

	 public List<User> findAll();
	 
	 public User findById(Long id);
	 
	 public void save(User user);
	 
	 public void delete(Long id);

}
