package martin.controllers;

import java.util.List;

import martin.models.User;
import martin.models.UserDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/users/")
public class UsersController {
	
	private ApplicationContext appctx;
	
	@RequestMapping(value="/seeder")
	public String seeder() {
		appctx = new ClassPathXmlApplicationContext("beans.xml");
		
		UserDao ud = (UserDao) appctx.getBean("userDao");
		User user = new User();
		user.setLogin("martin");
		user.setEmail("martin@email.cz");
		ud.save(user);
		
		user = new User();
		user.setLogin("anna");
		user.setEmail("anna@email.com");
		ud.save(user);

		user = new User();
		user.setLogin("ladislav");
		user.setEmail("ladislav@email.com");
		ud.save(user);

		return "redirect:/users/";
	}
	
	@RequestMapping(value="/")
	public String index(Model model) {
		appctx = new ClassPathXmlApplicationContext("beans.xml");
		
		UserDao ud = (UserDao) appctx.getBean("userDao");
		List<User> users = ud.findAll();
		
		model.addAttribute("users", users);
		return "users/index";
	}
}
