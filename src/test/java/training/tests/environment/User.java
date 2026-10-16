package training.tests.environment;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;

    public User (int id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public User() {
    }

    public getId() {
        return id;
    }

    public getName() {
        return name;
    }

    public getUsername() {
        return username;
    }

    public getEmail() {
        return email;
    }
}
