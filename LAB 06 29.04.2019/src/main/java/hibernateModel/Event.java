package hibernateModel;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id_event")
    @GeneratedValue(generator = "incrementer")
    @GenericGenerator(name = "incrementer", strategy = "increment")
    private Long id;

    @Column(name = "event_name")
    private String name;

    @Column(name = "agenda")
    private String agenda;

    @Column(name = "date")
    private String date;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAgenda() {
        return agenda;
    }
    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
