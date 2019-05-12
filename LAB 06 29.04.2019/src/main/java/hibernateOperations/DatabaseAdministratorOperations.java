package hibernateOperations;

import hibernateModel.Event;
import hibernateModel.EventEntry;
import hibernateModel.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TableEventEntry;
import operations.DatabaseLoginOperations;
import operations.DatabaseOperations;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DatabaseAdministratorOperations {

    private EntityManagerOperations EMO = new EntityManagerOperations();
    private DatabaseLoginOperations DLO = new DatabaseLoginOperations();
    private DatabaseOperations DO = new DatabaseOperations();


    public ObservableList<User> findAllUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String query = "SELECT u FROM User u";

        TypedQuery<User> typedQuery = EMO.getEntityManager().createQuery(query, User.class);
        users.addAll(typedQuery.getResultList());


        EMO.closeConnection();
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
        EMO.closeConnection();

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
            EMO.closeConnection();
        }

        return tmpUser;
    }

    public void deleteUserFromDatabase(long userID) {
        User userToDelete = findExistingUserToDelete(userID);

        EMO.getEntityManager().remove(userToDelete);
        EMO.closeConnection();
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
            EMO.closeConnection();
        }


        return existingEntry;
    }

    public EventEntry findExistingEntryToDelete(Long userID) {
        EventEntry existingEntry = null;
        String query = "SELECT ee FROM EventEntry ee WHERE ee.userId = :uID";

        TypedQuery<EventEntry> typedQuery = EMO.getEntityManager().createQuery(query, EventEntry.class);
        typedQuery.setParameter("uID", userID);

        try {
            existingEntry = typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            EMO.closeConnection();
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
        EMO.closeConnection();
    }

    public void changeUserPassword(Long userId, String newPassword) {
        String query = "UPDATE User u SET u.password = :uNewPassword WHERE u.id = :uID";

        Query updateQuery = EMO.getEntityManager().createQuery(query);
        updateQuery.setParameter("uNewPassword", newPassword);
        updateQuery.setParameter("uID", userId);
        EMO.getEntityManager().getTransaction().begin();
        updateQuery.executeUpdate();
        EMO.getEntityManager().getTransaction().commit();

        EMO.closeConnection();
    }

    public void addEventToDatabase(String name, String agenda, String date) {
        Event tmpEvent = new Event();
        tmpEvent.setName(name);
        tmpEvent.setAgenda(agenda);
        tmpEvent.setDate(date);

        EMO.getEntityManager().persist(tmpEvent);
        EMO.closeConnection();
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
            EMO.closeConnection();
        }
        return tmpEvent;
    }


    public void deleteEvent(Long eventID) {
        Event tmpEvent = lookForExistingEvent(eventID);
        EMO.getEntityManager().remove(tmpEvent);
        EMO.closeConnection();
    }

    public void updateEvent(Event modifiedEvent) {
        String query = "UPDATE Event e SET e.name = :eName, e.agenda = :eAgenda, e.date = :eDate WHERE e.id = :eID";

        Query updateQuery = EMO.getEntityManager().createQuery(query);
        updateQuery.setParameter("eName",modifiedEvent.getName());
        updateQuery.setParameter("eAgenda", modifiedEvent.getAgenda());
        updateQuery.setParameter("eDate", modifiedEvent.getDate());
        updateQuery.setParameter("eID", modifiedEvent.getId());
        EMO.getEntityManager().getTransaction().begin();
        updateQuery.executeUpdate();
        EMO.getEntityManager().getTransaction().commit();

        EMO.closeConnection();
    }

    public void acceptEntry(Long entryID) {
        String query = "UPDATE EventEntry ee SET ee.status = 'accepted' WHERE e.id = :eID";

        Query updateQuery = EMO.getEntityManager().createQuery(query);
        updateQuery.setParameter("eID", entryID);
        EMO.getEntityManager().getTransaction().begin();
        updateQuery.executeUpdate();
        EMO.getEntityManager().getTransaction().commit();

        EMO.closeConnection();
    }
//
//    public void discardEntry(int entryID) {
//
//        String query = "UPDATE events_entries " +
//                "SET " +
//                "status = 'canceled'" +
//                "WHERE entry_id = '" + entryID + "';";
//
//        executeStatementUpdate(query);
//    }
//
//    private void executeStatementUpdate(String query) {
//        try {
//            Statement entryStm = DO.MySQLConnection().createStatement();
//            entryStm.executeUpdate(query);
//            DO.MySQLConnection().close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
