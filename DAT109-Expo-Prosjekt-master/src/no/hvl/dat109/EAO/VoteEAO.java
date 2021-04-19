package no.hvl.dat109.EAO;

import no.hvl.dat109.model.Stand;
import no.hvl.dat109.model.User;
import no.hvl.dat109.model.Vote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Stateless EJB class that is used to manage persistence objects
 * Use it to save or update the database's vote table/objects
 */
@Stateless
public class VoteEAO {

	@PersistenceContext(name = "DAT109ExpoDB")
	private EntityManager em;

	/**
	 * @param v The relevant vote
	 * Adds a vote object to the database
	 */
	public void addVote(Vote v) {
		em.persist(v);
	}

	/**
	 * @param v vote object to update
	 *
	 * Updates an existing vote object
	 */
	public void updateVote(Vote v) {
		em.merge(v);
	}

	/**
	 * @param user user that voted
	 * @param stand stand vote applied to
	 * @return a vote object if found, else null
	 *
	 * Finds a vote by a user to a stand if it exists. Used to avoid duplicate votes.
	 */
	public Vote findVote(User user, Stand stand) {
		return em.createQuery(
			"SELECT c FROM Vote c WHERE c.stand.standId = :standId AND c.user.userId = :userId", Vote.class)
			.setParameter("standId", stand.getStandId())
			.setParameter("userId", user.getUserId())
			.getResultList()
			.stream()
			.findFirst()
			.orElse(null);
	}
}