package model;

public class user {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String date;

    private enum Permissions {
        USER, ADMINISTRATOR
    }

    private enum FoodPreferences {
        VEGAN, NO_GLUTEN, NO_PREFFERENCES
    }

    private enum ParticipationType {
        LISTENER, AUTHOR, SPONSOR, ORGANIZER
    }

    private Permissions permissions = null;
    private FoodPreferences foodPerfferences = null;
    private ParticipationType participationType = null;


}
