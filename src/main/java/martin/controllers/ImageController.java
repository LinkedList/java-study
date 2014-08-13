package martin.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/image")
public class ImageController {
	@Autowired
	private ServletContext servletctx;

	/**
	 * Folder that will be used for /image/ route
	 */
	@Value("#{props['folder']}")
	private String folder;

	/**
	 * Get requested jpg image
	 * @param name filename of the image
	 * @param response response var
	 * @return image 
	 * @throws IOException when the file doesn't exist
	 */
	@RequestMapping(value = "/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] image(@PathVariable("name") String name,
			HttpServletResponse response) throws IOException {
		String filename = folder + name + ".jpg";
		FileSystemResource file = new FileSystemResource(
				servletctx.getRealPath(filename));

		if (file.exists()) {
			InputStream in = servletctx
					.getResourceAsStream(filename);
			return IOUtils.toByteArray(in);
		} else {
			throw new FileNotFoundException("File doesn't exist");
		}
	}
	
	/**
	 * Exception handler for FileNotFoundException
	 * @return fileNotFound view
	 */
	@ExceptionHandler({FileNotFoundException.class})
	public String fileNotFoundExceptionHandler() {
		return "exceptions/fileNotFound";
	}
}
