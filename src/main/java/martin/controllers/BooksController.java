package martin.controllers;

import javax.validation.Valid;
import martin.exceptions.BookNotFoundException;
import martin.exceptions.UserNotFoundException;
import martin.models.commandobjects.BookCommandObject;
import martin.models.entities.Book;
import martin.models.entities.User;
import martin.models.managers.BookManager;
import martin.models.managers.UserManager;
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

/**
 *
 * @author martin
 */
@Controller
@RequestMapping(value = "/books/")
@SessionAttributes("command")
public class BooksController {

	@Autowired
	private BookManager bookManager;

	@Autowired
	private UserManager userManager;

	@ModelAttribute("command")
	public BookCommandObject createCommand() {
		return new BookCommandObject();
	}

	@RequestMapping(value="/createOrEdit", method=RequestMethod.GET)
	public String bookCreate(@RequestParam(value = "id", required = false) Long id, 
				@RequestParam(value = "userId", required = false) Long userId, Model model) throws BookNotFoundException, UserNotFoundException {

		Book book = null;
		if(id != null) {
			book = bookManager.findByIdWithUser(id);
			if(book == null) {
				throw new BookNotFoundException();
			}
		} else if (id == null && userId == null) {
			throw new UserNotFoundException();
		} else {
			User user = userManager.findById(userId);
			if(user == null) {
				throw new UserNotFoundException();
			}
			book = new Book();
			book.setUser(user);
		}

		BookCommandObject command = this.createCommand();
		command.setBook(book);

		model.addAttribute("command", command);
		return "books/bookCreateOrEdit";
	}

	@RequestMapping(value="/createOrEdit", method=RequestMethod.POST)
	public String bookCreatePost(@ModelAttribute("command") @Valid BookCommandObject command, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "books/bookCreateOrEdit";
		}	

		bookManager.saveOrUpdate(command.getBook());

		return "redirect:/users/" + command.getBook().getUser().getId();
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

	@ExceptionHandler({BookNotFoundException.class})
	public String bookNotFoundExceptionHandler() {
		return "exceptions/bookNotFound";
	}
}
