package hibernateModel;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "events_entries")
public class EventEntry {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "entry_id", unique = true)
    private Long entryId;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "participation_type", nullable = false)
    private String participationType;

    @Column(name = "food_preferences", nullable = false)
    private String foodPreferences;

    @Column(name = "status", nullable = false)
    private String status;

    public Long getEntryId() {
        return entryId;
    }
    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }
    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
