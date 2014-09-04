package martin.controllers;

import java.util.List;
import martin.models.entities.Book;
import martin.models.managers.BookManager;
import martin.models.seeders.BooksSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
