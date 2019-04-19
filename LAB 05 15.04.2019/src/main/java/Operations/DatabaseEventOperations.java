package Operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Event;

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

}
