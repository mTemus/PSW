package operations;

import model.NormalModelUser;

import java.sql.*;

public class DatabaseLoginOperations {

    private DatabaseOperations DO = new DatabaseOperations();

    private NormalModelUser tmp_NormalModel_user = null;

    public NormalModelUser searchForExistingUser(String login, String password) {

        NormalModelUser existingNormalModelUser = null;

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
            existingNormalModelUser = doFindingQuery(findingStm);
        }
        return existingNormalModelUser;
    }

    NormalModelUser doFindingQuery(PreparedStatement findStm) {
        ResultSet userResultFind;

        try {
            userResultFind = findStm.executeQuery();
            if (userResultFind.next()) {
                tmp_NormalModel_user = new NormalModelUser(userResultFind.getLong("id"),
                        userResultFind.getString("name"),
                        userResultFind.getString("surname"),
                        userResultFind.getString("login"),
                        userResultFind.getString("password"),
                        userResultFind.getString("email"),
                        userResultFind.getString("date"));
                String permission = userResultFind.getString("permissions");

                if (permission.matches("administrator")) {
                    tmp_NormalModel_user.setPermissions(NormalModelUser.Permissions.ADMINISTRATOR);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp_NormalModel_user;
    }

    public void addUserToDatabase(NormalModelUser normalModelUserToAdd) {

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
                "'" + normalModelUserToAdd.getName() + "', " +
                "'" + normalModelUserToAdd.getSurname() + "', " +
                "'" + normalModelUserToAdd.getLogin() + "', " +
                "'" + normalModelUserToAdd.getPassword() + "', " +
                "'" + normalModelUserToAdd.getEmail() + "', " +
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
