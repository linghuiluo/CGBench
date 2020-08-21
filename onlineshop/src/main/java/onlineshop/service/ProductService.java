package onlineshop.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineshop.model.product.Medication;
import onlineshop.model.product.ProductOrder;
import onlineshop.model.user.User;

@Service
public class ProductService {

	@Autowired
	private EntityManager em;

	@Transactional
	public ProductOrder placeOrder(User user, Medication medication, long quantity) {
		ProductOrder order = new ProductOrder(user, medication, quantity);
		order = em.merge(order);
		saveOrderToFile(order);
		return order;
	}

	private void saveOrderToFile(ProductOrder order) {
		File file = new File("orders/" + order.getUser().getUsername() + "/orders.csv");
		try {
			if (!file.getParentFile().exists()) {
				Files.createDirectories(Paths.get(file.getParent()));
			}
			boolean isNew = false;
			if (!file.exists()) {
				isNew = file.createNewFile();
			}
			try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {

				if (isNew) {
					writer.println("id;name;qty;price");
				}
				writer.print(order.getId());
				writer.print(';');
				writer.print(order.getOrderedMedication().getName());
				writer.print(';');
				writer.print(order.getQuantity());
				writer.print(';');
				writer.print(order.getOrderedMedication().getPrice());
				writer.print('\n');
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional
	public void deleteOrders(String fileName, User user) {
		StringBuilder str = new StringBuilder("orders/");
		str.append(fileName);
		String path = str.toString();
		File f = new File(path);
		if (f.exists())
			f.delete();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<ProductOrder> delete = cb.createCriteriaDelete(ProductOrder.class);
		em.createQuery(delete.where(cb.equal(delete.from(ProductOrder.class).get("user"), user))).executeUpdate();

	}

	public List<ProductOrder> getOrders(User user) {
		return em.createQuery("SELECT o FROM ProductOrder o WHERE o.user.email = '" + user.getUsername() + "'",
				ProductOrder.class).getResultList();
	}

	public List<Medication> getProducts() {
		return em.createQuery("SELECT m FROM Medication m", Medication.class).getResultList();
	}

	public List<Medication> getProducts(String medName) {
		return em.createQuery("SELECT m FROM Medication m where m.name like '%" + medName + "%'", Medication.class)
				.getResultList();
	}

	@Transactional
	public Medication createProduct(String name, double price, String imageUrl) {
		Medication m = new Medication(name, price, imageUrl);
		em.persist(m);
		return em.merge(m);
	}

	public Medication getMedication(long medicationId) {
		try {
			return em.createQuery("SELECT m FROM Medication m WHERE m.id = " + medicationId, Medication.class)
					.getSingleResult();
		} catch (NoResultException noResult) {
			return null;
		}
	}

}
