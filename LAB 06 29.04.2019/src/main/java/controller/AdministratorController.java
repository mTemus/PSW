package controller;

import model.TableEventEntry;
import operations.StageOperations;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.regex.Pattern;

public class AdministratorController {
    public TextField u_a_id_field;
    public TextField u_a_new_password_field;
    public TextField u_a_new_password_r_field;
    public Button u_a_del_button;
    public Button u_a_change_password_button;
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
    private StageOperations SO = new StageOperations();
    private LoginController LC = new LoginController();
    private NormalModelEvent normalModelEventToModify = null;

    public void deleteUser(ActionEvent event) {

        if (isIdSetted()) {
            String user_Id = u_a_id_field.getText();
            int userId = Integer.parseInt(user_Id);
            NormalModelUser existingNormalModelUserToDelete = DAO.findExistingUserToDelete(userId);

            if (existingNormalModelUserToDelete != null) {
                DAO.deleteUserFromDatabase(userId);
                u_a_alert_field.setText("NormalModelUser deleted succesfully.");
                setUsersTable();
                u_a_id_field.setText("");

                if (DAO.findExistingEntryToDelete(userId)) {
                    DAO.deleteEntryFromDatabase(userId);
                    u_a_alert_field.setText("NormalModelUser deleted succesfully. NormalModelUser's event entries was also deleted.");
                    setEntriesTable();
                }

            } else
                u_a_alert_field.setText("There is no user with this ID. Try again.");
        } else u_a_alert_field.setText("Please specify users's id.");
    }

    public void administratorLogout(ActionEvent event) throws IOException {
        SO.changeSceneToLogin(event);
        LC.setLoggedUser(null);
    }

    public void changeUsersPassword(ActionEvent event) {
        if (isIdSetted()) {
            String user_Id = u_a_id_field.getText();
            int userId = Integer.parseInt(user_Id);

            if (u_a_new_password_field.getText().equals(u_a_new_password_r_field.getText())) {
                String newPassword = u_a_new_password_field.getText();
                NormalModelUser existingNormalModelUserToPasswordChange = DAO.findExistingUserToDelete(userId);

                if (existingNormalModelUserToPasswordChange != null) {
                    DAO.changeUserPassword(userId, newPassword);
                    u_a_alert_field.setText("Password changed successfully.");
                    u_a_id_field.setText("");
                    setUsersTable();
                } else u_a_alert_field.setText("NormalModelUser with this id don't exists.");
            } else u_a_alert_field.setText("Passwords dont match, please try again.");
        } else u_a_alert_field.setText("Please specify users's id.");
    }

    public void addEvent(ActionEvent event) {

        if (!isEventNameSetted() || !isEventAgendaSetted() || !isEventDateSetted()) {
            ev_a_alert_field.setText("Please set values of the event properly.");
        } else if (dateRegex(ev_a_add_date_field.getText())) {
            ev_a_alert_field.setText("Please insert date correctly.");
        } else {
            String eventName = ev_a_add_name_field.getText();
            String eventAgenda = ev_a_add_agenda_field.getText();
            String eventDate = ev_a_add_date_field.getText();

            DAO.addEventToDatabase(eventName, eventAgenda, eventDate);
            ev_a_alert_field.setText("NormalModelEvent added successfully.");
            setEventsTable();
        }

    }

    public void deleteEvent(ActionEvent event) {
        String idOfEventToDeleteString = ev_a_delete_ev_id_field.getText();
        Long eventIdDeleted = Long.parseLong(idOfEventToDeleteString);

        NormalModelEvent normalModelEventToDelete = DAO.doFindingEventQuery(DAO.lookForExistingEvent(eventIdDeleted));

        if (normalModelEventToDelete != null) {
            DAO.deleteEvent(normalModelEventToDelete.getId());
            ev_a_alert_field.setText("NormalModelEvent deleted successfully.");
            ev_a_delete_ev_id_field.setText("");
            setEventsTable();
        } else {
            ev_a_alert_field.setText("There is no event with such id.");
        }
    }

    public void loadEventToModify(ActionEvent event) {
        String idOfEventToModifyString = ev_a_mod_ev_id_field.getText();
        Long eventIdModified = Long.parseLong(idOfEventToModifyString);

        normalModelEventToModify = DAO.doFindingEventQuery(DAO.lookForExistingEvent(eventIdModified));

        if (normalModelEventToModify != null) {
            ev_a_mod_name_field.setText(normalModelEventToModify.getName());
            ev_a_mod_agenda_field.setText(normalModelEventToModify.getAgenda());
            ev_a_mod_date_field.setText(normalModelEventToModify.getDate());

            ev_a_alert_field.setText("NormalModelEvent loaded successfully.");

        } else {
            ev_a_alert_field.setText("There is no event with such id.");
        }
    }

