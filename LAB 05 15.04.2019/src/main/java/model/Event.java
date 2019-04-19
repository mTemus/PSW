package model;

public class Event {

    private Long id;
    private String name;
    private String agenda;
    private String date;

    public Event(Long id, String name, String agenda, String date) {
        this.id = id;
        this.name = name;
        this.agenda = agenda;
        this.date = date;
    }

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
