package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.Properties;

public class EventController {


    /* ======================================== FX Fields etc. ======================================== */
    public TextField register_field_name;
    public TextField register_field_surname;
    public TextField register_field_login;
    public TextField register_field_email;
    public TextField register_field_password_r_un;
    public TextField register_field_password_un;
    public TextField uv_old_password;
    public TextField uv_new_password;
    public TextField uv_new_password_r;
    public TextField uv_field_name;
    public TextField uv_field_surname;
    public TextField uv_field_email;
    public TextField uv_field_join_date;
    public TextField uv_password_change_alert;
    public TextField login_alert_field;
    public TextField login_text_field;
    public TextField password_text_field_u;
    public PasswordField password_text_field;
    public PasswordField register_field_password;
    public PasswordField register_field_password_r;
    public TextArea register_text_area;
    public CheckBox password_checkbox;
    public CheckBox register_password_checkbox;
    public CheckBox register_password_r_checkbox;
    public Button register_button;
    public Button user_logout_button;
    public Button login_button;
    public Button administrator_logout_button;
    public Button uv_password_change_button;

    @FXML
    private TableView<User> tbl_users;
    @FXML
    private TableColumn<User, Long> col_id;
    @FXML
    private TableColumn<User, String> col_name;
    @FXML
    private TableColumn<User, String> col_surname;
    @FXML
    private TableColumn<User, String> col_login;
    @FXML
    private TableColumn<User, String> col_password;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_date;

    @FXML
    private MenuItem mi_exit;

    /* ======================================== FX Event Methods ======================================== */
    public void login_check_user(ActionEvent event) {
        String login = login_text_field.getText();
        String password = password_text_field.getText();

        loggedUser = searchForExistingUser(login, password);

        if (loginAttempts < 3) {
            if (loggedUser != null) {
                if (loggedUser.getPermissions() == User.Permissions.USER) {
                    login_alert_field.setPromptText("Successfully logged in. You can now access your data in \"User View\" tab.");
                    loginAttempts = 0;
                    someoneIsLogged = true;
                    userLoggedIn(loggedUser);
                } else if (loggedUser.getPermissions() == User.Permissions.ADMINISTRATOR) {
                    login_alert_field.setPromptText("Successfully logged in. You can now access data in \"Administrator View\" tab.");
                    loginAttempts = 0;
                    someoneIsLogged = true;
                    administratorLoggedIn();
                }
            }
        } else {
            login_alert_field.setText("You failed 3 times, logging in is denied.");
        }
    }

    public void register_check_user(ActionEvent event) {

        String name = register_field_name.getText();
        String surname = register_field_surname.getText();
        String login = register_field_login.getText();
        String password = register_field_password.getText();
        String password_r = register_field_password_r.getText();
        String email = register_field_email.getText();

        User tmp_user = searchForExistingUser(login, password);

        if (!someoneIsLogged) {
            if (password.contentEquals(password_r)) {
                if (tmp_user == null) {
                    tmp_user = new User(name, surname, login, password, email);
                    addUserToDatabase(tmp_user);
                    register_text_area.setPromptText("Registration of user " + tmp_user.getName() + " " + tmp_user.getSurname() + " went successfully, you can log in now.");
                    registrationComplete();

                    sendEmailAfterRegistering(tmp_user.getEmail(), tmp_user.getName(), tmp_user.getSurname());

                } else
                    register_text_area.setPromptText("User already exists. Please log in.");
            } else
                register_text_area.setPromptText("Register failed, passwords don't match. Please repeat your password correctly.");
        } else
            register_text_area.setPromptText("You are logged in. Please logout first.");
    }

    public void user_logout(ActionEvent event) {

        if (someoneIsLogged && loggedUser.getPermissions() == User.Permissions.USER) {
            someoneIsLogged = false;
            uv_field_name.setText("");
            uv_field_surname.setText("");
            uv_field_email.setText("");
            uv_field_join_date.setText("");
            loggedUser = null;
            userJoinDate = null;
            uv_password_change_alert.setPromptText("Logged off successfully.");
        } else
            uv_password_change_alert.setPromptText("You are not logged in!");
    }

    public void administrator_logout(ActionEvent event) {

        if (someoneIsLogged && loggedUser.getPermissions() == User.Permissions.ADMINISTRATOR) {
            users.clear();
            someoneIsLogged = false;
            login_alert_field.setPromptText("Logged off successfully.");
        }
    }

