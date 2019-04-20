package Operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.*;

public class DatabaseAdministratorOperations {

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
            Statement myStatement = MySQLConnection().createStatement();
            ResultSet myResultSet = myStatement.executeQuery("select * from user");

            while (myResultSet.next()) {
                User u = new User(myResultSet.getLong("id"),
                        myResultSet.getString("name"),
                        myResultSet.getString("surname"),
                        myResultSet.getString("login"),
                        myResultSet.getString("password"),
                        myResultSet.getString("email"),
                        myResultSet.getString("registrationDate"));
                users.add(u);
            }
            MySQLConnection().close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


}
