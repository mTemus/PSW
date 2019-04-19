package model;

public class User {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String date;

    public User(String name, String surname, String login, String password, String email) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = null;
        this.permissions = Permissions.USER;
        this.foodPreferences = null;
        this.participationType = null;
    }

    public enum Permissions {
        USER, ADMINISTRATOR
    }

    enum FoodPreferences {
        VEGAN, NO_GLUTEN, NO_PREFFERENCES
    }

    enum ParticipationType {
        LISTENER, AUTHOR, SPONSOR, ORGANIZER
    }

    private Permissions permissions = null;
    private FoodPreferences foodPreferences = null;
    private ParticipationType participationType = null;

    public User(Long id, String name, String surname, String login, String password, String email, String date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
        this.permissions = Permissions.USER;
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

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
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
