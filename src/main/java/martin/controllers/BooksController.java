package martin.controllers;

import javax.validation.Valid;
import martin.models.entities.Book;
import martin.models.entities.User;
import martin.models.fobjects.FBook;
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
import org.springframework.web.bind.annotation.RequestParam;

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

	
	@RequestMapping(value="/create/{userId}", method=RequestMethod.GET)
	public String bookCreate(@PathVariable("userId") Long userId, Model model) {
		Book book = new Book();

		model.addAttribute("book", book);
		model.addAttribute("userId", userId);
		return "books/bookCreate";
	}

	@RequestMapping(value="/create/{userId}", method=RequestMethod.POST)
	public String bookCreatePost(@PathVariable("userId") Long userId, @ModelAttribute @Valid Book book, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "books/bookCreate";
		}	

		User user = userManager.findById(userId);
		book.setUser(user);

		bookManager.saveOrUpdate(book);

		return "redirect:/users/" + userId;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String bookEdit(@PathVariable("id") Long id, @RequestParam(required = false, value = "returnToIndex", defaultValue = "false") Boolean returnToIndex, Model model) {

		Book book = bookManager.findById(id);
		FBook fBook = new FBook();
		fBook.setTitle(book.getTitle());
		fBook.setDescription(book.getDescription());

		model.addAttribute("FBook", fBook);
		model.addAttribute("id", id);
		if(returnToIndex) {
			model.addAttribute("returnToIndex", returnToIndex);
		}
		return "books/bookEdit";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String bookEditPost(@PathVariable("id") Long id, @ModelAttribute @Valid FBook fBook, BindingResult result, @RequestParam(required = false, value = "returnToIndex", defaultValue = "false") Boolean returnToIndex, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("returnToIndex", returnToIndex);
			return "books/bookEdit";
		}

		Book bookToSave = bookManager.findById(id);
		bookToSave.setTitle(fBook.getTitle());
		bookToSave.setDescription(fBook.getDescription());

		bookManager.saveOrUpdate(bookToSave);

		if(returnToIndex) {
			return "redirect:/admin/books/";
		} else {
			Book book = bookManager.findByIdWithUser(id);
			return "redirect:/users/" + book.getUser().getId();
		}
	}

	@RequestMapping(value="/delete/{id}")
	public String bookDelete(@PathVariable("id") Long id, @RequestParam(value = "returnToIndex", required = false) Boolean returnToIndex) {
		Book book = bookManager.findByIdWithUser(id);
		User user = book.getUser();

		bookManager.delete(book.getId());
		
		if(returnToIndex != null) {
			return "redirect:/admin/books/";
		} else {
			return "redirect:/users/" + user.getId();
		}
	}
}
