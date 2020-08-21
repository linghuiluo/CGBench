package onlinechat.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserMessage implements Comparable<UserMessage> {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String message;
	private Date created;
	private String channel;

	public UserMessage() {
	}

	public UserMessage(String name, String message) {
		this.name = name;
		this.message = message;
		this.created = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public int compareTo(UserMessage o) {
		return getCreated().compareTo(o.getCreated());
	}

}
