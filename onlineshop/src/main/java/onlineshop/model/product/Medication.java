package onlineshop.model.product;

import javax.persistence.Entity;

import onlineshop.model.AbstractEntity;

@Entity
public class Medication extends AbstractEntity {

	private String name;
	private double price;
	private String imageUrl;

	public Medication() {
	}

	public Medication(String name, double price, String imageUrl) {
		setName(name);
		setPrice(price);
		setImageUrl(imageUrl);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
