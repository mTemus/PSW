package model;

public class TableEventEntry {

    private Long id;
    private String eventName;
    private String userName;
    private String userSurname;
    private String participationType;
    private String foodPreferences;
    private String entryStatus;

    public TableEventEntry(Long id, String eventName, String userName, String userSurname, String participationType, String foodPreferences, String entryStatus) {
        this.id = id;
        this.eventName = eventName;
        this.userName = userName;
        this.userSurname = userSurname;
        this.participationType = participationType;
        this.foodPreferences = foodPreferences;
        this.entryStatus = entryStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getParticipationType() {
        return participationType;
    }

    public void setParticipationType(String participationType) {
        this.participationType = participationType;
    }

    public String getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }
}
