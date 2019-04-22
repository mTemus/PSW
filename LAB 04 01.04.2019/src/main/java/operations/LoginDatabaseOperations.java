package operations;

import controller.LoginController;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDatabaseOperations {

    private DatabaseOperations DO = new DatabaseOperations();
//    private LoginController LC = new LoginController();

    public User searchForExistingUser(String login, String password) {

        User existingUser = null;

        PreparedStatement findingStm = null;
        try {
            findingStm = DO.MySQLConnection().prepareStatement("SELECT * FROM user WHERE login LIKE ? AND password LIKE ?");
            findingStm.setString(1, login);
            findingStm.setString(2, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        existingUser = doFindingQuery(findingStm);

        return existingUser;
    }

    private User doFindingQuery(PreparedStatement findStm) {
        User tmp_user = null;
        ResultSet userResultFind;

        try {
            userResultFind = findStm.executeQuery();
            if (userResultFind.next()) {
                tmp_user = new User(userResultFind.getLong("id"),
                        userResultFind.getString("name"),
                        userResultFind.getString("surname"),
                        userResultFind.getString("login"),
                        userResultFind.getString("password"),
                        userResultFind.getString("email"));
                String permission = userResultFind.getString("permissions");
                LoginController.setUserJoinDate(userResultFind.getString("date"));

                if (permission.matches("administrator")) {
                    tmp_user.setPermissions(User.Permissions.ADMINISTRATOR);
                }

            }
            DO.MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp_user;
    }

    public void addUserToDatabase(User userToAdd) {

        String Query = "INSERT INTO user " +
                "(id, " +
                "name, " +
                "surname, " +
                "login, " +
                "password, " +
                "email, " +
                "permissions, " +
                "date) " +

                "VALUES " +
                "(DEFAULT," +
                "'" + userToAdd.getName() + "', " +
                "'" + userToAdd.getSurname() + "', " +
                "'" + userToAdd.getLogin() + "', " +
                "'" + userToAdd.getPassword() + "', " +
                "'" + userToAdd.getEmail() + "', " +
                "'user', " +
                "(NOW())" + ");";
        try {
            Statement Stm = DO.MySQLConnection().createStatement();
            Stm.executeUpdate(Query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
