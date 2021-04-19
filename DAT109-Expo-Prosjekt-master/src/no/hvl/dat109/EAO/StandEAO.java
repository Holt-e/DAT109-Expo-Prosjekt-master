package no.hvl.dat109.EAO;

import no.hvl.dat109.model.Stand;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Stateless EJB class that is used to manage persistence objects
 * Use it to save or update the database's stand table/objects
 */
@Stateless
public class StandEAO {

	@PersistenceContext(name = "DAT109ExpoDB")
	private EntityManager em;

	/**
	 * @param name, standName to search for
	 * @return a stand object if found, or null
	 */
	public Stand findStand(String name) {
		return em.createQuery(
			"SELECT c FROM Stand c WHERE c.standName LIKE :standName", Stand.class)
			.setParameter("standName", name)
			.getResultList()
			.stream()
			.findFirst()
			.orElse(null);

	}

	/**
	 * @param s stand object to be added to the database
	 *
	 * Adds a new stand object to the database
	 */
	public void addStand(Stand s) {
		em.persist(s);
	}

	/**
	 * @param s stand object to update
	 *
	 * Updates an existing stand object
	 */
	public void updateStand(Stand s) {
		em.merge(s);
	}
}