package martin.controllers;

import java.util.List;
import martin.models.entities.User;
import martin.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping(value = "/users/")
public class UsersController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/seeder")
	public String seeder() {
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

		return "redirect:/users/";
	}

	@RequestMapping(value = "/")
	public String index(Model model) {
		List<User> users = userManager.findAll();

		model.addAttribute("users", users);
		return "users/index";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long id) {

		userManager.delete(id);

		return "redirect:/users/";
	}

	@RequestMapping(value = "/user/{id}")
	public String user(@PathVariable("id") Long id, Model model) throws UserNotFoundException {

		User user = userManager.findById(id);

		if (user == null) {
			throw new UserNotFoundException();
		}
		model.addAttribute("user", user);
		return "users/user";
	}

	@RequestMapping(value = "/user/edit/{id}", method=RequestMethod.GET)
	public String userEdit(@PathVariable("id") Long id, Model model) throws UserNotFoundException {

		User user = userManager.findById(id);

		if (user == null) {
			throw new UserNotFoundException();
		}
		model.addAttribute("user", user);
		return "users/userEdit";
	}

	@RequestMapping(value = "/user/edit/{id}", method=RequestMethod.POST)
	public String userEditPost(@PathVariable("id") Long id, @ModelAttribute @Valid User user, BindingResult result,  Model model) throws UserNotFoundException {
		if(result.hasErrors()) {
			return "users/userEdit";
		}
		userManager.saveOrUpdate(user);

		return "redirect:/users/user/" + id;
	}
	/**
	 * Exception handler for FileNotFoundException
	 * @return fileNotFound view
	 */
	@ExceptionHandler({UserNotFoundException.class})
	public String fileNotFoundExceptionHandler() {
		return "exceptions/userNotFound";
	}

	public class UserNotFoundException extends Exception {

		public UserNotFoundException() {
			super();
		}

		public UserNotFoundException(String message) {
			super(message);
		}

		public UserNotFoundException(String message, Throwable cause) {
			super(message, cause);
		}

		public UserNotFoundException(Throwable cause) {
			super(cause);
		}
	}
}
