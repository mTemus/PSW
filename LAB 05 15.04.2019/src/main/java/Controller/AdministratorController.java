package Controller;

import Operations.DatabaseAdministratorOperations;
import Operations.DatabaseEventOperations;
import Operations.StageOperations;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Event;
import model.EventEntry;
import model.User;

public class AdministratorController {
    public TextField u_a_id_field;
    public TextField u_a_new_password_field;
    public TextField u_a_new_password_r_field;
    public Button u_a_del_button;
    public Button u_a_change_password_button;
    public CheckBox u_a_new_password_checkbox;
    public CheckBox u_a_new_password_r_checkbox;
    public Button administrator_logout_button;
    public TextArea u_a_alert_field;
    public TextArea ev_a_alert_field;
    public TextArea ev_a_add_agenda_field;
    public TextField ev_a_add_name_field;
    public TextField ev_a_add_date_field;
    public Button ev_a_add_event_button;
    public TextField ev_a_delete_ev_id_field;
    public Button ev_a_delete_event_button;
    public TextField ev_a_mod_ev_id_field;
    public Button ev_a_mod_load_event_button;
    public TextField ev_a_mod_name_field;
    public TextArea ev_a_mod_agenda_field;
    public TextField ev_a_mod_date_field;
    public Button ev_a_mod_save_event_button;
    public TextArea en_a_alert_field;
    public Button en_a_accept_button;
    public Button en_a_discard_button;
    public TextField en_a_entry_id_field;
    public TableView tbl_users;
    public TableColumn col_id_user;
    public TableColumn col_name_user;
    public TableColumn col_surname_user;
    public TableColumn col_login_user;
    public TableColumn col_password_user;
    public TableColumn col_email_user;
    public TableColumn col_date_user;
    public TableView tbl_events;
    public TableColumn col_id_event;
    public TableColumn col_name_event;
    public TableColumn col_agenda_event;
    public TableColumn col_date_event;
    public TableView tbl_entries;
    public TableColumn col_id_entries;
    public TableColumn col_event_name_entries;
    public TableColumn col_name_entries;
    public TableColumn col_surname_entries;
    public TableColumn col_participation_entries;
    public TableColumn col_food_entries;
    public TableColumn col_entry_status_entries;

    private DatabaseAdministratorOperations DAO = new DatabaseAdministratorOperations();
    private DatabaseEventOperations DEO = new DatabaseEventOperations();
    StageOperations SO = new StageOperations();


    public void deleteUser(ActionEvent event) {
        String user_Id = u_a_id_field.getText();
        Integer userId = Integer.parseInt(user_Id);
        User existingUserToDelete = DAO.findExistingUserToDelete(userId);

        if (existingUserToDelete != null) {
            DAO.deleteUserFromDatabase(userId);
            u_a_alert_field.setText("User deleted succesfully.");
            setUsersTable();
        } else
            u_a_alert_field.setText("There is no user with this ID. Try again.");
    }


    public void showPassword(ActionEvent event) {
    }

    public void showPasswordR(ActionEvent event) {
    }

    public void administratorLogout(ActionEvent event) {
    }

    public void addEvent(ActionEvent event) {
    }

    public void loadEventToModify(ActionEvent event) {
    }

    public void saveModifiedEvent(ActionEvent event) {
    }

    public void acceptEntry(ActionEvent event) {
    }

    public void discardEntry(ActionEvent event) {
    }

    public void initialize() {

        setUsersTable();
        setEventsTable();
        setEntriesTable();
    }

    private void addDataToUserTable(ObservableList<User> usersList) {
        col_id_user.setCellValueFactory(new PropertyValueFactory<User, Long>("id"));
        col_name_user.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        col_surname_user.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        col_login_user.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        col_password_user.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        col_email_user.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_date_user.setCellValueFactory(new PropertyValueFactory<User, String>("date"));
        tbl_users.setItems(usersList);
    }

    private void addDataToEventTable(ObservableList<Event> eventsList) {
        col_id_event.setCellValueFactory(new PropertyValueFactory<Event, Long>("id"));
        col_name_event.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        col_agenda_event.setCellValueFactory(new PropertyValueFactory<Event, String>("agenda"));
        col_date_event.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        tbl_events.setItems(eventsList);
    }

    private void addDataToEntryTable(ObservableList<EventEntry> entriesList) {
        col_id_entries.setCellValueFactory(new PropertyValueFactory<EventEntry, Long>("id"));
        col_event_name_entries.setCellValueFactory(new PropertyValueFactory<EventEntry, String>("eventName"));
        col_name_entries.setCellValueFactory(new PropertyValueFactory<EventEntry, String>("userName"));
        col_surname_entries.setCellValueFactory(new PropertyValueFactory<EventEntry, String>("userSurname"));
        col_participation_entries.setCellValueFactory(new PropertyValueFactory<EventEntry, String>("participationType"));
        col_food_entries.setCellValueFactory(new PropertyValueFactory<EventEntry, String>("foodPreferences"));
        col_entry_status_entries.setCellValueFactory(new PropertyValueFactory<EventEntry, String>("entryStatus"));
        tbl_entries.setItems(entriesList);
    }


    private void setUsersTable() {
        ObservableList<User> users = DAO.findAllUsers();
        addDataToUserTable(users);
    }

    private void setEventsTable() {
        ObservableList<Event> events = DEO.findAllEvents();
        addDataToEventTable(events);
    }

    private void setEntriesTable() {
        ObservableList<EventEntry> entries = DAO.findAllEventEntries();
        addDataToEntryTable(entries);
    }

}
