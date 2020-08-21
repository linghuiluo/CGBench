package teleforum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import teleforum.model.Thread;
import teleforum.model.ThreadRowMapper;

@Service
public class ThreadService {
	@Autowired
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	@Autowired
	PostService postService = new PostService();

	public void reset() {
		jdbcTemplate.execute("DROP TABLE threads IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE threads(id SERIAL, name VARCHAR(255))");
	}

	@SuppressWarnings("unchecked")
	public List<Thread> getThreads() {
		return (List<Thread>) jdbcTemplate.query("SELECT id, name FROM threads ", new ThreadRowMapper());
	}

	@SuppressWarnings("unchecked")
	public Thread getThread(Integer id) {
		String query = "SELECT id, name FROM threads WHERE id = " + id.toString();
		Thread t = (Thread) jdbcTemplate.queryForObject(query, new ThreadRowMapper());
		if (t != null) {
			t.setPosts(postService.findPosts(t.getId()));
		}
		return t;
	}

	public void addThread(Thread t) {
		jdbcTemplate.execute("INSERT INTO threads(name) VALUES ('" + t.getName() + "')");
	}
}
