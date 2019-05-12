package controller;

import operations.EmailOperations;
import operations.StageOperations;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController {

    private int loginAttempts = 0;
    private static NormalModelUser loggedNormalModelUser;
    private boolean someoneIsLogged = false;

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

    private DatabaseLoginOperations DLO = new DatabaseLoginOperations();
    private EmailOperations EO = new EmailOperations();
    private StageOperations SO = new StageOperations();

    public void login_check_user(ActionEvent event) throws IOException {
        String login = login_text_field.getText();
        String password = password_text_field.getText();

        loggedNormalModelUser = DLO.searchForExistingUser(login, password);

        if (loginAttempts < 3) {
            if (loggedNormalModelUser != null) {
                if (loggedNormalModelUser.getPermissions() == NormalModelUser.Permissions.USER) {
                    login_alert_field.setPromptText("Successfully logged in.");
                    loginAttempts = 0;
                    someoneIsLogged = true;
                    SO.changeSceneToUser(event);


                } else if (loggedNormalModelUser.getPermissions() == NormalModelUser.Permissions.ADMINISTRATOR) {
                    login_alert_field.setPromptText("Successfully logged in as an Administrator.");
                    loginAttempts = 0;
                    someoneIsLogged = true;
                    SO.changeSceneToAdministrator(event);
                }
            } else {
                loginAttempts++;
                login_alert_field.setPromptText("Invalid user or password, please try again.");
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

        NormalModelUser tmp_NormalModel_user = DLO.searchForExistingUser(login, password);

        if (!someoneIsLogged) {
            if (password.contentEquals(password_r)) {
                if (tmp_NormalModel_user == null) {
                    tmp_NormalModel_user = new NormalModelUser(name, surname, login, password, email);
                    DLO.addUserToDatabase(tmp_NormalModel_user);
                    register_text_area.setPromptText("Registration of NormalModelUser " + tmp_NormalModel_user.getName() + " " + tmp_NormalModel_user.getSurname() + " went successfully, you can log in now.");
                    registrationComplete();

                    EO.sendEmailAfterRegistering(tmp_NormalModel_user.getEmail(), tmp_NormalModel_user.getName(), tmp_NormalModel_user.getSurname());

                } else
                    register_text_area.setPromptText("NormalModelUser already exists. Please log in.");
            } else
                register_text_area.setPromptText("Register failed, passwords don't match. Please repeat your password correctly.");
        } else
            register_text_area.setPromptText("You are logged in. Please logout first.");
    }

    public void showPassword(ActionEvent event) {
        bindShowPassword(password_text_field_u, password_checkbox, password_text_field);
    }

    public void register_show_password(ActionEvent event) {
        bindShowPassword(register_field_password_un, register_password_checkbox, register_field_password);
    }

    public void register_show_password_r(ActionEvent event) {
        bindShowPassword(register_field_password_r_un, register_password_r_checkbox, register_field_password_r);
    }

    private static void bindShowPassword(TextField unmasked_password, CheckBox password_checkbox, PasswordField masked_password) {
        unmasked_password.managedProperty().bind(password_checkbox.selectedProperty());
        unmasked_password.visibleProperty().bind(password_checkbox.selectedProperty());
        masked_password.managedProperty().bind(password_checkbox.selectedProperty().not());
        masked_password.visibleProperty().bind(password_checkbox.selectedProperty().not());
        unmasked_password.textProperty().bindBidirectional(masked_password.textProperty());
    }

    private void registrationComplete() {
        register_field_name.setText("");
        register_field_surname.setText("");
        register_field_login.setText("");
        register_field_password.setText("");
        register_field_password_r.setText("");
        register_field_email.setText("");
    }

    static NormalModelUser getLoggedNormalModelUser() {
        return loggedNormalModelUser;
    }

    void setLoggedUser(NormalModelUser loggedNormalModelUser) {
        LoginController.loggedNormalModelUser = loggedNormalModelUser;
    }
}
