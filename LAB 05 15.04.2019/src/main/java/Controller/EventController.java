package Controller;

import Operations.DatabaseEventOperations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Event;
import model.User;

public class EventController {

    private DatabaseEventOperations DEO = new DatabaseEventOperations();
    private User loggedUser = LoginController.getLoggedUser();
    private static Event choosedEvent = null;

    private static ObservableList<Event> events = FXCollections.observableArrayList();

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

    public void initialize() {
        setComboBox();

    }

    public void registerOnEvent(ActionEvent event) {


    }

    private void setComboBox() {
        events = DEO.findAllEvents();
        ObservableList<String> eventsNamesCombobox = DEO.findAllEventNames();
        event_combobox.getItems().addAll(eventsNamesCombobox);
    }

    public void checkCombobox(ActionEvent event) {
        String eventName = (String) event_combobox.getValue();
        setEventAreas(events, eventName);
    }

    private void setEventAreas(ObservableList<Event> events, String eventName) {
        for (Event e : events) {
            if (eventName.equals(e.getName())) {
                event_agenda_field.setText(e.getAgenda());
                event_date_field.setText(e.getDate());
                choosedEvent = e;
                break;
            }
        }
    }


}
