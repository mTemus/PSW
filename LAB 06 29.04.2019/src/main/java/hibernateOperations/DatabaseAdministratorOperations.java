package hibernateOperations;

import hibernateModel.Event;
import hibernateModel.EventEntry;
import hibernateModel.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TableEventEntry;

import javax.persistence.*;

public class DatabaseAdministratorOperations {

    private EntityManagerOperations EMO = new EntityManagerOperations();
    private DatabaseLoginOperations DLO = new DatabaseLoginOperations();


    public ObservableList<User> findAllUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String query = "SELECT u FROM User u";

        TypedQuery<User> typedQuery = EMO.getEntityManager().createQuery(query, User.class);
        users.addAll(typedQuery.getResultList());


        return users;
    }

    public ObservableList<TableEventEntry> findAllTableEventEntries() {
        ObservableList<TableEventEntry> eventEntries = FXCollections.observableArrayList();

        String query = "SELECT NEW model.TableEventEntry(" +
                "ee.entryId, " +
                "e.name, " +
                "u.name, " +
                "u.surname, " +
                "ee.participationType, " +
                "ee.foodPreferences, " +
                "ee.status) " +
                "FROM Event e JOIN EventEntry ee ON e.id = ee.eventId JOIN User u ON ee.userId = u.id";

        TypedQuery<TableEventEntry> typedQuery = EMO.getEntityManager().createQuery(query, TableEventEntry.class);

        eventEntries.addAll(typedQuery.getResultList());
        return eventEntries;
    }

    public User findExistingUserToDelete(long userID) {
        User tmpUser = null;
        String query = "SELECT u FROM User u WHERE u.id = :uID";

        TypedQuery<User> typedQuery = EMO.getEntityManager().createQuery(query, User.class);
        typedQuery.setParameter("uID", userID);

        try {
            tmpUser = typedQuery.getSingleResult();

        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {

        }

        return tmpUser;
    }

    public void deleteUserFromDatabase(long userID) {
        EntityManager EM = EMO.getEntityManager();

        String query = "DELETE User u WHERE u.id = :uID";

        Query updateQuery = EM.createQuery(query);
        updateQuery.setParameter("uID", userID);
        EM.getTransaction().begin();
        updateQuery.executeUpdate();
        EM.getTransaction().commit();
        EM.close();
    }

    public EventEntry findExistingEntry(Long entryID) {
        EventEntry existingEntry = null;

        String query = "SELECT ee FROM EventEntry ee WHERE ee.entryId = :eID";

        TypedQuery<EventEntry> typedQuery = EMO.getEntityManager().createQuery(query, EventEntry.class);
        typedQuery.setParameter("eID", entryID);

        try {
            existingEntry = typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {

        }


        return existingEntry;
    }

    public EventEntry findExistingEntryToDelete(Long userID) {
        EventEntry existingEntry;
        String query = "SELECT ee FROM EventEntry ee WHERE ee.userId = :uID";

        TypedQuery<EventEntry> typedQuery = EMO.getEntityManager().createQuery(query, EventEntry.class);
        typedQuery.setParameter("uID", userID);

        try {
            existingEntry = typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            existingEntry = null;
        } finally {

        }
        return existingEntry;
    }


    public EventEntry findExistingEntryToModify(Long entryID) {
        EventEntry entryToModify = findExistingEntry(entryID);
        return entryToModify;
    }


    public void deleteEntryFromDatabase(long userID) {
        EventEntry deletedEntry = findExistingEntryToDelete(userID);
        EMO.getEntityManager().remove(deletedEntry);

    }

    public void changeUserPassword(Long userId, String newPassword) {
        EntityManager EM = EMO.getEntityManagerFactory().createEntityManager();

        String query = "UPDATE User u SET u.password = :uNewPassword WHERE u.id = :uID";

        Query updateQuery = EM.createQuery(query);
        updateQuery.setParameter("uNewPassword", newPassword);
        updateQuery.setParameter("uID", userId);
        EM.getTransaction().begin();
        updateQuery.executeUpdate();
        EM.getTransaction().commit();
        EM.close();

    }

    public void addEventToDatabase(String name, String agenda, String date) {
        EntityManager em = EMO.getEntityManager();
        Event tmpEvent = new Event();
        tmpEvent.setName(name);
        tmpEvent.setAgenda(agenda);
        tmpEvent.setDate(date);

        em.getTransaction().begin();
        em.persist(tmpEvent);
        em.getTransaction().commit();
        em.close();

    }

    public Event lookForExistingEvent(Long eventID) {
        Event tmpEvent = null;
        String query = "SELECT e FROM Event e WHERE e.id = :eID";

        TypedQuery<Event> typedQuery = EMO.getEntityManager().createQuery(query, Event.class);
        typedQuery.setParameter("eID", eventID);

        try {
            tmpEvent = typedQuery.getSingleResult();

        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {

        }
        return tmpEvent;
    }


    public void deleteEvent(Long eventID) {
        EntityManager EM = EMO.getEntityManager();

        String query = "DELETE Event e WHERE e.id = :eID";

        Query updateQuery = EM.createQuery(query);
        updateQuery.setParameter("eID", eventID);
        EM.getTransaction().begin();
        updateQuery.executeUpdate();
        EM.getTransaction().commit();
        EM.close();
    }

    public void updateEvent(Event modifiedEvent) {
        EntityManager em = EMO.getEntityManager();
        String query = "UPDATE Event e SET e.name = :eName, e.agenda = :eAgenda, e.date = :eDate WHERE e.id = :eID";

        Query updateQuery = em.createQuery(query);
        updateQuery.setParameter("eName", modifiedEvent.getName());
        updateQuery.setParameter("eAgenda", modifiedEvent.getAgenda());
        updateQuery.setParameter("eDate", modifiedEvent.getDate());
        updateQuery.setParameter("eID", modifiedEvent.getId());
        em.getTransaction().begin();
        updateQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();

    }

    public void acceptEntry(Long entryID) {
        String status = "'accepted'";

        modifyEntryStatus(status, entryID);
    }

    public void discardEntry(Long entryID) {
        String status = "'canceled'";

        modifyEntryStatus(status, entryID);
    }

    private void modifyEntryStatus(String status, Long entryID) {
        String query = "UPDATE EventEntry ee SET ee.status = " + status + " WHERE ee.entryId = :eID";
        Query updateQuery = EMO.getEntityManager().createQuery(query);
        updateQuery.setParameter("eID", entryID);
        EMO.getEntityManager().getTransaction().begin();
        updateQuery.executeUpdate();
        EMO.getEntityManager().getTransaction().commit();


    }


}
