package onlineshop.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import onlineshop.model.user.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDetailsServiceImpl() {
	}

	@Override
	@Transactional
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		TypedQuery<User> userQuery = entityManager
				.createQuery("SELECT u FROM User u where  u.email = :username", User.class)
				.setParameter("username", username);
		try {
			return userQuery.getSingleResult();
		} catch (NoResultException nre) {
			throw new UsernameNotFoundException(nre.getMessage(), nre);
		}

	}

	@Transactional
	public User createNewUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		entityManager.persist(user);
		return entityManager.merge(user);
	}

	public static boolean isUserLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null;
	}

	public static User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return (User) authentication.getPrincipal();
		}
		return null;
	}

}
