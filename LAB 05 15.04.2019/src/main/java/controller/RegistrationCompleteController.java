package controller;

import operations.StageOperations;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class RegistrationCompleteController {
    public Button logout_button;
    public Button register_again_button;
    public TextArea registration_text_area;

    private static StageOperations SO = new StageOperations();

    public void initialize() {
        registration_text_area.setText("Registered successfully on " + EventController.getChosenEvent().getName() + ", " +
                "your entry status is: \"waiting\", " +
                "we will send you an email, when it will be accepted or rejected.");
    }

    public void userRegisterAgain(ActionEvent event) throws IOException {
        EventController.setChosenEvent(null);
        SO.changeSceneToUser(event);
    }

    public void userLogout(ActionEvent event) throws IOException {
        EventController.setLoggedUser(null);
        SO.changeSceneToLogin(event);
    }

}
