package teleforum.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teleforum.model.Monitor;

@Service
public class MonitorService {
	@Autowired
	EntityManager entityManager;

	@Transactional
	public void reset() {
		entityManager.createNativeQuery("DROP TABLE monitor IF EXISTS").executeUpdate();
		entityManager.createNativeQuery(
				"CREATE TABLE monitor(id SERIAL, query VARCHAR(255), name VARCHAR(255), max_results INT, case_sensitive BIT)")
				.executeUpdate();
	}

	public List<Monitor> getMonitors() {
		return entityManager.createQuery("SELECT m FROM Monitor m ", Monitor.class).getResultList();
	}

	@Transactional
	public void addMonitor(String name, String query, int maxResults, boolean caseSensitive) {
		entityManager.persist(new Monitor(name, query, maxResults, caseSensitive));
	}

	public Monitor getMonitor(Integer id) {
		return entityManager.createQuery("SELECT m FROM Monitor m WHERE m.id = :id", Monitor.class)
				.setParameter("id", id).getSingleResult();

	}
}
