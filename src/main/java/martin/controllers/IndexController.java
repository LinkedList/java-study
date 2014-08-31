package martin.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@Autowired
	private ApplicationContext appctx;
	
	@RequestMapping(value="/")
	public String index() {
		return "index/index";
	}
	
	/**
	 * Basic hello world route
	 * @param model ui Model with registered name
	 * @return view helloworld
	 */
	@RequestMapping(value="/hello")
	public String hello(Model model) {
		model.addAttribute("name", "World!");
		return "index/helloworld";
	}

	/**
	 * Hello route with name parameter
	 * @param name name that will be helloed to
	 * @param model ui Model with name
	 * @return
	 */
	@RequestMapping(value="/hello/{name}")
	public String helloVar(@PathVariable("name") String name, Model model) {
		model.addAttribute("name", name);
		return "index/helloworld";
	}
	
	/**
	 * View model route with different things passed to a view
	 * @param model Model with stringBean
	 * @return view stringBeanRoute
	 */
	@RequestMapping(value="/viewModelTest")
	public String viewModelTest(Model model) {
		model.addAttribute("string", "This is a string");
		model.addAttribute("date", new Date());
		model.addAttribute("list", new String[] {"apple", "banana", "orange"});
		return "index/viewModelTest";
	}
}