package de.fraunhofer.iem.springbench.postmapping;

import java.util.Objects;

public class Post {
	private Long id;
	private String content;

	public Post() {

	}

	public Post(Long id, String content) {
		this.id = id;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Post post = (Post) o;
		return Objects.equals(id, post.id) && Objects.equals(content, post.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content);
	}
}
