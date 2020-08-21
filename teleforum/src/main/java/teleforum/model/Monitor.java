package teleforum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Monitor {

	@Id
	@GeneratedValue
	private Integer id;
	private String query;
	private String name;
	private int maxResults;
	private boolean caseSensitive;

	public Monitor(String name, String query, int maxResults, boolean caseSensitive) {
		this.name = name;
		this.query = query;
		this.maxResults = maxResults;
		this.caseSensitive = caseSensitive;
	}

	public Monitor(int id) {
		this.id = id;
	}

	public Monitor(int id, String query) {
		this.id = id;
		this.query = query;
	}

	public Monitor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
}
