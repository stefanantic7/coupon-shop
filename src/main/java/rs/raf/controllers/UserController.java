package rs.raf.controllers;

import rs.raf.dtos.UserDto;
import rs.raf.models.User;
import rs.raf.requests.LoginRequest;
import rs.raf.responses.LoginResponse;
import rs.raf.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserController {

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResponse login(@Valid LoginRequest loginRequest) {
        String token = this.userService.login(loginRequest);
        UserDto user = this.userService.find(loginRequest.getUsername());
        return new LoginResponse(token, user);
    }
}
