package martin.controllers;

import java.util.List;
import martin.models.entities.Account;
import martin.models.entities.Book;
import martin.models.entities.User;
import martin.models.managers.AccountManager;
import martin.models.managers.BookManager;
import martin.models.managers.UserManager;
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

	@Autowired
	private BookManager bookManager;

	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/accounts")
	public String accounts(Model model) {
		List<Account> accounts = accountManager.findAll();

		model.addAttribute("accounts", accounts);
		return "admin/accounts";
	}

	@RequestMapping(value = "/books")
	public String books(Model model) {
		List<Book> books = bookManager.findAll();

		model.addAttribute("books", books);
		return "admin/books";
	}
	
	@RequestMapping(value = "/users")
	public String index(Model model) {
		List<User> users = userManager.findAll();

		model.addAttribute("users", users);
		return "admin/users";
	}
	
}
