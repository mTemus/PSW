package operations;

import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabaseOperations {

    private DatabaseOperations DO = new DatabaseOperations();

    public void changeUsersPassword(String usersLogin, String newPassword) {
        try {
            String Query = "UPDATE user " +
                    "SET " +
                    "password = '" + newPassword + "'" +
                    "WHERE login LIKE '" + usersLogin + "';";
            System.out.println(Query);

            Statement Stm = DO.MySQLConnection().createStatement();
            Stm.executeUpdate(Query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
