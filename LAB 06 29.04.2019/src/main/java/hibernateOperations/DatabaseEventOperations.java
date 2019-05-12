package hibernateOperations;

import hibernateModel.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        ObservableList<Event> events= findAllEvents();

        for (Event e: events) {
            eventNames.add(e.getName());
        }

        return eventNames;
    }
//
//    public boolean registerUserOnEvent(NormalModelUser normalModelUser, NormalModelEvent normalModelEvent) {
//
//        try {
//            String Query = "INSERT INTO events_entries " +
//                    "(entry_id, " +
//                    "event_id, " +
//                    "user_id, " +
//                    "participation_type, " +
//                    "food_preferences, " +
//                    "status) " +
//                    "VALUES " +
//                    "(default, " +
//                    "'" + normalModelEvent.getId() + "', " +
//                    "'" + normalModelUser.getId() + "', " +
//                    "'" + normalModelUser.getParticipationType() + "', " +
//                    "'" + normalModelUser.getFoodPreferences() + "', " +
//                    "default);";
//
//            Statement Stm = MySQLConnection().createStatement();
//            Stm.executeUpdate(Query);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public String checkIfEntryExists(Long userID, Long eventID) {
//        String status = null;
//        ResultSet entryRS;
//
//        PreparedStatement findingEntryStm = null;
//        try {
//            findingEntryStm = MySQLConnection().prepareStatement("SELECT * FROM events_entries WHERE event_id LIKE ? AND user_id LIKE ?");
//            findingEntryStm.setLong(1, eventID);
//            findingEntryStm.setLong(2, userID);
//
//            entryRS = findingEntryStm.executeQuery();
//
//            if (entryRS.next()){
//                status = entryRS.getString("status");
//                return status;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return null;
//    }
}
