package hibernateModel;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String date;
    private String permissions;

    public enum Permissions {
        USER, ADMINISTRATOR
    }

    public enum FoodPreferences {
        VEGETARIAN, GLUTEN_FREE, NO_PREFERENCES
    }

    public enum ParticipationType {
        LISTENER, AUTHOR, SPONSOR, ORGANIZER
    }

    private FoodPreferences foodPreferences = null;
    private ParticipationType participationType = null;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    @Column(name = "permissions")
    public String getPermissions() {
        return permissions;
    }

    @Column(name = "foodPreferences")
    public FoodPreferences getFoodPreferences() {
        return foodPreferences;
    }

    @Column(name = "participationType")
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
