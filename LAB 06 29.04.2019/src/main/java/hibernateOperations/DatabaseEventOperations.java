package hibernateOperations;

import hibernateModel.Event;
import hibernateModel.EventEntry;
import hibernateModel.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class DatabaseEventOperations {
    private EntityManagerOperations EMO = new EntityManagerOperations();

    public ObservableList<Event> findAllEvents() {
        ObservableList<Event> events = FXCollections.observableArrayList();
        String query = "SELECT e FROM Event e";
        TypedQuery<Event> typedQuery = EMO.getEntityManager().createQuery(query, Event.class);
        events.addAll(typedQuery.getResultList());
        EMO.closeConnection();
        return events;
    }

    public ObservableList<String> findAllEventNames() {
        ObservableList<String> eventNames = FXCollections.observableArrayList();
        ObservableList<Event> events = findAllEvents();

        for (Event e : events) {
            eventNames.add(e.getName());
        }

        return eventNames;
    }

    public boolean registerUserOnEvent(User user, Event event) {
        EventEntry tmpEntry = new EventEntry();
        try {
            tmpEntry.setEventId(event.getId());
            tmpEntry.setUserId(user.getId());
            tmpEntry.setFoodPreferences(user.getFoodPreferences().toString());
            tmpEntry.setParticipationType(user.getParticipationType().toString());
            tmpEntry.setStatus("waiting");

            EMO.getEntityManager().persist(tmpEntry);
            EMO.closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String checkIfEntryExists(Long eventID, Long userID) {
        EventEntry tmpEntry;

        String query = "SELECT ee FROM EventEntry ee WHERE ee.userId = :uID AND ee.eventId = :eID";
        TypedQuery<EventEntry> typedQuery = EMO.getEntityManager().createQuery(query, EventEntry.class);
        typedQuery.setParameter("uID", userID);
        typedQuery.setParameter("eID", eventID);

        try {
            tmpEntry = typedQuery.getSingleResult();
            return tmpEntry.getStatus();

        } catch (NoResultException nre) {

            return null;
        }
    }
}
