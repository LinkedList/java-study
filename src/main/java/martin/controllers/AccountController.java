package martin.controllers;

import javax.validation.Valid;
import martin.exceptions.AccountNotFoundException;
import martin.exceptions.UserNotFoundException;
import martin.models.commandobjects.AccountCommandObject;
import martin.models.entities.Account;
import martin.models.entities.User;
import martin.models.managers.AccountManager;
import martin.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping(value = "/account/")
@SessionAttributes({"command"})
public class AccountController {

	@Autowired
	private AccountManager accountManager;

	@Autowired
	private UserManager userManager;

	@ModelAttribute("command")
	public AccountCommandObject createCommand() {
		return new AccountCommandObject();
	}
	
	@RequestMapping(value="/createOrEdit", method=RequestMethod.GET)
	public String accountCreateOrEdit(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "userId", required = false) Long userId,
			Model model) throws AccountNotFoundException, UserNotFoundException {

		Account account = null;
		if(id != null) {
			account = accountManager.findByIdWithUser(id);
			if(account == null) {
				throw new AccountNotFoundException();
			}
		} else if(id == null && userId == null){
			throw new UserNotFoundException();
		} else {
			account = new Account();
			User user = userManager.findById(userId);
			if(user == null) {
				throw new UserNotFoundException();
			}
			account.setUser(user);
		}

		AccountCommandObject command = this.createCommand();
		command.setAccount(account);

		model.addAttribute("command", command);
		return "account/accountCreateOrEdit";
	}
	
	@RequestMapping(value="/createOrEdit", method=RequestMethod.POST)
	public String accountCreateOrEditPost(
		@ModelAttribute("command") @Valid AccountCommandObject command,
		BindingResult result,
		Model model) {

		if(result.hasErrors()) {
			return "account/accountCreateOrEdit";
		}

		accountManager.saveOrUpdate(command.getAccount());

		return "redirect:/users/" + command.getAccount().getUser().getId();
	}

	@ExceptionHandler({UserNotFoundException.class})
	public String userNotFoundExceptionHandler() {
		return "exceptions/userNotFound";
	}

	@ExceptionHandler({AccountNotFoundException.class})
	public String accountNotFoundExceptionHandler() {
		return "exceptions/accountNotFound";
	}
}
