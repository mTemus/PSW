package Operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public User findExistingUserToDelete(Integer userID) {
        User tmpUser = null;
        PreparedStatement userPrpStm = null;

        try {
            userPrpStm = MySQLConnection().prepareStatement("select * from user where id = ?");
            userPrpStm.setLong(1, userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tmpUser = DLO.doFindingQuery(userPrpStm);

        if (tmpUser != null)
            return tmpUser;
        else
            return null;
    }

    public void deleteUserFromDatabase(long userID) {

        try {
            String Query = "DELETE FROM user WHERE id = " + userID;

            Statement Stm = MySQLConnection().createStatement();
            Stm.executeUpdate(Query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
