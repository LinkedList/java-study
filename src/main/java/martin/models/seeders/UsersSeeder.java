package martin.models.seeders;

import martin.models.entities.User;
import martin.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author martin
 */
@Component
public class UsersSeeder {

	@Autowired
	private UserManager userManager;

	public void seed() {
		User user = new User();
		user.setLogin("martin");
		user.setEmail("martin@email.cz");
		userManager.saveOrUpdate(user);

		user = new User();
		user.setLogin("anna");
		user.setEmail("anna@email.com");
		userManager.saveOrUpdate(user);

		user = new User();
		user.setLogin("honza");
		user.setEmail("honza@email.com");
		userManager.saveOrUpdate(user);

		user = new User();
		user.setLogin("marek");
		user.setEmail("marek@email.com");
		userManager.saveOrUpdate(user);

		user = new User();
		user.setLogin("ladislav");
		user.setEmail("ladislav@email.com");
		userManager.saveOrUpdate(user);

		user = new User();
		user.setLogin("tomas");
		user.setEmail("tomas@email.com");
		userManager.saveOrUpdate(user);
	}

}
