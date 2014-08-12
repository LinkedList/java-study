package martin.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import martin.beans.DateBean;
import martin.beans.ListBean;
import martin.beans.StringBean;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	@Autowired
	ServletContext context;

	/**
	 * Folder that will be used for /image/ route
	 */
	@Value("#{props['folder']}")
	private String folder;

	private ApplicationContext ac;
	
	@RequestMapping(value="/")
	public String index() {
		return "index";
	}
	
	/**
	 * Basic hello world route
	 * @param model ui Model with registered name
	 * @return view helloworld
	 */
	@RequestMapping(value="/hello")
	public String hello(Model model) {
		model.addAttribute("name", "World!");
		return "helloworld";
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
		return "helloworld";
	}
	
	/**
	 * Route that loads a StringBean and displays its contents
	 * @param model Model with stringBean
	 * @return view stringBeanRoute
	 */
	@RequestMapping(value="/stringBeanRoute")
	public String stringBeanRoute(Model model) {
		ac = new ClassPathXmlApplicationContext(
				"beans.xml");

		StringBean stringBean = (StringBean) ac.getBean("stringBean");
		
		model.addAttribute("bean", stringBean);
		return "stringBeanRoute";
	}
	
	/**
	 * Route that loads a DateBean and displays current time
	 * @param model Model with dateBean
	 * @return view dateBeanRoute
	 */
	@RequestMapping(value="/dateBeanRoute")
	public String dateBeanRoute(Model model) {
		ac = new ClassPathXmlApplicationContext(
				"beans.xml");
		
		DateBean dateBean = (DateBean) ac.getBean("dateBean");
		model.addAttribute("bean", dateBean);
		return "dateBeanRoute";
	}
	
	/**
	 * Route that loads a ListBean and displays it's contents
	 * @param model Model with listBean
	 * @return view listBeanRoute
	 */
	@RequestMapping(value="/listBeanRoute")
	public String listBeanRoute(Model model) {
		ac = new ClassPathXmlApplicationContext(
				"beans.xml");
		
		ListBean listBean = (ListBean) ac.getBean("listBean");
		model.addAttribute("bean", listBean);
		return "listBeanRoute";
	}

	/**
	 * Get requested jpg image
	 * @param name filename of the image
	 * @param response response var
	 * @return image 
	 * @throws IOException when the file doesn't exist
	 */
	@RequestMapping(value = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] image(@PathVariable("name") String name,
			HttpServletResponse response) throws IOException {
		String filename = folder + name + ".jpg";
		FileSystemResource file = new FileSystemResource(
				context.getRealPath(filename));

		if (file.exists()) {
			InputStream in = context
					.getResourceAsStream(filename);
			return IOUtils.toByteArray(in);
		} else {
			throw new IOException("File doesn't exist");
		}
	}
	
	/**
	 * Exception handler for IOException
	 * @return fileDoesntExist view
	 */
	@ExceptionHandler({IOException.class})
	public String ioExceptionHandler() {
		return "exceptions/fileDoesntExist";
	}
}
