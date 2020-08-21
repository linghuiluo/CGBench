package teleforum.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class PostRowMapper implements RowMapper {

	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		Post p = new Post(rs.getString("name"), rs.getInt("threadid"), rs.getString("text"));
		p.setId(rs.getInt("id"));
		return p;
	}
}
