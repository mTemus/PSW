package hibernateOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerOperations {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Event-Server");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();


    public void closeConnection(){
        entityManagerFactory.close();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
