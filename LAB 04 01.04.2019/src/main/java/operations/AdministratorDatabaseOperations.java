package operations;

import controller.AdministratorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdministratorDatabaseOperations {

    private DatabaseOperations DO = new DatabaseOperations();


    public ObservableList<User> findAllUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            Statement myStatement = DO.MySQLConnection().createStatement();
            ResultSet myResultSet = myStatement.executeQuery("select * from user");

            while (myResultSet.next()) {
                User u = new User(myResultSet.getLong("id"),
                        myResultSet.getString("name"),
                        myResultSet.getString("surname"),
                        myResultSet.getString("login"),
                        myResultSet.getString("password"),
                        myResultSet.getString("email"),
                        myResultSet.getString("date"));
                users.add(u);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
