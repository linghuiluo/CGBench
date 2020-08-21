package teleforum.model;

import java.util.LinkedList;
import java.util.List;

public class Thread {
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addPost(Post postId) {
		this.posts.add(postId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	private List<Post> posts = new LinkedList<Post>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;

	public Thread(Integer id, String name) {
		this.name = name;
		this.id = id;
	}

	public Thread(String name) {
		this.name = name;
	}

	public Thread(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Thread{" + "name='" + name + '\'' + ", posts=" + posts + ", id=" + id + '}';
	}

	public Thread() {
	}
}
