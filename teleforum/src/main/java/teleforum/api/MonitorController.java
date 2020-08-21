package teleforum.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import teleforum.model.Monitor;
import teleforum.service.MonitorService;
import teleforum.service.PostService;

@Controller
public class MonitorController {
	@Autowired
	PostService postService = new PostService();
	@Autowired
	MonitorService monitorService = new MonitorService();

	@GetMapping("/monitors")
	public String monitors(Model model) throws IOException {
		model.addAttribute("monitors", monitorService.getMonitors());
		model.addAttribute("monitor", new Monitor());
		return "listMonitors";
	}

	@PostMapping("/monitors")
	public String postSubmit(HttpServletRequest request, Model model) {
		String query = request.getParameter("query");
		String name = request.getParameter("name");
		String maxResultsString = request.getParameter("maxResults");
		int maxResults = 0;
		if (!maxResultsString.isEmpty()) {
			maxResults = Integer.parseInt(maxResultsString);
		}
		boolean caseSensitive = Boolean.parseBoolean(request.getParameter("caseSensitive"));
		monitorService.addMonitor(name, query, maxResults, caseSensitive);
		model.addAttribute("monitors", monitorService.getMonitors());
		model.addAttribute("monitor", new Monitor());
		return "listMonitors";
	}

	@GetMapping("/viewMonitor")
	public String viewMonitor(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		Monitor m = monitorService.getMonitor(id);
		if (m == null) {
			model.addAttribute("msg", "Monitor not found");
			return "viewError";
		}
		model.addAttribute("query", m.getQuery());
		model.addAttribute("posts", postService.findPosts(m));
		return "viewMonitor";
	}
}
