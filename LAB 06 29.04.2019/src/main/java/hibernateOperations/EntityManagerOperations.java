package hibernateOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class EntityManagerOperations {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Event-Server");


    EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
