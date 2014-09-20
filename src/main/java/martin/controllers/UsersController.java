package martin.controllers;

import martin.exceptions.UserNotFoundException;
import javax.validation.Valid;
import martin.models.commandobjects.UserCommandObject;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping(value = "/users/")
@SessionAttributes("command")
public class UsersController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private UsersSeeder usersSeeder;

	@ModelAttribute("command")
	public UserCommandObject createCommand() {
		return new UserCommandObject();
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

	@RequestMapping(value="/createOrEdit", method=RequestMethod.GET)
	public String userCreateOrEdit(@RequestParam(value = "id", required = false) Long id, Model model) throws UserNotFoundException {
		User user = null;
		if(id == null) {
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


	@RequestMapping(value="/createOrEdit", method=RequestMethod.POST)
	public String userCreateOrEditPost(@ModelAttribute("command") @Valid UserCommandObject command, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			return "users/userCreateOrEdit";
		}

		userManager.saveOrUpdate(command.getUser());
		
		return "redirect:/users/" + command.getUser().getId();
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
