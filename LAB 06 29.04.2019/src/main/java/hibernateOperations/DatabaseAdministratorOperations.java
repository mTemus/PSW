package hibernateOperations;

import hibernateModel.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TableEventEntry;
import operations.DatabaseLoginOperations;
import operations.DatabaseOperations;

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

        String query = "SELECT NEW model.TableEventEntry(ee.entryId, e.name, u.name, u.surname, ee.participationType, ee.foodPreferences, ee.status) FROM Event e JOIN EventEntry ee ON e.id = ee.eventId JOIN User u ON ee.userId = u.id";

        TypedQuery<TableEventEntry> typedQuery = EMO.getEntityManager().createQuery(query, TableEventEntry.class);

        eventEntries.addAll(typedQuery.getResultList());
        EMO.closeConnection();

        return eventEntries;
    }

//    public NormalModelUser findExistingUserToDelete(int userID) {
//        NormalModelUser tmpNormalModelUser = null;
//        PreparedStatement userPrpStm = null;
//
//        try {
//            userPrpStm = DO.MySQLConnection().prepareStatement("select * from user where id = ?");
//            userPrpStm.setLong(1, userID);
//            DO.MySQLConnection().close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        tmpNormalModelUser = DLO.doFindingQuery(userPrpStm);
//
//
//        return tmpNormalModelUser;
//    }
//
//    public void deleteUserFromDatabase(long userID) {
//        String query = "DELETE FROM user WHERE id = " + userID;
//        executeStatementUpdate(query);
//    }
//
//    private PreparedStatement findExistingEntry(String query) {
//        PreparedStatement entryPrpStm = null;
//
//        try {
//            entryPrpStm = DO.MySQLConnection().prepareStatement(query);
//            DO.MySQLConnection().close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return entryPrpStm;
//    }
//
//    public Boolean findExistingEntryToDelete(int userID) {
//        Boolean tmpEntry = null;
//        String query = "select * from events_entries where user_id = " + userID + ";";
//        PreparedStatement entryPrpStm = findExistingEntry(query);
//        tmpEntry = doFindingQueryOfEntry(entryPrpStm);
//
//        return tmpEntry;
//    }
//
//    public Boolean findExistingEntryToModify(int entryID) {
//        Boolean tmpEntry = null;
//        String query = "select * from events_entries where entry_id = " + entryID + ";";
//        PreparedStatement entryPrpStm = findExistingEntry(query);
//        tmpEntry = doFindingQueryOfEntry(entryPrpStm);
//
//        return tmpEntry;
//    }
//
//    private Boolean doFindingQueryOfEntry(PreparedStatement findStm) {
//        ResultSet entryResultFind;
//
//        try {
//            entryResultFind = findStm.executeQuery();
//
//            return entryResultFind.next();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public void deleteEntryFromDatabase(long userID) {
//        String query = "DELETE FROM events_entries WHERE user_id = " + userID;
//
//        executeStatementUpdate(query);
//    }
//
//    public void changeUserPassword(int userId, String newPassword) {
//        String query = "UPDATE user " +
//                "SET " +
//                "password = '" + newPassword + "'" +
//                "WHERE id LIKE '" + userId + "';";
//
//        executeStatementUpdate(query);
//    }
//
//    public void addEventToDatabase(String name, String agenda, String date) {
//        String query = "INSERT INTO event " +
//                "(id_event, " +
//                "event_name, " +
//                "agenda, " +
//                "date) " +
//
//                "VALUES " +
//                "(DEFAULT," +
//                "'" + name + "', " +
//                "'" + agenda + "', " +
//                "'" + date + "');";
//        executeStatementUpdate(query);
//    }
//
//    public PreparedStatement lookForExistingEvent(Long eventID) {
//
//        PreparedStatement findingEventStm = null;
//        try {
//            findingEventStm = DO.MySQLConnection().prepareStatement("SELECT * FROM event WHERE id_event LIKE ?");
//            findingEventStm.setLong(1, eventID);
//
//            DO.MySQLConnection().close();
//            return findingEventStm;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public NormalModelEvent doFindingEventQuery(PreparedStatement findStm) {
//        ResultSet eventResultFind;
//        NormalModelEvent tmp_NormalModel_event = null;
//
//        try {
//            eventResultFind = findStm.executeQuery();
//            if (eventResultFind.next()) {
//                tmp_NormalModel_event = new NormalModelEvent(eventResultFind.getLong("id_event"),
//                        eventResultFind.getString("event_name"),
//                        eventResultFind.getString("agenda"),
//                        eventResultFind.getString("date"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return tmp_NormalModel_event;
//    }
//
//    public void deleteEvent(Long eventID) {
//
//        String query = "DELETE FROM event WHERE id_event = " + eventID;
//        executeStatementUpdate(query);
//    }
//
//    public void updateEvent(NormalModelEvent modifiedNormalModelEvent) {
//
//        String query = "UPDATE event " +
//                "SET " +
//                "event_name = '" + modifiedNormalModelEvent.getName() + "'," +
//                "agenda = '" + modifiedNormalModelEvent.getAgenda() + "'," +
//                "date = '" + modifiedNormalModelEvent.getDate() + "'" +
//                "WHERE id_event LIKE '" + modifiedNormalModelEvent.getId() + "';";
//
//        executeStatementUpdate(query);
//    }
//
//    public void acceptEntry(int entryID) {
//
//        String query = "UPDATE events_entries " +
//                "SET " +
//                "status = 'accepted'" +
//                "WHERE entry_id = '" + entryID + "';";
//        executeStatementUpdate(query);
//    }
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
