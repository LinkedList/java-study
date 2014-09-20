package martin.models.commandobjects;

import javax.validation.Valid;
import martin.models.entities.User;

/**
 *
 * @author martin
 */
public class UserCommandObject {

	@Valid
	private User user;

	public UserCommandObject() {
	}

	/**
	 * Get the value of user
	 *
	 * @return the value of user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Set the value of user
	 *
	 * @param user new value of user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}
