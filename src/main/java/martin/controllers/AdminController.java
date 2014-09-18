package martin.controllers;

import java.util.List;
import martin.models.entities.Account;
import martin.models.managers.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

	@Autowired
	private AccountManager accountManager;

	@RequestMapping(value="/accounts")
	public String accounts(Model model) {
		List<Account> accounts = accountManager.findAll();

		model.addAttribute("accounts", accounts);
		return "admin/accounts";
	}
	
}
