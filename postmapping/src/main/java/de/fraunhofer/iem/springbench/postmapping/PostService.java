package de.fraunhofer.iem.springbench.postmapping;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
@Service
public class PostService {
	private final AtomicLong counter = new AtomicLong();

	private final Set<Post> posts = new HashSet<>(Set.of(new Post(counter.incrementAndGet(), "Post one"),
			new Post(counter.incrementAndGet(), "Post two"), new Post(counter.incrementAndGet(), "Post three"),
			new Post(counter.incrementAndGet(), "Post four")));

	public Post save(Post p) {

		var post = new Post(counter.incrementAndGet(), p.getContent());
		this.posts.add(post);
		return post;
	}

	public Set<Post> all() {

		return this.posts;
	}
}
