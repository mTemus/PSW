package controller;

import hibernateModel.Event;
import hibernateModel.User;
import hibernateOperations.DatabaseEventOperations;
import operations.StageOperations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class EventController {

    public ToggleGroup food_p;
    private DatabaseEventOperations DEO = new DatabaseEventOperations();
    private StageOperations SO = new StageOperations();
    private static User loggedNormalModelUser = LoginController.getLoggedUser();
    private static Event chosenNormalModelEvent = null;

    private static ObservableList<Event> normalModelEvents = FXCollections.observableArrayList();

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
        loggedNormalModelUser = LoginController.getLoggedNormalModelUser();
        setComboBox();
    }

    public void registerOnEvent(ActionEvent event) {
        event_required_event.setText("");
        event_text_required_participation.setText("");

        if (checkChosen()) {
            checkAndSetParticipation();
            checkAndSetFoodPreferences();
            event_error_area.setText("");

            if (DEO.checkIfEntryExists(loggedNormalModelUser.getId(), chosenNormalModelEvent.getId()) == null) {
                if (DEO.registerUserOnEvent(loggedNormalModelUser, chosenNormalModelEvent)) {
                    try {
                        SO.changeSceneToRegistrationComplete(event);
                        // there should be email sending
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    event_error_area.setText("Something went wrong, please contact the administrator.");
                }
            } else {
                String status = DEO.checkIfEntryExists(loggedNormalModelUser.getId(), chosenNormalModelEvent.getId());
                event_error_area.setText("You have already registered on this event! Your entry status is: " + status);
            }

        } else if (event_combobox.getValue() == null && !participationIsSelected()) {
            event_error_area.setText("Please choose " + noEventChosen() + " and " + noParticipationTypeChosen() + " and try again.");
        } else if (event_combobox.getValue() == null) {
            event_error_area.setText("Please choose " + noEventChosen() + "  and try again.");
        } else if (!participationIsSelected()) {
            event_error_area.setText("Please chose " + noParticipationTypeChosen() + " and try again.");
        }
    }

    private void setComboBox() {
        normalModelEvents = DEO.findAllEvents();
        ObservableList<String> eventsNamesCombobox = DEO.findAllEventNames();
        event_combobox.getItems().addAll(eventsNamesCombobox);
    }

    public void checkCombobox(ActionEvent event) {
        String eventName = (String) event_combobox.getValue();
        setEventAreas(normalModelEvents, eventName);
    }

    private void setEventAreas(ObservableList<NormalModelEvent> normalModelEvents, String eventName) {
        for (NormalModelEvent e : normalModelEvents) {
            if (eventName.equals(e.getName())) {
                event_agenda_field.setText(e.getAgenda());
                event_date_field.setText(e.getDate());
                chosenNormalModelEvent = e;
                break;
            }
        }
    }

    private void checkAndSetParticipation() {
        if (participation_radio_author.isSelected())
            loggedNormalModelUser.setParticipationType(NormalModelUser.ParticipationType.AUTHOR);
        else if (participation_radio_listener.isSelected())
            loggedNormalModelUser.setParticipationType(NormalModelUser.ParticipationType.LISTENER);
        else if (participation_radio_organizer.isSelected())
            loggedNormalModelUser.setParticipationType(NormalModelUser.ParticipationType.ORGANIZER);
        else if (participation_radio_sponsor.isSelected())
            loggedNormalModelUser.setParticipationType(NormalModelUser.ParticipationType.SPONSOR);
    }

    private void checkAndSetFoodPreferences() {
        if (food_radio_noPreferences.isSelected())
            loggedNormalModelUser.setFoodPreferences(NormalModelUser.FoodPreferences.NO_PREFERENCES);
        else if (food_radio_vegetarian.isSelected())
            loggedNormalModelUser.setFoodPreferences(NormalModelUser.FoodPreferences.VEGETARIAN);
        else if (food_radio_glutenFree.isSelected())
            loggedNormalModelUser.setFoodPreferences(NormalModelUser.FoodPreferences.GLUTEN_FREE);
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

    private String noEventChosen() {
        String question = "your event";

        event_required_event.setText("is required!");
        event_required_event.setFill(Color.RED);

        return question;
    }

    private String noParticipationTypeChosen() {
        String question = "participation type";

        event_text_required_participation.setText("is required!");
        event_text_required_participation.setFill(Color.RED);

        return question;
    }

    static void setLoggedNormalModelUser(NormalModelUser loggedNormalModelUser) {
        EventController.loggedNormalModelUser = loggedNormalModelUser;
    }

    static void setChosenNormalModelEvent(NormalModelEvent chosenNormalModelEvent) {
        EventController.chosenNormalModelEvent = chosenNormalModelEvent;
    }

    public static NormalModelEvent getChosenNormalModelEvent() {
        return chosenNormalModelEvent;
    }

}