    public void saveModifiedEvent(ActionEvent event) {
        normalModelEventToModify.setName(ev_a_mod_name_field.getText());
        normalModelEventToModify.setAgenda(ev_a_mod_agenda_field.getText());
        normalModelEventToModify.setDate(ev_a_mod_date_field.getText());

        DAO.updateEvent(normalModelEventToModify);
        ev_a_alert_field.setText("NormalModelEvent modified successfully.");
        ev_a_mod_name_field.setText("");
        ev_a_mod_agenda_field.setText("");
        ev_a_mod_date_field.setText("");
        ev_a_mod_ev_id_field.setText("");
        setEventsTable();
    }

    public void acceptEntry(ActionEvent event) {
        String idOfEntryToModifyString = en_a_entry_id_field.getText();
        int entryIdModified = Integer.parseInt(idOfEntryToModifyString);

        if(DAO.findExistingEntryToModify(entryIdModified)){
            DAO.acceptEntry(entryIdModified);
            en_a_alert_field.setText("TableEventEntry accepted successfully.");
            en_a_entry_id_field.setText("");
            setEntriesTable();
            // there should be email sending
        } else en_a_alert_field.setText("No entry with such ID, please try again.");

    }

    public void discardEntry(ActionEvent event) {
        String idOfEntryToModifyString = en_a_entry_id_field.getText();
        int entryIdModified = Integer.parseInt(idOfEntryToModifyString);

        if(DAO.findExistingEntryToModify(entryIdModified)){
            DAO.discardEntry(entryIdModified);
            en_a_alert_field.setText("TableEventEntry canceled successfully.");
            en_a_entry_id_field.setText("");
            setEntriesTable();
            // there should be email sending
        } else en_a_alert_field.setText("No entry with such ID, please try again.");
    }

    public void initialize() {

        setUsersTable();
        setEventsTable();
        setEntriesTable();
    }

    private void addDataToUserTable(ObservableList<NormalModelUser> usersList) {
        col_id_user.setCellValueFactory(new PropertyValueFactory<NormalModelUser, Long>("id"));
        col_name_user.setCellValueFactory(new PropertyValueFactory<NormalModelUser, String>("name"));
        col_surname_user.setCellValueFactory(new PropertyValueFactory<NormalModelUser, String>("surname"));
        col_login_user.setCellValueFactory(new PropertyValueFactory<NormalModelUser, String>("login"));
        col_password_user.setCellValueFactory(new PropertyValueFactory<NormalModelUser, String>("password"));
        col_email_user.setCellValueFactory(new PropertyValueFactory<NormalModelUser, String>("email"));
        col_date_user.setCellValueFactory(new PropertyValueFactory<NormalModelUser, String>("date"));
        tbl_users.setItems(usersList);
    }

    private void addDataToEventTable(ObservableList<NormalModelEvent> eventsList) {
        col_id_event.setCellValueFactory(new PropertyValueFactory<NormalModelEvent, Long>("id"));
        col_name_event.setCellValueFactory(new PropertyValueFactory<NormalModelEvent, String>("name"));
        col_agenda_event.setCellValueFactory(new PropertyValueFactory<NormalModelEvent, String>("agenda"));
        col_date_event.setCellValueFactory(new PropertyValueFactory<NormalModelEvent, String>("date"));
        tbl_events.setItems(eventsList);
    }

    private void addDataToEntryTable(ObservableList<TableEventEntry> entriesList) {
        col_id_entries.setCellValueFactory(new PropertyValueFactory<TableEventEntry, Long>("id"));
        col_event_name_entries.setCellValueFactory(new PropertyValueFactory<TableEventEntry, String>("eventName"));
        col_name_entries.setCellValueFactory(new PropertyValueFactory<TableEventEntry, String>("userName"));
        col_surname_entries.setCellValueFactory(new PropertyValueFactory<TableEventEntry, String>("userSurname"));
        col_participation_entries.setCellValueFactory(new PropertyValueFactory<TableEventEntry, String>("participationType"));
        col_food_entries.setCellValueFactory(new PropertyValueFactory<TableEventEntry, String>("foodPreferences"));
        col_entry_status_entries.setCellValueFactory(new PropertyValueFactory<TableEventEntry, String>("entryStatus"));
        tbl_entries.setItems(entriesList);
    }

    private void setUsersTable() {
        ObservableList<NormalModelUser> normalModelUsers = DAO.findAllUsers();
        addDataToUserTable(normalModelUsers);
    }

    private void setEventsTable() {
        ObservableList<NormalModelEvent> normalModelEvents = DEO.findAllEvents();
        addDataToEventTable(normalModelEvents);
    }

    private void setEntriesTable() {
        ObservableList<TableEventEntry> entries = DAO.findAllEventEntries();
        addDataToEntryTable(entries);
    }

    private boolean isIdSetted() {
        return !u_a_id_field.getText().equals("");
    }

    private boolean isEventNameSetted() {
        return !ev_a_add_name_field.getText().equals("");
    }

    private boolean isEventAgendaSetted() {
        return !ev_a_add_agenda_field.getText().equals("");
    }

    private boolean isEventDateSetted() {
        return !ev_a_add_date_field.getText().equals("");
    }

    private boolean dateRegex(String date) {
        Pattern datePattern = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
        return !datePattern.matcher(date).matches();
    }


}
