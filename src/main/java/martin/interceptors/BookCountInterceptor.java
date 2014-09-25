package martin.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import martin.models.managers.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 *
 * @author martin
 */
@Component
public class BookCountInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private BookManager bookManager;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (ClassUtils.isAssignableValue(ResourceHttpRequestHandler.class, handler)) {
		    return;
		}
		Integer bookCount = bookManager.findAll().size();

		modelAndView.addObject("bookCount", bookCount);
	}	
}
