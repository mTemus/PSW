package hibernateOperations;

import hibernateModel.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class DatabaseLoginOperations {
    private EntityManagerOperations EMO = new EntityManagerOperations();

    public User searchForExistingUser(String userLogin, String userPassword) {
        User existingUser;
        String query = "SELECT u FROM User u WHERE u.login = :uLogin AND u.password = :uPassword";

        TypedQuery<User> typedQuery = EMO.getEntityManager().createQuery(query, User.class);
        typedQuery.setParameter("uLogin", userLogin);
        typedQuery.setParameter("uPassword", userPassword);

        try {
            existingUser = typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

        return existingUser;
    }

    public void addUserToDatabase(User userToAdd) {
        EntityManager em = EMO.getEntityManager();

        em.getTransaction().begin();
        em.persist(userToAdd);
        em.getTransaction().commit();
        em.close();
    }

}
