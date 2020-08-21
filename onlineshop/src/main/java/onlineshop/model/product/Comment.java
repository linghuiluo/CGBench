package onlineshop.model.product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import onlineshop.model.AbstractEntity;
import onlineshop.model.user.User;

@Entity
public class Comment extends AbstractEntity {

	private String text;

	@ManyToOne
	private User user;

	@ManyToOne
	private Medication medication;

	public Comment() {
	}

	public Comment(User user, String text, Medication medication) {
		setText(text);
		setUser(user);
		setMedication(medication);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

}