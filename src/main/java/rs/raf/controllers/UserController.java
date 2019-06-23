package rs.raf.controllers;

import rs.raf.dtos.UserDto;
import rs.raf.models.User;
import rs.raf.requests.CreateUserRequest;
import rs.raf.requests.LoginRequest;
import rs.raf.responses.LoginResponse;
import rs.raf.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDto create(@Valid CreateUserRequest createUserRequest) {
        return this.userService.create(createUserRequest.getFirstName(),
                                        createUserRequest.getLastName(),
                                        createUserRequest.getPrivilegeLevel(),
                                        createUserRequest.getUsername(),
                                        createUserRequest.getPassword());
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResponse login(@Valid LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        String token = this.userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        UserDto user = this.userService.find(loginRequest.getUsername());
        return new LoginResponse(token, user);
    }
}
