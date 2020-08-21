package onlineshop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineshop.model.product.Comment;
import onlineshop.model.product.Medication;

@Service
public class CommentService {

	@Autowired
	private EntityManager em;

	@Transactional
	public void saveComment(Comment comment) {
		Sanitizer sanitizer = new Sanitizer();
		comment.setText(sanitizer.sanitize(comment.getText()));
		em.persist(comment);
	}

	public List<Comment> getComments(Medication medication) {
		return em.createQuery("Select c FROM Comment c where medication = :medication order by id", Comment.class)
				.setParameter("medication", medication).getResultList();
	}

}
