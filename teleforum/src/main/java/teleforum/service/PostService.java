package teleforum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import teleforum.model.Monitor;
import teleforum.model.Post;
import teleforum.model.PostRowMapper;

@Service
public class PostService {
	@Autowired
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public void reset() {
		jdbcTemplate.execute("DROP TABLE posts IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE posts(id SERIAL, threadID integer, name VARCHAR(255), text VARCHAR(255))");

	}

	public void addPost(Post p) {
		jdbcTemplate.execute("INSERT INTO posts(name,text,threadid) VALUES ('" + p.getName() + "','" + p.getText()
				+ "','" + p.getThread() + "')");

	}

	@SuppressWarnings("unchecked")
	public Post getPost(Integer id) {
		String query = "SELECT id, name, text, threadID FROM posts WHERE id = " + id.toString();
		return (Post) jdbcTemplate.queryForObject(query, new PostRowMapper());
	}

	public List<Post> findPosts(Monitor monitor) {
		String query = monitor.getQuery();
		if (!monitor.isCaseSensitive()) {
			query = query.toLowerCase();
		}
		String q = "SELECT id, name, text, threadID FROM posts WHERE "
				+ (monitor.isCaseSensitive() ? "text" : "LOWER(text)") + " like '%" + query + "%'";

		@SuppressWarnings("unchecked")
		List<Post> postList = (List<Post>) jdbcTemplate.query(q, new PostRowMapper());
		if (monitor.getMaxResults() > 0) {
			for (int i = postList.size() - 1; i > monitor.getMaxResults() - 1; i--) {
				postList.remove(i);
			}
		}
		return postList;
	}

	public List<Post> findPosts(Integer id) {
		String q = "SELECT id, name, text, threadID FROM posts WHERE threadID = '" + id.toString() + "'";
		@SuppressWarnings("unchecked")
		List<Post> postList = (List<Post>) jdbcTemplate.query(q, new PostRowMapper());
		return postList;
	}
}
