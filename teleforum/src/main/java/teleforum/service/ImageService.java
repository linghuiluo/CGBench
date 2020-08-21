package teleforum.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import teleforum.model.Image;
import teleforum.model.ImageRowMapper;

@Service
public class ImageService {

	@Autowired
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public void reset() throws IOException {
		jdbcTemplate.execute("DROP TABLE image IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE image(id SERIAL, path VARCHAR(255))");

		cleanImages(new File("images/"));
		Files.copy(Paths.get("initialImages/face.png"), Paths.get("images/face.png"),
				StandardCopyOption.REPLACE_EXISTING);

	}

	private void cleanImages(File dir) {
		for (File file : dir.listFiles())
			if (!file.isDirectory())
				file.delete();
	}

	@SuppressWarnings("unchecked")
	public List<Image> getImages() {
		return (List<Image>) jdbcTemplate.query("SELECT id, path FROM image ", new ImageRowMapper());
	}

	public void addImage(Image i) {
		jdbcTemplate.execute("INSERT INTO image(path) VALUES ('" + i.getPath() + "')");
	}
}
