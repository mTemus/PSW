import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene loginScene;
    Scene administratorScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent loginViewParent = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        loginScene = new Scene(loginViewParent);

        window.setTitle("Login view");
        window.setScene(loginScene);
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
