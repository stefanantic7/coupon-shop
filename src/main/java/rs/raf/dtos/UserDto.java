package rs.raf.dtos;

import rs.raf.enums.Privilege;

public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String privilegeLevel;
    private String username;

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

    public String getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(String privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
