package hibernateOperations;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class EntityOperations {


    public Session myHibernateSession() {
//        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("org.hibernate.event_server.jpa");
//        EntityManager entityManager = entityFactory.createEntityManager();


        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


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

        return session;
    }
}
