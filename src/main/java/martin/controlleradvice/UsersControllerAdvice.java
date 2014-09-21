package martin.controlleradvice;

import martin.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author martin
 */
@ControllerAdvice
public class UsersControllerAdvice {

	@Autowired
	private UserManager UserManager;

	@ModelAttribute
	public void addAttributes(Model model) {
		Integer usersSize = UserManager.findAll().size();
		model.addAttribute("usersSize", usersSize);
	}
}
