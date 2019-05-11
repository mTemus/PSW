package hibernateOperations;

import hibernateModel.User;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class DatabaseLoginOperations {
    private User tmp_user = null;
    private EntityManagerOperations EMO = new EntityManagerOperations();

    public User searchForExistingUser(String userLogin, String userPassword) {
        User existingUser = null;

        EntityManager entityManager = EMO.getEntityManagerFactory().createEntityManager();

        String query = "SELECT u FROM User u WHERE u.login = :uLogin AND u.password = :uPassword";

        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter("uLogin", userLogin);
        typedQuery.setParameter("uPassword", userPassword);

        try {
            existingUser = typedQuery.getSingleResult();

            System.out.println(existingUser.getName() + " " + existingUser.getSurname());
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            EMO.getEntityManagerFactory().close();
        }

        return existingUser;
    }

}
