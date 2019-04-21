package Operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Event;
import model.EventEntry;
import model.User;

import java.sql.*;

public class DatabaseAdministratorOperations {

    DatabaseLoginOperations DLO = new DatabaseLoginOperations();

    private Connection MySQLConnection() {
        Connection MySQLConnection = null;

        try {
            MySQLConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/psw" +
                            "?useUnicode=true" +
                            "&useJDBCCompliantTimezoneShift=true" +
                            "&useLegacyDatetimeCode=false" +
                            "&serverTimezone=UTC",
                    "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return MySQLConnection;
    }

    public ObservableList<User> findAllUsers() {

        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            Statement userStatement = MySQLConnection().createStatement();
            ResultSet userResultSet = userStatement.executeQuery("select * from user");

            while (userResultSet.next()) {
                User u = new User(userResultSet.getLong("id"),
                        userResultSet.getString("name"),
                        userResultSet.getString("surname"),
                        userResultSet.getString("login"),
                        userResultSet.getString("password"),
                        userResultSet.getString("email"),
                        userResultSet.getString("date"));
                users.add(u);
            }
            MySQLConnection().close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<EventEntry> findAllEventEntries() {
        ObservableList<EventEntry> eventEntries = FXCollections.observableArrayList();

        try {
            Statement entryStatement = MySQLConnection().createStatement();
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
                EventEntry ee = new EventEntry(entryResultSet.getLong("entry_id"),
                        entryResultSet.getString("event_name"),
                        entryResultSet.getString("name"),
                        entryResultSet.getString("surname"),
                        entryResultSet.getString("participation_type"),
                        entryResultSet.getString("food_preferences"),
                        entryResultSet.getString("status"));
                eventEntries.add(ee);
            }
            return eventEntries;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findExistingUserToDelete(int userID) {
        User tmpUser = null;
        PreparedStatement userPrpStm = null;

        try {
            userPrpStm = MySQLConnection().prepareStatement("select * from user where id = ?");
            userPrpStm.setLong(1, userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tmpUser = DLO.doFindingQuery(userPrpStm);

        return tmpUser;
    }

    public void deleteUserFromDatabase(long userID) {
        String query = "DELETE FROM user WHERE id = " + userID;
        executeStatementUpdate(query);
    }

    private PreparedStatement findExistingEntry(String query) {
        PreparedStatement entryPrpStm = null;

        try {
            entryPrpStm = MySQLConnection().prepareStatement(query);
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

            if (entryResultFind.next()) {
                MySQLConnection().close();
                return true;
            } else return false;

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
            findingEventStm = MySQLConnection().prepareStatement("SELECT * FROM event WHERE id_event LIKE ?");
            findingEventStm.setLong(1, eventID);

            return findingEventStm;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Event doFindingEventQuery(PreparedStatement findStm) {
        ResultSet eventResultFind;
        Event tmp_event = null;

        try {
            eventResultFind = findStm.executeQuery();
            if (eventResultFind.next()) {
                tmp_event = new Event(eventResultFind.getLong("id_event"),
                        eventResultFind.getString("event_name"),
                        eventResultFind.getString("agenda"),
                        eventResultFind.getString("date"));
            }
            MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp_event;
    }

    public void deleteEvent(Long eventID) {

        String query = "DELETE FROM event WHERE id_event = " + eventID;
        executeStatementUpdate(query);
    }

    public void updateEvent(Event modifiedEvent) {

        String query = "UPDATE event " +
                "SET " +
                "event_name = '" + modifiedEvent.getName() + "'," +
                "agenda = '" + modifiedEvent.getAgenda() + "'," +
                "date = '" + modifiedEvent.getDate() + "'" +
                "WHERE id_event LIKE '" + modifiedEvent.getId() + "';";

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
            Statement entryStm = MySQLConnection().createStatement();
            entryStm.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
