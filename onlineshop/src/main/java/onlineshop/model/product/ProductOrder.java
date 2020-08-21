package onlineshop.model.product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import onlineshop.model.AbstractEntity;
import onlineshop.model.user.User;

@Entity
public class ProductOrder extends AbstractEntity {

	@ManyToOne
	private User user;

	@ManyToOne
	private Medication orderedMedication;

	private long quantity;

	public ProductOrder() {

	}

	public ProductOrder(User user, Medication medication, long quantity) {
		setUser(user);
		setOrderedMedication(medication);
		setQuantity(quantity);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Medication getOrderedMedication() {
		return orderedMedication;
	}

	public void setOrderedMedication(Medication orderedMedication) {
		this.orderedMedication = orderedMedication;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

}
