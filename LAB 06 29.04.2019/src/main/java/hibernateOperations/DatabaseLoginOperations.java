package hibernateOperations;

import hibernateModel.User;

import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseLoginOperations {
    private User tmp_user = null;
    private EntityOperations EO = new EntityOperations();

    public User searchForExistingUser(String userLogin, String userPassword) {
        User existingUser = null;
        Query query = EO.myEntityManager().createQuery("SELECT u FROM hibernateModel.User as usr WHERE usr.login LIKE ? AND usr.password = ?");
        query.setParameter(0, userLogin);
        query.setParameter(1,userPassword);

//        System.out.println("query");
//        System.out.println(query.getSingleResult().toString());

//        System.out.println(query.getSingleResult());

//        tmp_user = (User) query.getSingleResult();

//        List<User> userlost = query.getResultList();
//
//
//        for (User u : userlost) {
//            System.out.println(u.getName() + " | " + u.getSurname());
//        }

//        System.out.println(tmp_user.getName() + " | " + tmp_user.getSurname());


//        System.out.println(query.getSingleResult());
        System.out.println(query.getFirstResult());
        System.out.println(query.getResultList());

        return existingUser;
    }

}
