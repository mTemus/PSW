package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class AdministratorController {
    public TextField u_a_id_field;
    public TextField u_a_new_password_field;
    public TextField u_a_new_password_r_field;
    public Button u_a_del_button;
    public Button u_a_change_password_button;
    public CheckBox u_a_new_password_checkbox;
    public CheckBox u_a_new_password_r_checkbox;
    public Button administrator_logout_button;
    public TextField u_a_alert_field;
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

    public void deleteUser(ActionEvent event) {
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
}
