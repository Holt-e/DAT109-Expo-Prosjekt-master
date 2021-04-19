package no.hvl.dat109.EAO;

import no.hvl.dat109.model.Expo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Stateless EJB class that is used to manage persistence objects
 * Use it to save or update the database's expo table/objects
 */
@Stateless
public class ExpoEAO {

	@PersistenceContext(name = "DAT109ExpoDB")
	private EntityManager em;

	/**
	 * @param expoId
	 * @return found expo, or null if nothing was found
	 *
	 * Finds an expo by its PK, expoId.
	 */
	public Expo findExpo(int expoId) {
		return em.find(Expo.class, expoId);
	}

	/**
	 * @param name name of the expo
	 * @return found expo, or null if nothing was found
	 *
	 * Finds an expo by its name, expoName
	 */
	public Expo findExpo(String name) {
		return em.createQuery("SELECT c FROM Expo c WHERE c.expoName  LIKE :expoName", Expo.class)
			.setParameter("expoName", name)
			.getResultList()
			.stream()
			.findFirst()
			.orElse(null);
	}

	/**
	 * @param e expo object to be added to the database
	 *
	 * Adds a new expo object to the database
	 */
	public void addExpo(Expo e) {
		em.persist(e);
	}

	/**
	 * @param e expo object to update
	 *
	 * Updates an existing expo object
	 */
	public void updateExpo(Expo e) {
		em.merge(e);
	}

    public List<Expo> getAllExpos() {
		return em.createQuery("SELECT e FROM Expo e").getResultList();
    }

	public boolean deleteExpo(String tde) {

		Expo unmanaged = findExpo(tde);
		Expo managed = em.find(Expo.class, unmanaged.getExpoId());

		if(managed != null) {
			em.remove(managed);
			return true;
		}
		return false;
	}
}
