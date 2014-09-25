package martin.controllers;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import martin.exceptions.UserNotFoundException;
import javax.validation.Valid;
import martin.models.commandobjects.UserCommandObject;
import martin.models.commandobjects.UserFavouritesCommandObject;
import martin.models.entities.Book;
import martin.models.entities.User;
import martin.models.managers.BookManager;
import martin.models.managers.UserManager;
import martin.models.seeders.UsersSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/users/")
@SessionAttributes("command")
public class UsersController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private UsersSeeder usersSeeder;

	@Autowired
	private BookManager bookManager;

	@ModelAttribute("command")
	public UserCommandObject createCommand() {
		return new UserCommandObject();
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Book.class, "book", new PropertyEditorSupport(){
			@Override
			public void setAsText(String id) {
				if(id.equals("-")) {
					setValue(null);
				} else {
					Book book = bookManager.findById(Long.parseLong(id));
					setValue(book);
				}
			}
		});
	}

	@RequestMapping(value = "/seeder")
	public String seeder() {

		usersSeeder.seed();

		return "redirect:/admin/users/";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long id) {

		userManager.delete(id);

		return "redirect:/admin/users/";
	}

	@RequestMapping(value = "/{id}")
	public String user(@PathVariable("id") Long id, Model model) throws UserNotFoundException {

		User user = userManager.findByIdWithDetails(id);

		if (user == null) {
			throw new UserNotFoundException();
		}
		model.addAttribute("user", user);
		return "users/user";
	}

	@RequestMapping(value = "/createOrEdit", method = RequestMethod.GET)
	public String userCreateOrEdit(@RequestParam(value = "id", required = false) Long id, Model model) throws UserNotFoundException {
		User user = null;
		if (id == null) {
			user = new User();
		} else {
			user = userManager.findById(id);
			if (user == null) {
				throw new UserNotFoundException();
			}
		}

		UserCommandObject command = this.createCommand();
		command.setUser(user);

		model.addAttribute("command", command);
		return "users/userCreateOrEdit";
	}

	@RequestMapping(value = "/createOrEdit", method = RequestMethod.POST)
	public String userCreateOrEditPost(@ModelAttribute("command") @Valid UserCommandObject command, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "users/userCreateOrEdit";
		}

		userManager.saveOrUpdate(command.getUser());

		return "redirect:/users/" + command.getUser().getId();
	}

	@RequestMapping(value = "/editFavourites/{id}", method = RequestMethod.GET)
	public String userFavourites(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
		User user = userManager.findByIdWithDetails(id);
		if (user == null) {
			throw new UserNotFoundException();
		}

		UserFavouritesCommandObject command = new UserFavouritesCommandObject();
		command.setUser(user);
		Map<Long, String> map = new HashMap<>();
		for (Book book : user.getBooks()) {
			map.put(book.getId(), book.getTitle());
		}

		model.addAttribute("command", command);
		model.addAttribute("map", map);

		return "users/favourites";
	}

	@RequestMapping(value = "/editFavourites", method = RequestMethod.POST)
	public String userFavouritesPost(@ModelAttribute("command") UserFavouritesCommandObject command, BindingResult result, Model model) {
		
		User user = command.getUser();
		user.setFavouriteBook(command.getBook());
		userManager.saveOrUpdate(user);
		
		return "redirect:/users/" + command.getUser().getId();
	}

	/**
	 * Exception handler for FileNotFoundException
	 *
	 * @return fileNotFound view
	 */
	@ExceptionHandler({UserNotFoundException.class})
	public String userNotFoundExceptionHandler() {
		return "exceptions/userNotFound";
	}

}
