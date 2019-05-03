package hibernateModel;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementer")
    @GenericGenerator(name = "incrementer", strategy = "increment")
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

    public User(String name, String surname, String login, String password, String email) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = null;
        this.permissions = model.User.Permissions.USER.toString();
        this.foodPreferences = null;
        this.participationType = null;
    }

    public enum Permissions {
        USER, ADMINISTRATOR
    }

    public enum FoodPreferences {
        VEGETARIAN, GLUTEN_FREE, NO_PREFERENCES
    }

    public enum ParticipationType {
        LISTENER, AUTHOR, SPONSOR, ORGANIZER
    }

    private model.User.FoodPreferences foodPreferences = null;
    private model.User.ParticipationType participationType = null;

    public User(Long id, String name, String surname, String login, String password, String email, String date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
        this.permissions = model.User.Permissions.USER.toString();
        this.foodPreferences = null;
        this.participationType = null;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public model.User.FoodPreferences getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(model.User.FoodPreferences foodPreferences) {
        this.foodPreferences = foodPreferences;
    }

    public model.User.ParticipationType getParticipationType() {
        return participationType;
    }

    public void setParticipationType(model.User.ParticipationType participationType) {
        this.participationType = participationType;
    }
}
