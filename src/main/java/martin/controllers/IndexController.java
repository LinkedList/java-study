package martin.controllers;

import javax.servlet.ServletContext;

import martin.beans.DateBean;
import martin.beans.ListBean;
import martin.beans.StringBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@Autowired
	private ServletContext servletctx;

	/**
	 * Folder that will be used for /image/ route
	 */
	@Value("#{props['folder']}")
	private String folder;

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
	 * Route that loads a StringBean and displays its contents
	 * @param model Model with stringBean
	 * @return view stringBeanRoute
	 */
	@RequestMapping(value="/stringBeanRoute")
	public String stringBeanRoute(Model model) {
		appctx = new ClassPathXmlApplicationContext(
				"beans.xml");

		StringBean stringBean = (StringBean) appctx.getBean("stringBean");
		
		model.addAttribute("bean", stringBean);
		return "index/stringBeanView";
	}
	
	/**
	 * Route that loads a DateBean and displays current time
	 * @param model Model with dateBean
	 * @return view dateBeanRoute
	 */
	@RequestMapping(value="/dateBeanRoute")
	public String dateBeanRoute(Model model) {
		appctx = new ClassPathXmlApplicationContext(
				"beans.xml");
		
		DateBean dateBean = (DateBean) appctx.getBean("dateBean");
		model.addAttribute("bean", dateBean);
		return "index/dateBeanView";
	}
	
	/**
	 * Route that loads a ListBean and displays it's contents
	 * @param model Model with listBean
	 * @return view listBeanRoute
	 */
	@RequestMapping(value="/listBeanRoute")
	public String listBeanRoute(Model model) {
		appctx = new ClassPathXmlApplicationContext(
				"beans.xml");
		
		ListBean listBean = (ListBean) appctx.getBean("listBean");
		model.addAttribute("bean", listBean);
		return "index/listBeanView";
	}
	
}
