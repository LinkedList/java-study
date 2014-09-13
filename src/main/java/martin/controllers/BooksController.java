package martin.controllers;

import java.util.List;
import javax.validation.Valid;
import martin.models.entities.Book;
import martin.models.entities.User;
import martin.models.managers.BookManager;
import martin.models.managers.UserManager;
import martin.models.seeders.BooksSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author martin
 */
@Controller
@RequestMapping(value = "/books/")
public class BooksController {

	@Autowired
	private BookManager bookManager;

	@Autowired
	private UserManager userManager;

	@Autowired
	private BooksSeeder booksSeeder;

	@RequestMapping(value = "/seeder")
	public String seeder() {
		
		booksSeeder.seed();

		return "redirect:/books/";
	}

	@RequestMapping(value = "/")
	public String index(Model model) {
		List<Book> books = bookManager.findAll();

		model.addAttribute("books", books);
		return "books/index";
	}
	
	@RequestMapping(value="/book/create/{userId}", method=RequestMethod.GET)
	public String bookCreate(@PathVariable("userId") Long userId, Model model) {
		Book book = new Book();

		model.addAttribute("book", book);
		model.addAttribute("userId", userId);
		return "books/bookCreate";
	}

	@RequestMapping(value="/book/create/{userId}", method=RequestMethod.POST)
	public String bookCreatePost(@PathVariable("userId") Long userId, @ModelAttribute @Valid Book book, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "books/bookCreate";
		}	

		User user = userManager.findById(userId);
		book.setUser(user);

		bookManager.saveOrUpdate(book);

		return "redirect:/users/user/" + userId;
	}
}
