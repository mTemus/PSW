package model;

public class User {

    private String login, password, name, surname, email;

    private enum Permissions {
        USER, ADMINISTRATOR, NULL;
    }

    public User(String login, String password, String name, String surname, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        Permissions permissions = Permissions.USER;
    }
}
