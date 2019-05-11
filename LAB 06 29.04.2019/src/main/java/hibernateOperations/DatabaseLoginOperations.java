package hibernateOperations;

import hibernateModel.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseLoginOperations {
    private User tmp_user = null;


    public User searchForExistingUser(String userLogin, String userPassword) {
        User existingUser = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Check MySQL database version
//            String sql = "SELECT * FROM user WHERE login LIKE " + "'" + userLogin + "'" + " AND password LIKE " + "'" + userPassword + "'";

//            String result = (String) session.createNativeQuery(sql).getSingleResult();
//            System.out.println(result);


            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT usr FROM User ");
//            query.setString("uLogin", userLogin);
//            query.setString("uPassword", userPassword);


            session.getTransaction().commit();

            System.out.println(query.toString());

            User uu = (User) query.uniqueResult();
//            User u = (User) query.getResultList();


            System.out.println(uu.getName());
            System.out.println(uu.getSurname());
            System.out.println("--------------");
//            System.out.println(u.getName());
//            System.out.println(u.getSurname());

        } catch (HibernateException e) {
            e.printStackTrace();
        }


//
//
//        System.out.println(Arrays.toString(query.getNamedParameters()));
//        System.out.println(query.getQueryString());
//        System.out.println(Arrays.toString(query.getReturnAliases()));
//        System.out.println(Arrays.toString(query.getReturnTypes()));
//
////        System.out.println("query");
////        System.out.println(query.getSingleResult().toString());
//
////        System.out.println(query.getSingleResult());
//
////        tmp_user = (User) query.getSingleResult();
//
////        List<User> userlost = query.getResultList();
////
////
////        for (User u : userlost) {
////            System.out.println(u.getName() + " | " + u.getSurname());
////        }
//
////        System.out.println(tmp_user.getName() + " | " + tmp_user.getSurname());
//
//
////        System.out.println(query.getSingleResult());
//
//
        return existingUser;
    }

    public void updateUserPassword(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("UPDATE User u SET u.password=:password WHERE u.login=:login");
        query.setString("password", password);
        query.setString("login", login);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }


}
