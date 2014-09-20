package martin.models.commandobjects;

import javax.validation.Valid;
import martin.models.entities.Account;

/**
 *
 * @author martin
 */
public class AccountCommandObject {

	@Valid
	private Account account;

	/**
	 * Get the value of account
	 *
	 * @return the value of account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Set the value of account
	 *
	 * @param account new value of account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}


}
