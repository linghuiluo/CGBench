package teleforum.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teleforum.model.Post;
import teleforum.service.PostService;

@Controller
public class PostController {
	@Autowired
	PostService postService = new PostService();

	@PostMapping("/post")
	public String postSubmit(HttpServletRequest request) throws IOException {
		Post post = new Post();
		post.setName(StringEscapeUtils.escapeSql(request.getParameter("name")));
		post.setText(StringEscapeUtils.escapeSql(request.getParameter("text")));
		post.setThread(Integer.parseInt(StringEscapeUtils.escapeSql(request.getParameter("thread"))));
		postService.addPost(post);
		return "redirect:/viewThread?id=" + post.getThread();
	}

	@GetMapping("/viewPost")
	public String viewPost(@RequestParam(name = "id", required = true) Integer id, Model model) {
		Post p = postService.getPost(id);
		if (p != null) {
			model.addAttribute("post", p);
			return "viewPost";
		}
		model.addAttribute("msg", "Post not found");
		return "viewError";
	}
}
