package rs.raf.requests;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull(message = "Username must not be null")
    @NotBlank(message = "Username must not be blank")
    private String username;

    @NotNull(message = "Password must not be null")
    @NotBlank(message = "Password must not be blank")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
