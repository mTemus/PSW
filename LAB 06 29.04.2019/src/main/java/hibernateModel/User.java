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
    public Long id;
    @Column(name = "name")
    public String name;
    @Column(name = "surname")
    public String surname;
    @Column(name = "login")
    public String login;
    @Column(name = "password")
    public String password;
    @Column(name = "email")
    public String email;
    @Column(name = "date")
    public String date;
    @Column(name = "permissions")
    public String permissions;

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

    public FoodPreferences getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(FoodPreferences foodPreferences) {
        this.foodPreferences = foodPreferences;
    }

    public ParticipationType getParticipationType() {
        return participationType;
    }

    public void setParticipationType(ParticipationType participationType) {
        this.participationType = participationType;
    }
}
