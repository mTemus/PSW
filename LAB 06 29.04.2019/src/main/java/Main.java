import hibernateOperations.DatabaseAdministratorOperations;
import hibernateOperations.DatabaseLoginOperations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loginViewParent = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        Scene loginScene = new Scene(loginViewParent);
        primaryStage.setTitle("Login view");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        DatabaseAdministratorOperations DAO = new DatabaseAdministratorOperations();

        DAO.changeUserPassword(1L, "1234");

        launch(args);
    }
}
