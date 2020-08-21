package teleforum.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teleforum.model.Post;
import teleforum.model.Thread;
import teleforum.service.ThreadService;

@Controller
public class ThreadController {
	@Autowired
	private ThreadService threadService = new ThreadService();

	@GetMapping("/viewThread")
	public String viewThread(@RequestParam(name = "id", required = true) Integer id, Model model) throws IOException {
		Thread t = threadService.getThread(id);
		if (t != null) {
			model.addAttribute("thread", t);
			Post p = new Post("", id, "");
			model.addAttribute("post", p);
			return "viewThread";
		}
		model.addAttribute("msg", "Thread not found");
		return "viewError";
	}

	@GetMapping("/")
	public String listThreads(Model model) throws IOException {
		model.addAttribute("threads", threadService.getThreads());
		model.addAttribute("thread", new Thread());
		return "listThreads";
	}

	@PostMapping("/thread")
	public void postSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Thread thread = new Thread();
		thread.setName(request.getParameter("name"));
		threadService.addThread(thread);
		StringBuilder str = new StringBuilder("/?newThread=");
		str.append(thread.getName());
		response.sendRedirect(str.toString());
	}
}
