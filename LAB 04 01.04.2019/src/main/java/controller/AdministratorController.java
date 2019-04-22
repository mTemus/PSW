package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import operations.AdministratorDatabaseOperations;
import operations.StageOperations;

import java.io.IOException;

public class AdministratorController {

    public TableView tbl_users;
    public TableColumn col_id;
    public TableColumn col_name;
    public TableColumn col_surname;
    public TableColumn col_login;
    public TableColumn col_password;
    public TableColumn col_email;
    public TableColumn col_date;
    public Button administrator_logout_button;

    private AdministratorDatabaseOperations ADO = new AdministratorDatabaseOperations();
    private StageOperations SO = new StageOperations();
    private LoginController LC = new LoginController();

    public void initialize() {
        ObservableList<User> users = ADO.findAllUsers();

        addDataToTable(users);
    }

    public void administratorLogout(ActionEvent event) throws IOException {
            LC.setSomeoneIsLogged(false);
            LC.setLoggedUser(null);
            LC.setUserJoinDate(null);
            SO.changeSceneToLogin(event);
    }

    private void addDataToTable(ObservableList<User> usersList) {
        col_id.setCellValueFactory(new PropertyValueFactory<User, Long>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        col_login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        col_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_date.setCellValueFactory(new PropertyValueFactory<User, String>("date"));
        tbl_users.setItems(usersList);
    }

}
