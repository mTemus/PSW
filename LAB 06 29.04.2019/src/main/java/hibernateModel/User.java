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
}
