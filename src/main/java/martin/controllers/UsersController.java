package martin.controllers;

import java.util.List;
import martin.models.entities.User;
import martin.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/users/")
public class UsersController {
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value="/seeder")
	public String seeder() {
		User user = new User();
		user.setLogin("martin");
		user.setEmail("martin@email.cz");
		userManager.save(user);
		
		user = new User();
		user.setLogin("anna");
		user.setEmail("anna@email.com");
		userManager.save(user);

		user = new User();
		user.setLogin("honza");
		user.setEmail("honza@email.com");
		userManager.save(user);

		user = new User();
		user.setLogin("marek");
		user.setEmail("marek@email.com");
		userManager.save(user);

		user = new User();
		user.setLogin("ladislav");
		user.setEmail("ladislav@email.com");
		userManager.save(user);

		return "redirect:/users/";
	}
	
	@RequestMapping(value="/")
	public String index(Model model) {
		List<User> users = userManager.findAll();
		
		model.addAttribute("users", users);
		return "users/index";
	}

	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Long id) {

		userManager.delete(id);

		return "redirect:/users/";
	}
}
