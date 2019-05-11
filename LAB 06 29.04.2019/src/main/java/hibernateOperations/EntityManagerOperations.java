package hibernateOperations;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerOperations {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Event-Server");

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
