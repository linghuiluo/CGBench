package teleforum.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class ThreadRowMapper implements RowMapper {

	public Thread mapRow(ResultSet rs, int rowNum) throws SQLException {
		Thread t = new Thread();
		t.setId(rs.getInt("id"));
		t.setName(rs.getString("name"));
		return t;
	}
}
