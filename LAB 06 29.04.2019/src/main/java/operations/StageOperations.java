package operations;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageOperations {

    public void changeSceneToUser(ActionEvent event) throws IOException {
        Parent EventViewParent = FXMLLoader.load(getClass().getResource("/eventView.fxml"));
        Scene eventScene = new Scene(EventViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(eventScene);
        window.setTitle("NormalModelEvent panel");
        window.show();
    }

    public void changeSceneToAdministrator(ActionEvent event) throws IOException {
        Parent AdministratorViewParent = FXMLLoader.load(getClass().getResource("/administratorView.fxml"));
        Scene administratorScene = new Scene(AdministratorViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(administratorScene);
        window.setTitle("Administration panel");
        window.show();
    }

    public void changeSceneToRegistrationComplete(ActionEvent event) throws IOException {
        Parent regCompleteViewParent = FXMLLoader.load(getClass().getResource("/registrationCompleteView.fxml"));
        Scene regCompleteScene = new Scene(regCompleteViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(regCompleteScene);
        window.setTitle("Registration complete");
        window.show();
    }

    public void changeSceneToLogin(ActionEvent event) throws IOException {
        Parent loginViewParent = FXMLLoader.load(getClass().getResource("/loginView.fxml"));
        Scene loginScene = new Scene(loginViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.setTitle("Login view");
        window.show();
    }


}