    public void uv_change_password(ActionEvent event) {
        if (someoneIsLogged && loggedUser.getPermissions() == User.Permissions.USER) {
            if (uv_old_password.getText().contentEquals(loggedUser.getPassword())) {
                if (uv_new_password.getText().contentEquals(uv_new_password_r.getText())) {
                    changeUsersPassword(loggedUser.getLogin(), uv_new_password.getText());
                    uv_password_change_alert.setPromptText("Password changed successfully.");
                    uv_old_password.setText("");
                    uv_new_password.setText("");
                    uv_new_password_r.setText("");
                } else
                    uv_password_change_alert.setPromptText("New passwords don't match. Write it correctly and try again.");
            } else
                uv_password_change_alert.setPromptText("Old password is wrong. Write it correctly and try again.");
        } else
            uv_password_change_alert.setPromptText("You are not logged in!");

    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }


    public void showPassword(ActionEvent event) {
        password_text_field_u.managedProperty().bind(password_checkbox.selectedProperty());
        password_text_field_u.visibleProperty().bind(password_checkbox.selectedProperty());
        password_text_field.managedProperty().bind(password_checkbox.selectedProperty().not());
        password_text_field.visibleProperty().bind(password_checkbox.selectedProperty().not());
        password_text_field_u.textProperty().bindBidirectional(password_text_field.textProperty());
    }

    public void register_show_password(ActionEvent event) {
        register_field_password_un.managedProperty().bind(register_password_checkbox.selectedProperty());
        register_field_password_un.visibleProperty().bind(register_password_checkbox.selectedProperty());
        register_field_password.managedProperty().bind(register_password_checkbox.selectedProperty().not());
        register_field_password.visibleProperty().bind(register_password_checkbox.selectedProperty().not());
        register_field_password_un.textProperty().bindBidirectional(register_field_password.textProperty());
    }

    public void register_show_password_r(ActionEvent event) {
        register_field_password_r_un.managedProperty().bind(register_password_r_checkbox.selectedProperty());
        register_field_password_r_un.visibleProperty().bind(register_password_r_checkbox.selectedProperty());
        register_field_password_r.managedProperty().bind(register_password_r_checkbox.selectedProperty().not());
        register_field_password_r.visibleProperty().bind(register_password_r_checkbox.selectedProperty().not());
        register_field_password_r_un.textProperty().bindBidirectional(register_field_password_r.textProperty());
    }



    /* ======================================== Other Fields ======================================== */

    private int loginAttempts = 0;
    private User loggedUser = null;
    private String userJoinDate = null;

    private boolean someoneIsLogged = false;

    private ObservableList<User> users = FXCollections.observableArrayList();

    /* ======================================== Other Methods ======================================== */

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
                        userResultFind.getString("email"));
                String permission = userResultFind.getString("permissions");
                userJoinDate = userResultFind.getString("registrationDate");

                if (permission.matches("administrator")) {
                    tmp_user.setPermissions(User.Permissions.ADMINISTRATOR);
                }

            } else {
                login_alert_field.setPromptText("Incorrect user or password! Try again.");
                loginAttempts++;
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

    private void registrationComplete() {
        register_field_name.setText("");
        register_field_surname.setText("");
        register_field_login.setText("");
        register_field_password.setText("");
        register_field_password_r.setText("");
        register_field_email.setText("");

    }

    private void userLoggedIn(User user) {
        uv_field_name.setText(user.getName());
        uv_field_surname.setText(user.getSurname());
        uv_field_email.setText(user.getEmail());
        uv_field_join_date.setText(userJoinDate);
        login_text_field.setText("");
        password_text_field.setText("");
    }

    private void administratorLoggedIn() {
        findAllUsers();
    }

    private void addDataToTable(ObservableList<User> usersList) {
        col_id.setCellValueFactory(new PropertyValueFactory<User, Long>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        col_login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_date.setCellValueFactory(new PropertyValueFactory<User, String>("registrationDate"));
        tbl_users.setItems(usersList);
    }

    private void findAllUsers() {
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
                addDataToTable(users);
            }

            MySQLConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void changeUsersPassword(String usersLogin, String newPassword) {
        try {
            String Query = "UPDATE user " +
                    "SET " +
                    "password = '" + newPassword + "'" +
                    "WHERE login LIKE '" + usersLogin + "';";
            System.out.println(Query);

            Statement Stm = MySQLConnection().createStatement();
            Stm.executeUpdate(Query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sendEmailAfterRegistering(String userEmail, String userName, String userSurname) {

        final String username = "TemusOrigami@gmail.com";
        final String password = "ZP()PS\\/\\/Utp@";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("TemusOrigami@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEmail));
            message.setSubject("Registration Complete.");
            message.setText("Dear " + userName + " " + userSurname + ","
                    + "\n\n Registration wet successfully!");

            Transport.send(message);

            System.out.println("Message sent.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}



