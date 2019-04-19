package Operations;

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
        Stage winDLOw = (Stage) ((Node) event.getSource()).getScene().getWindow();
        winDLOw.setScene(eventScene);
        winDLOw.setTitle("Event panel");
        winDLOw.show();

    }

    public void changeSceneToAdministrator(ActionEvent event) throws IOException {
        Parent AdministratorViewParent = FXMLLoader.load(getClass().getResource("/administratorView.fxml"));
        Scene administratorScene = new Scene(AdministratorViewParent);
        Stage winDLOw = (Stage) ((Node) event.getSource()).getScene().getWindow();
        winDLOw.setScene(administratorScene);
        winDLOw.setTitle("Administration panel");
        winDLOw.show();
    }

    public void changeSceneToRegistrationComplete(ActionEvent event) throws IOException {
        Parent regCompleteViewParent = FXMLLoader.load(getClass().getResource("/registrationCompleteView.fxml"));
        Scene regCompleteScene = new Scene(regCompleteViewParent);
        Stage winDLOw = (Stage) ((Node) event.getSource()).getScene().getWindow();
        winDLOw.setScene(regCompleteScene);
        winDLOw.setTitle("Registration complete");
        winDLOw.show();
    }


}
