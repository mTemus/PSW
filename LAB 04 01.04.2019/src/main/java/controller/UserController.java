package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import operations.StageOperations;
import operations.UserDatabaseOperations;

import java.io.IOException;

public class UserController {

    public Button user_logout_button;
    public TextField uv_old_password;
    public TextField uv_new_password;
    public TextField uv_new_password_r;
    public Button uv_password_change_button;
    public TextField uv_field_name;
    public TextField uv_field_surname;
    public TextField uv_field_email;
    public TextField uv_field_join_date;
    public TextField uv_password_change_alert;

    private LoginController LC = new LoginController();
    private UserDatabaseOperations UDO = new UserDatabaseOperations();
    private StageOperations SO = new StageOperations();
    private User userLoggedIn = null;

    public void initialize() {
        userLoggedIn = LC.getLoggedUser();
        setUserView(userLoggedIn);
    }

    private void setUserView(User user) {
        uv_field_name.setText(user.getName());
        uv_field_surname.setText(user.getSurname());
        uv_field_email.setText(user.getEmail());
        uv_field_join_date.setText(LC.getUserJoinDate());
    }

    public void userLogout(ActionEvent event) throws IOException {
        if (LC.isSomeoneIsLogged() && LC.getLoggedUser().getPermissions() == User.Permissions.USER) {
            LC.setSomeoneIsLogged(false);
            uv_field_name.setText("");
            uv_field_surname.setText("");
            uv_field_email.setText("");
            uv_field_join_date.setText("");
            LC.setLoggedUser(null);
            LC.setUserJoinDate(null);
            SO.changeSceneToLogin(event);
        } else
            uv_password_change_alert.setPromptText("You are not logged in!");
    }

    public void userChangePassword(ActionEvent event) {
        if (LC.isSomeoneIsLogged() && LC.getLoggedUser().getPermissions() == User.Permissions.USER) {
            if (uv_old_password.getText().contentEquals(LC.getLoggedUser().getPassword())) {
                if (uv_new_password.getText().contentEquals(uv_new_password_r.getText())) {
                    UDO.changeUsersPassword(LC.getLoggedUser().getLogin(), uv_new_password.getText());
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
}
