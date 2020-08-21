package teleforum.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import teleforum.model.Image;
import teleforum.service.ImageService;

@Controller
public class ImageController {

	@Autowired
	ImageService imageService = new ImageService();

	@GetMapping("/images")
	public String monitors(Model model) throws IOException {
		model.addAttribute("images", imageService.getImages());
		return "viewImages";
	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public String submit(MultipartHttpServletRequest request, ModelMap modelMap) throws IOException, ServletException {
		MultipartFile file = request.getFile("file");
		modelMap.addAttribute("file", file);
		String filename = file.getOriginalFilename();
		imageService.addImage(new Image(filename));
		InputStream inputStream = file.getInputStream();
		Files.copy(inputStream, Paths.get("images/" + filename), StandardCopyOption.REPLACE_EXISTING);
		return "redirect:/images";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/viewImage")
	public void viewImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String fileName = request.getParameter("image");
			StringBuilder str = new StringBuilder("images/");
			str.append(fileName);
			String path = str.toString();
			File f = new File(path);
			byte[] file = Files.readAllBytes(f.toPath());
			response.reset();
			response.setContentType("image");
			response.getOutputStream().write(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
