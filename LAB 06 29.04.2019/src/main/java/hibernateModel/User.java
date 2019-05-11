package hibernateModel;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "date")
    private String date;

    @Column(name = "permissions")
    private String permissions;

    @Column(name = "foodPreferences")
    private FoodPreferences foodPreferences = null;

    @Column(name = "participationType")
    private ParticipationType participationType = null;


    public enum Permissions {
        USER, ADMINISTRATOR
    }
    public enum FoodPreferences {
        VEGETARIAN, GLUTEN_FREE, NO_PREFERENCES
    }
    public enum ParticipationType {
        LISTENER, AUTHOR, SPONSOR, ORGANIZER
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getDate() {
        return date;
    }
    public String getPermissions() {
        return permissions;
    }
    public FoodPreferences getFoodPreferences() {
        return foodPreferences;
    }
    public ParticipationType getParticipationType() {
        return participationType;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    public void setFoodPreferences(FoodPreferences foodPreferences) {
        this.foodPreferences = foodPreferences;
    }
    public void setParticipationType(ParticipationType participationType) {
        this.participationType = participationType;
    }
}
