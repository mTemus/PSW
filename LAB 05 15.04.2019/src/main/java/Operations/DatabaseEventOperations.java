package Operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Event;
import model.User;

import java.sql.*;

public class DatabaseEventOperations {

    public Connection MySQLConnection() {
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

    public ObservableList<Event> findAllEvents() {
        ObservableList<Event> events = FXCollections.observableArrayList();

        try {
            Statement eventStatement = MySQLConnection().createStatement();
            ResultSet eventResultSet = eventStatement.executeQuery("select * from event");

            while (eventResultSet.next()) {
                Event e = new Event(eventResultSet.getLong("id_event"),
                        eventResultSet.getString("name"),
                        eventResultSet.getString("agenda"),
                        eventResultSet.getString("date")
                );
                events.add(e);
            }
            MySQLConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public ObservableList<String> findAllEventNames() {
        ObservableList<String> eventNames = FXCollections.observableArrayList();

        try {
            Statement eventNamesStatement = MySQLConnection().createStatement();
            ResultSet eventNamesResultSet = eventNamesStatement.executeQuery("select name from event");

            while (eventNamesResultSet.next()) {
                eventNames.add(eventNamesResultSet.getString("name"));

            }
            MySQLConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventNames;
    }

    public boolean registerUserOnEvent(User user, Event event) {

        try {
            String Query = "INSERT INTO events_entries " +
                    "(entry_id, " +
                    "event_id, " +
                    "user_id, " +
                    "participation_type, " +
                    "food_preferences, " +
                    "status) " +
                    "VALUES " +
                    "(default, " +
                    "'" + event.getId() + "', " +
                    "'" + user.getId() + "', " +
                    "'" + user.getParticipationType() + "', " +
                    "'" + user.getFoodPreferences() + "', " +
                    "default);";

            Statement Stm = MySQLConnection().createStatement();
            Stm.executeUpdate(Query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String checkIfEntryExists(Long userID, Long eventID) {
        String status = null;





        return status;
    }


}
