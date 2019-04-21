package Operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Event;
import model.User;

import java.sql.*;

public class DatabaseEventOperations {

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

    public ObservableList<Event> findAllEvents() {
        ObservableList<Event> events = FXCollections.observableArrayList();

        try {
            Statement eventStatement = MySQLConnection().createStatement();
            ResultSet eventResultSet = eventStatement.executeQuery("select * from event");

            while (eventResultSet.next()) {
                Event e = new Event(eventResultSet.getLong("id_event"),
                        eventResultSet.getString("event_name"),
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
            ResultSet eventNamesResultSet = eventNamesStatement.executeQuery("select event_name from event");

            while (eventNamesResultSet.next()) {
                eventNames.add(eventNamesResultSet.getString("event_name"));

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
        ResultSet entryRS;

        PreparedStatement findingEntryStm = null;
        try {
            findingEntryStm = MySQLConnection().prepareStatement("SELECT * FROM events_entries WHERE event_id LIKE ? AND user_id LIKE ?");
            findingEntryStm.setLong(1, eventID);
            findingEntryStm.setLong(2, userID);

            entryRS = findingEntryStm.executeQuery();

            if (entryRS.next()){
                status = entryRS.getString("status");
                return status;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
