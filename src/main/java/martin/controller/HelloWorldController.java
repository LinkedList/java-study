package martin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

	@RequestMapping(value="/")
	public String hello(Model model) {
		model.addAttribute("name", "World!");
		return "helloworld";
	}

	@RequestMapping(value="/{name}")
	public String helloVar(@PathVariable("name") String name, Model model) {
		model.addAttribute("name", name);
		return "helloworld";
	}

}
