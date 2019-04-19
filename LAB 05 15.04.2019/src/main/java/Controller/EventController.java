package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.User;

public class EventController {

    LoginController LC = new LoginController();
    User loggedUser = LC.getLoggedUser();

    public TextArea event_agenda_field;
    public ComboBox event_combobox;
    public RadioButton participation_radio_listener;
    public ToggleGroup participation_t;
    public RadioButton participation_radio_author;
    public RadioButton participation_radio_sponsor;
    public RadioButton participation_radio_organizer;
    public RadioButton food_radio_noPreferences;
    public RadioButton food_radio_vegetarian;
    public RadioButton food_radio_glutenFree;
    public TextField event_date_field;
    public Button register_on_event_button;


    public void registerOnEvent(ActionEvent event) {


    }
}
