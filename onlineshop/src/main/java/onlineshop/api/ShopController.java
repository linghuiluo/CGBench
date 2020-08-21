package onlineshop.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import onlineshop.model.product.Comment;
import onlineshop.model.product.Medication;
import onlineshop.model.user.User;
import onlineshop.service.CommentService;
import onlineshop.service.ProductService;
import onlineshop.service.UserDetailsServiceImpl;

@Controller
public class ShopController {

	@Autowired
	private UserDetailsServiceImpl userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CommentService commentService;

	@GetMapping("/login")
	public String getLogin(Model model) {
		return "login";
	}

	@GetMapping("/register")
	public String getRegistration(Model model) {
		return "register";
	}

	@PostMapping("/register")
	public void doRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("password-repeat");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");

		if (password.equals(passwordRepeat)) {
			User user = new User(username, password);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setAddress(address);
			userService.createNewUser(user);
		}
		StringBuilder str = new StringBuilder("/login?firstname=");
		;
		str.append(firstname);
		response.sendRedirect(str.toString());
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("products", productService.getProducts());
		return "home";
	}

	@GetMapping("/product")
	public String showProduct(HttpServletRequest request, Model model) {
		int medicationId = Integer.parseInt(request.getParameter("medicationId"));
		Medication medication = productService.getMedication(medicationId);
		model.addAttribute(medication);
		model.addAttribute("comments", commentService.getComments(medication));
		return "product";
	}

	@GetMapping("/orders")
	public String getOrders(Model model) {
		model.addAttribute("orders", productService.getOrders(UserDetailsServiceImpl.getCurrentUser()));
		return "orders";
	}

	@GetMapping("/ordersFile")
	public void getOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = request.getParameter("fileName");
		StringBuilder str = new StringBuilder("orders/");
		str.append(fileName);
		InputStream is = new FileInputStream(str.toString());
		IOUtils.copy(is, response.getOutputStream());
		response.setHeader("Content-Disposition", "inline; filename=\"orders.csv\"");
		response.setContentType("text/plain; name=\"orders.csv\"");
		response.flushBuffer();
	}

	@PostMapping("/order")
	public void placeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int medicationId = Integer.parseInt(request.getParameter("medicationId"));
		int quantity = Integer.parseInt(request.getParameter("qty"));
		Medication medication = productService.getMedication(medicationId);
		productService.placeOrder(UserDetailsServiceImpl.getCurrentUser(), medication, quantity);
		response.sendRedirect("/");
	}

	@PostMapping("/comment")
	public void createComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String commentText = request.getParameter("comment");
		int medicationId = Integer.parseInt(request.getParameter("medicationId"));
		Medication medication = productService.getMedication(medicationId);
		commentService.saveComment(new Comment(UserDetailsServiceImpl.getCurrentUser(), commentText, medication));
		response.sendRedirect(request.getHeader("Referer"));
	}

	@PostMapping("/search")
	public String search(HttpServletRequest request, Model model) {
		String medName = request.getParameter("medName");
		model.addAttribute("products", productService.getProducts(medName));
		return "home";
	}

	@PostMapping("/deleteOrders")
	public void deleteOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = request.getParameter("fileName");

		productService.deleteOrders(fileName, UserDetailsServiceImpl.getCurrentUser());
		response.sendRedirect("/orders");
	}
}
