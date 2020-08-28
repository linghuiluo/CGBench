package de.fraunhofer.iem.springbench.postmapping.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import de.fraunhofer.iem.springbench.postmapping.Post;
import de.fraunhofer.iem.springbench.postmapping.PostService;

@Controller
public class MyController {

	@Autowired
    private PostService postService;
	
	@PostMapping(value = "/posts")
    public ResponseEntity<Post> createPost(HttpServletRequest request,
                                        UriComponentsBuilder uriComponentsBuilder) {

        var content = request.getParameter("content");

        var post = new Post();
        post.setContent(content);

        post = postService.save(post);
        
        String folder = content.trim();

		String cmd = "mkdir " + folder;

		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

        UriComponents uriComponents =
                uriComponentsBuilder.path("/posts/{id}").buildAndExpand(post.getId());
        var location = uriComponents.toUri();

        return ResponseEntity.created(location).build();
    }
}
