package operations;

import model.User;

import java.sql.*;

public class DatabaseLoginOperations {

    private DatabaseOperations DO = new DatabaseOperations();

    private User tmp_user = null;

    public User searchForExistingUser(String login, String password) {

        User existingUser = null;

        PreparedStatement findingStm = null;
        try {
            findingStm = DO.MySQLConnection().prepareStatement("SELECT * FROM user WHERE login LIKE ? AND password LIKE ?");
            findingStm.setString(1, login);
            findingStm.setString(2, password);
            DO.MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (findingStm != null) {
            existingUser = doFindingQuery(findingStm);
        }
        return existingUser;
    }

    User doFindingQuery(PreparedStatement findStm) {
        ResultSet userResultFind;

        try {
            userResultFind = findStm.executeQuery();
            if (userResultFind.next()) {
                tmp_user = new User(userResultFind.getLong("id"),
                        userResultFind.getString("name"),
                        userResultFind.getString("surname"),
                        userResultFind.getString("login"),
                        userResultFind.getString("password"),
                        userResultFind.getString("email"),
                        userResultFind.getString("date"));
                String permission = userResultFind.getString("permissions");

                if (permission.matches("administrator")) {
                    tmp_user.setPermissions(User.Permissions.ADMINISTRATOR);
                }
            }
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
            DO.MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
