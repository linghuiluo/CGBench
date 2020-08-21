package teleforum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import teleforum.model.Image;
import teleforum.model.Post;
import teleforum.model.Thread;
import teleforum.service.ImageService;
import teleforum.service.MonitorService;
import teleforum.service.PostService;
import teleforum.service.ThreadService;

@SpringBootApplication
public class TeleForumApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TeleForumApplication.class, args);
	}

	@Autowired
	private ThreadService threadService = new ThreadService();
	@Autowired
	private PostService postService = new PostService();
	@Autowired
	private MonitorService monitorService = new MonitorService();
	@Autowired
	private ImageService imageService = new ImageService();

	@Override
	public void run(String... strings) throws Exception {

		threadService.reset();
		postService.reset();
		monitorService.reset();
		imageService.reset();

		threadService.addThread(new Thread("Faces"));
		threadService.addThread(new Thread("Java Spring"));

		postService.addPost(new Post("img:face.png", 1, "Jeah"));
		postService.addPost(new Post("Homer", 1, "Nooooooooo"));
		postService.addPost(new Post("c++", 2, "rocks"));
		postService.addPost(new Post("java", 2, "rocks even more"));

		imageService.addImage(new Image("face.png"));

		monitorService.addMonitor("Rock", "rocks", 0, true);
	}
}