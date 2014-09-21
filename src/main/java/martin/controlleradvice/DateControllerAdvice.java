package martin.controlleradvice;

import java.util.Date;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author martin
 */
@ControllerAdvice
public class DateControllerAdvice {

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("date", new  Date());
	}
}
