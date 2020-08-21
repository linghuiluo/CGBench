package onlineshop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import onlineshop.model.product.Medication;
import onlineshop.model.user.User;
import onlineshop.service.ProductService;
import onlineshop.service.UserDetailsServiceImpl;

/**
 * This is a data generator for demo, no vulnerabilities in this class.
 * 
 * 
 */
@Component
public class DataGenerator implements CommandLineRunner {

	@Autowired
	private UserDetailsServiceImpl userService;

	@Autowired
	private ProductService productService;

	@SuppressWarnings("unused")
	@Override
	public void run(String... args) throws Exception {
		User testUser;
		try {
			testUser = userService.loadUserByUsername("test@test.de");
		} catch (UsernameNotFoundException unfe) {
			testUser = new User("test@test.de", "test");
			testUser.setFirstName("Alice");
			testUser.setLastName("Wonderland");
			testUser = userService.createNewUser(testUser);
		}

		Medication asp;
		if (productService.getProducts().isEmpty()) {
			asp = productService.createProduct("Aspiration", 22.95, "adam-lemieux-1419860-unsplash.jpg");
			productService.createProduct("Ibuprofan", 22.95, "brett-jordan-1379182-unsplash.jpg");
			productService.createProduct("Voltronen", 22.95, "freestocks-org-126848-unsplash.jpg");
			productService.createProduct("Supradyn", 22.95, "brett-jordan-1379182-unsplash.jpg");
			productService.createProduct("Bepanthen", 22.95, "joshua-coleman-623077-unsplash.jpg");
			productService.createProduct("Nicolette", 22.95, "pina-messina-464953-unsplash.jpg");
			productService.createProduct("Thomasyrin", 22.95, "kenny-luo-1310669-unsplash.jpg");
			productService.createProduct("Weck", 22.95, "simone-van-der-koelen-611419-unsplash.jpg");
			productService.createProduct("Tantum Verde", 22.95, "kenny-luo-1310669-unsplash.jpg");
			productService.createProduct("Canisten", 22.95, "simone-van-der-koelen-611419-unsplash.jpg");
		} else {
			asp = productService.getProducts().get(0);
		}

	}

}
