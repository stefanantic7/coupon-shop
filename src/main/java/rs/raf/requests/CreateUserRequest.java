package rs.raf.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateUserRequest {

    @NotNull(message = "First Name must not be blank")
    @NotBlank(message = "First Name must not be blank")
    private String firstName;

    @NotNull(message = "Last Name must not be blank")
    @NotBlank(message = "Last Name must not be blank")
    private String lastName;

    @NotNull(message = "Privilege Level must not be blank")
    @NotBlank(message = "Privilege Level must not be blank")
    @Pattern(regexp = "administrator|operator|ADMINISTRATOR|OPERATOR", message = "Privilege Level should be administrator or operator")
    private String privilegeLevel;

    @NotNull(message = "Username Level must not be blank")
    @NotBlank(message = "Username Level must not be blank")
    private String username;

    @NotNull(message = "Password Level must not be blank")
    @NotBlank(message = "password Level must not be blank")
    @Size(min = 8, max = 255, message = "Password length should be greater than 8 characters")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
