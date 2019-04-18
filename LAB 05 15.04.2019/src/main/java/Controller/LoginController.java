package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;

public class LoginController {

    private int loginAttempts = 0;
    private User loggedUser = null;
    private String userJoinDate = null;

    private boolean someoneIsLogged = false;

    private ObservableList<User> users = FXCollections.observableArrayList();

    public TextField password_text_field_u;
    public TextField login_text_field;
    public TextField login_alert_field;
    public PasswordField password_text_field;
    public CheckBox password_checkbox;
    public Button login_button;
    public TextField register_field_password_r_un;
    public TextField register_field_password_un;
    public TextField register_field_name;
    public TextField register_field_surname;
    public TextField register_field_login;
    public TextField register_field_email;
    public Button register_button;
    public TextArea register_text_area;
    public CheckBox register_password_checkbox;
    public CheckBox register_password_r_checkbox;
    public PasswordField register_field_password;
    public PasswordField register_field_password_r;



//    public void login_check_user(ActionEvent event) {
//        String login = login_text_field.getText();
//        String password = password_text_field.getText();
//
//        loggedUser = searchForExistingUser(login, password);
//
//        if (loginAttempts < 3) {
//            if (loggedUser != null) {
//                if (loggedUser.getPermissions() == User.Permissions.USER) {
//                    login_alert_field.setPromptText("Successfully logged in.");
//                    loginAttempts = 0;
//                    someoneIsLogged = true;
//                } else if (loggedUser.getPermissions() == User.Permissions.ADMINISTRATOR) {
//                    login_alert_field.setPromptText("Successfully logged in. You can now access data in \"Administrator View\" tab.");
//                    loginAttempts = 0;
//                    someoneIsLogged = true;
//                }
//            }
//        } else {
//            login_alert_field.setText("You failed 3 times, logging in is denied.");
//        }
//    }
//
//    public void register_check_user(ActionEvent event) {
//
//        String name = register_field_name.getText();
//        String surname = register_field_surname.getText();
//        String login = register_field_login.getText();
//        String password = register_field_password.getText();
//        String password_r = register_field_password_r.getText();
//        String email = register_field_email.getText();
//
//        User tmp_user = searchForExistingUser(login, password);
//
//        if (!someoneIsLogged) {
//            if (password.contentEquals(password_r)) {
//                if (tmp_user == null) {
//                    tmp_user = new User(name, surname, login, password, email);
//                    addUserToDatabase(tmp_user);
//                    register_text_area.setPromptText("Registration of User " + tmp_user.getName() + " " + tmp_user.getSurname() + " went successfully, you can log in now.");
//                    registrationComplete();
//
//                    sendEmailAfterRegistering(tmp_user.getEmail(), tmp_user.getName(), tmp_user.getSurname());
//
//                } else
//                    register_text_area.setPromptText("User already exists. Please log in.");
//            } else
//                register_text_area.setPromptText("Register failed, passwords don't match. Please repeat your password correctly.");
//        } else
//            register_text_area.setPromptText("You are logged in. Please logout first.");
//    }


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









}