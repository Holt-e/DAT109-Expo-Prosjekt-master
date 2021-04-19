package no.hvl.dat109.EAO;

import no.hvl.dat109.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Stateless EJB class that is used to manage persistence objects
 * Use it to save or update the database's user table/objects
 */
@Stateless
public class UserEAO {

    @PersistenceContext(name = "DAT109ExpoDB")
    private EntityManager em;

    /**
     * @param b the user object to be added
     *  Adds a new user to the database
     */
    public void addUser(User b) {
        em.persist(b);
    }

    /**
     * @param u user object to update
     *
     * Updates an existing user object
     */
    public void updateUser(User u) {
        em.merge(u);
    }

    /**
     * @param username users phone number to search by
     * @return Fetch a user with the given phone number
     */
    public User findUserByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
            .setParameter("username", username)
            .getResultList()
            .stream()
            .findFirst()
            .orElse(null);
    }

    /**
     * @param id userId PK that is searched for
     * @return a user object if found, or null
     */
    public User findUserById(int id) {
        return em.find(User.class, id);
    }
}
