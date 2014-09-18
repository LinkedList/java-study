package martin.controllers;

import martin.exceptions.UserNotFoundException;
import java.util.List;
import javax.validation.Valid;
import martin.models.entities.User;
import martin.models.managers.UserManager;
import martin.models.seeders.UsersSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/users/")
public class UsersController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private UsersSeeder usersSeeder;

	@RequestMapping(value = "/seeder")
	public String seeder() {
		
		usersSeeder.seed();

		return "redirect:/users/";
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

	@RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	public String userEdit(@PathVariable("id") Long id, Model model) throws UserNotFoundException {

		User user = userManager.findById(id);

		if (user == null) {
			throw new UserNotFoundException();
		}
		model.addAttribute("user", user);
		return "users/userEdit";
	}

	@RequestMapping(value = "/edit/{id}", method=RequestMethod.POST)
	public String userEditPost(@PathVariable("id") Long id, @ModelAttribute @Valid User user, BindingResult result,  Model model) throws UserNotFoundException {
		if(result.hasErrors()) {
			return "users/userEdit";
		}
		userManager.saveOrUpdate(user);

		return "redirect:/users/" + id;
	}

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String userCreate(Model model) {
		User user = new User();

		model.addAttribute("user", user);
		return "users/userCreate";
	}


	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String userCreatePost(@ModelAttribute @Valid User user, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "users/userCreate";
		}

		userManager.saveOrUpdate(user);
		
		return "redirect:/users/" + user.getId();
	}
	/**
	 * Exception handler for FileNotFoundException
	 * @return fileNotFound view
	 */
	@ExceptionHandler({UserNotFoundException.class})
	public String userNotFoundExceptionHandler() {
		return "exceptions/userNotFound";
	}

}
