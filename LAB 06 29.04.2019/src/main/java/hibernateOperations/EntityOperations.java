package hibernateOperations;

import hibernateModel.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EntityOperations {


    public EntityManager myEntityManager() {
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("org.hibernate.event_server.jpa");
        EntityManager entityManager = entityFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        entityManager.close();
//        entityFactory.close();


//        entityManager.persist(object); <-- dodawanie do bazy danych
        // entityManager.getTransaction().commit(); <-- potwierdzenie operacji
        //entityManager.merge(object) <-- update
        //entityManager.find(Object.class, argument);
        //entityManager.delete(Object);

//        String queryToExecute = "";
//        Query query = entityManager.createQuery(queryToExecute);
//
//
//        User user = (User) query.getSingleResult();
//        List<User> userList = query.getResultList();

        return entityManager;
    }
}
