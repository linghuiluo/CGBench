package teleforum.model;

public class Image {
	String path;
	String name;
	Integer id;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Image(String path, Integer id) {
		this.path = path;
		this.id = id;
	}

	public Image(String path) {
		this.path = path;
	}

	public Image(Integer id) {
		this.id = id;
	}

	public Image() {
	}

	@Override
	public String toString() {
		return "Image{" + "path='" + path + '\'' + ", id=" + id + '}';
	}
}
