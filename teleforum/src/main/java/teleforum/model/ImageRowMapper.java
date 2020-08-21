package teleforum.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class ImageRowMapper implements RowMapper {

	public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
		Image i = new Image();
		i.setId(rs.getInt("id"));
		i.setPath(rs.getString("path"));
		return i;
	}
}
