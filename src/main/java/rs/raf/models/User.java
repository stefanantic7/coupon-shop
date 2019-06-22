package rs.raf.models;

import rs.raf.enums.Privilege;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private Privilege privilegeLevel;
    private String username;
    private String password;

    public User() {}

    public User(long id, String firstName, String lastName, Privilege privilegeLevel, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.privilegeLevel = privilegeLevel;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Privilege getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(Privilege privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return this.getPrivilegeLevel().equals(Privilege.ADMINISTRATOR);
    }

    public boolean isOperator() {
        return this.getPrivilegeLevel().equals(Privilege.OPERATOR);
    }
}
