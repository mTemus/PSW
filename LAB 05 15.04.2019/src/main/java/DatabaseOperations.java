import model.User;
import java.sql.*;

public class DatabaseOperations {

    public DatabaseOperations() {
    }

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

    private User searchForExistingUser(String login, String password) {

        User existingUser = null;

        PreparedStatement findingStm = null;
        try {
            findingStm = MySQLConnection().prepareStatement("SELECT * FROM user WHERE login LIKE ? AND password LIKE ?");
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
                        userResultFind.getString("email"),
                        userResultFind.getString("date"));
                String permission = userResultFind.getString("permissions");


                if (permission.matches("administrator")) {
                    tmp_user.setPermissions(User.Permissions.ADMINISTRATOR);
                }

            } else {
                System.out.println("User login error.");
            }
            MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp_user;
    }

    private void addUserToDatabase(User userToAdd) {

        String Query = "INSERT INTO user " +
                "(id, " +
                "name, " +
                "surname, " +
                "login, " +
                "password, " +
                "email, " +
                "permissions, " +
                "registrationDate) " +

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
            Statement Stm = MySQLConnection().createStatement();
            Stm.executeUpdate(Query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
