package rs.raf.responses;

import rs.raf.dtos.UserDto;
import rs.raf.models.User;

public class LoginResponse {
    private String token;
    private UserDto userDto;

    public LoginResponse(String token, UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setUser(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserDto getUser() {
        return userDto;
    }
}
