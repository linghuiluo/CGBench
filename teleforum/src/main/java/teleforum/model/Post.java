package teleforum.model;

public class Post {
	private int id;
	private int thread;
	private String text;
	private String name;

	public Post(String name, int thread, String text) {
		this.name = name;
		this.thread = thread;
		this.text = text;
	}

	public Post() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getThread() {
		return thread;
	}

	public int getId() {
		return id;
	}

	public void setThread(int thread) {
		this.thread = thread;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Post{" + "name='" + name + '\'' + ", text='" + text + '\'' + ", thread='" + thread + '\'' + '}';
	}

}
