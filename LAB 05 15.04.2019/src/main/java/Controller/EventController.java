package Controller;

import Operations.DatabaseEventOperations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Event;
import model.User;

public class EventController {

    private DatabaseEventOperations DEO = new DatabaseEventOperations();
    private User loggedUser = LoginController.getLoggedUser();
    private static Event choosedEvent = null;

    private static ObservableList<Event> events = FXCollections.observableArrayList();

    public Text event_text_required_participation;
    public Text event_required_event;
    public TextArea event_error_area;
    public Text event_participation_text;
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
        event_required_event.setText("");
        event_text_required_participation.setText("");

        if (checkChosen()) {
            checkAndSetParticipation();
            checkAndSetFoodPreferences();
        } else if (event_combobox.getValue() == null && !participationIsSelected()) {
            event_required_event.setText("is required!");
            event_required_event.setFill(Color.RED);
            event_text_required_participation.setText("is required!");
            event_text_required_participation.setFill(Color.RED);

        } else if (event_combobox.getValue() == null) {
            event_required_event.setText("is required!");
            event_required_event.setFill(Color.RED);
        } else if (!participationIsSelected()) {
            event_text_required_participation.setText("is required!");
            event_text_required_participation.setFill(Color.RED);
        }


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

    private void checkAndSetParticipation() {
        if (participation_radio_author.isSelected())
            loggedUser.setParticipationType(User.ParticipationType.AUTHOR);
        else if (participation_radio_listener.isSelected())
            loggedUser.setParticipationType(User.ParticipationType.LISTENER);
        else if (participation_radio_organizer.isSelected())
            loggedUser.setParticipationType(User.ParticipationType.ORGANIZER);
        else if (participation_radio_sponsor.isSelected())
            loggedUser.setParticipationType(User.ParticipationType.SPONSOR);
    }

    private void checkAndSetFoodPreferences() {
        if (food_radio_noPreferences.isSelected())
            loggedUser.setFoodPreferences(User.FoodPreferences.NO_PREFFERENCES);
        else if (food_radio_vegetarian.isSelected())
            loggedUser.setFoodPreferences(User.FoodPreferences.VEGETARIAN);
        else if (food_radio_glutenFree.isSelected())
            loggedUser.setFoodPreferences(User.FoodPreferences.GLUTEN_FREE);


    }

    private boolean checkChosen() {
        return event_combobox.getValue() != null && participationIsSelected();
    }

    private boolean participationIsSelected() {
        return participation_radio_listener.isSelected() ||
                participation_radio_author.isSelected() ||
                participation_radio_sponsor.isSelected() ||
                participation_radio_organizer.isSelected();

    }

}
