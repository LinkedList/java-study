package martin.controllers;

import javax.validation.Valid;
import martin.models.entities.Account;
import martin.models.entities.User;
import martin.models.managers.AccountManager;
import martin.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping(value = "/account/")
public class AccountController {

	@Autowired
	private AccountManager accountManager;

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value="/create/{userId}", method=RequestMethod.GET)
	public String accountCreate(@PathVariable("userId") Long userId, Model model) {
		Account account = new Account();

		model.addAttribute("account", account);
		model.addAttribute("userId", userId);
		return "account/accountCreate";
	}
	
	@RequestMapping(value="/create/{userId}", method=RequestMethod.POST)
	public String accountCreatePost(@PathVariable("userId") Long userId, @ModelAttribute @Valid Account account, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "account/accountCreate";
		}

		User user = userManager.findById(userId);
		account.setUser(user);

		accountManager.saveOrUpdate(account);

		return "redirect:/users/user/" + userId;
	}
}
