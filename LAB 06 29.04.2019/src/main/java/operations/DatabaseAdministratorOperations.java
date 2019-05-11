package operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.NormalModelEvent;
import model.NormalModelEventEntry;
import model.NormalModelUser;

import java.sql.*;

public class DatabaseAdministratorOperations {

    private DatabaseLoginOperations DLO = new DatabaseLoginOperations();
    private DatabaseOperations DO = new DatabaseOperations();


    public ObservableList<NormalModelUser> findAllUsers() {

        ObservableList<NormalModelUser> normalModelUsers = FXCollections.observableArrayList();

        try {
            Statement userStatement = DO.MySQLConnection().createStatement();
            ResultSet userResultSet = userStatement.executeQuery("select * from user");

            while (userResultSet.next()) {
                NormalModelUser u = new NormalModelUser(userResultSet.getLong("id"),
                        userResultSet.getString("name"),
                        userResultSet.getString("surname"),
                        userResultSet.getString("login"),
                        userResultSet.getString("password"),
                        userResultSet.getString("email"),
                        userResultSet.getString("date"));
                normalModelUsers.add(u);
            }
            DO.MySQLConnection().close();
            return normalModelUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<NormalModelEventEntry> findAllEventEntries() {
        ObservableList<NormalModelEventEntry> eventEntries = FXCollections.observableArrayList();

        try {
            Statement entryStatement = DO.MySQLConnection().createStatement();
            ResultSet entryResultSet = entryStatement.executeQuery("SELECT events_entries.entry_id, " +
                    "event.event_name, " +
                    "user.name, " +
                    "user.surname, " +
                    "events_entries.participation_type, " +
                    "events_entries.food_preferences, " +
                    "events_entries.status\n" +
                    "FROM event \n" +
                    "JOIN events_entries \n" +
                    "ON event.id_event = events_entries.event_id\n" +
                    "JOIN user \n" +
                    "ON events_entries.user_id = user.id;");

            while (entryResultSet.next()) {
                NormalModelEventEntry ee = new NormalModelEventEntry(entryResultSet.getLong("entry_id"),
                        entryResultSet.getString("event_name"),
                        entryResultSet.getString("name"),
                        entryResultSet.getString("surname"),
                        entryResultSet.getString("participation_type"),
                        entryResultSet.getString("food_preferences"),
                        entryResultSet.getString("status"));
                eventEntries.add(ee);
            }
            DO.MySQLConnection().close();
            return eventEntries;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public NormalModelUser findExistingUserToDelete(int userID) {
        NormalModelUser tmpNormalModelUser = null;
        PreparedStatement userPrpStm = null;

        try {
            userPrpStm = DO.MySQLConnection().prepareStatement("select * from user where id = ?");
            userPrpStm.setLong(1, userID);
            DO.MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tmpNormalModelUser = DLO.doFindingQuery(userPrpStm);


        return tmpNormalModelUser;
    }

    public void deleteUserFromDatabase(long userID) {
        String query = "DELETE FROM user WHERE id = " + userID;
        executeStatementUpdate(query);
    }

    private PreparedStatement findExistingEntry(String query) {
        PreparedStatement entryPrpStm = null;

        try {
            entryPrpStm = DO.MySQLConnection().prepareStatement(query);
            DO.MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entryPrpStm;
    }

    public Boolean findExistingEntryToDelete(int userID) {
        Boolean tmpEntry = null;
        String query = "select * from events_entries where user_id = " + userID + ";";
        PreparedStatement entryPrpStm = findExistingEntry(query);
        tmpEntry = doFindingQueryOfEntry(entryPrpStm);

        return tmpEntry;
    }

    public Boolean findExistingEntryToModify(int entryID) {
        Boolean tmpEntry = null;
        String query = "select * from events_entries where entry_id = " + entryID + ";";
        PreparedStatement entryPrpStm = findExistingEntry(query);
        tmpEntry = doFindingQueryOfEntry(entryPrpStm);

        return tmpEntry;
    }

    private Boolean doFindingQueryOfEntry(PreparedStatement findStm) {
        ResultSet entryResultFind;

        try {
            entryResultFind = findStm.executeQuery();

            return entryResultFind.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteEntryFromDatabase(long userID) {
        String query = "DELETE FROM events_entries WHERE user_id = " + userID;

        executeStatementUpdate(query);
    }

    public void changeUserPassword(int userId, String newPassword) {
        String query = "UPDATE user " +
                "SET " +
                "password = '" + newPassword + "'" +
                "WHERE id LIKE '" + userId + "';";

        executeStatementUpdate(query);
    }

    public void addEventToDatabase(String name, String agenda, String date) {
        String query = "INSERT INTO event " +
                "(id_event, " +
                "event_name, " +
                "agenda, " +
                "date) " +

                "VALUES " +
                "(DEFAULT," +
                "'" + name + "', " +
                "'" + agenda + "', " +
                "'" + date + "');";
        executeStatementUpdate(query);
    }

    public PreparedStatement lookForExistingEvent(Long eventID) {

        PreparedStatement findingEventStm = null;
        try {
            findingEventStm = DO.MySQLConnection().prepareStatement("SELECT * FROM event WHERE id_event LIKE ?");
            findingEventStm.setLong(1, eventID);

            DO.MySQLConnection().close();
            return findingEventStm;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public NormalModelEvent doFindingEventQuery(PreparedStatement findStm) {
        ResultSet eventResultFind;
        NormalModelEvent tmp_NormalModel_event = null;

        try {
            eventResultFind = findStm.executeQuery();
            if (eventResultFind.next()) {
                tmp_NormalModel_event = new NormalModelEvent(eventResultFind.getLong("id_event"),
                        eventResultFind.getString("event_name"),
                        eventResultFind.getString("agenda"),
                        eventResultFind.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp_NormalModel_event;
    }

    public void deleteEvent(Long eventID) {

        String query = "DELETE FROM event WHERE id_event = " + eventID;
        executeStatementUpdate(query);
    }

    public void updateEvent(NormalModelEvent modifiedNormalModelEvent) {

        String query = "UPDATE event " +
                "SET " +
                "event_name = '" + modifiedNormalModelEvent.getName() + "'," +
                "agenda = '" + modifiedNormalModelEvent.getAgenda() + "'," +
                "date = '" + modifiedNormalModelEvent.getDate() + "'" +
                "WHERE id_event LIKE '" + modifiedNormalModelEvent.getId() + "';";

        executeStatementUpdate(query);
    }

    public void acceptEntry(int entryID) {

        String query = "UPDATE events_entries " +
                "SET " +
                "status = 'accepted'" +
                "WHERE entry_id = '" + entryID + "';";
        executeStatementUpdate(query);
    }

    public void discardEntry(int entryID) {

        String query = "UPDATE events_entries " +
                "SET " +
                "status = 'canceled'" +
                "WHERE entry_id = '" + entryID + "';";

        executeStatementUpdate(query);
    }

    private void executeStatementUpdate(String query) {
        try {
            Statement entryStm = DO.MySQLConnection().createStatement();
            entryStm.executeUpdate(query);
            DO.MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
