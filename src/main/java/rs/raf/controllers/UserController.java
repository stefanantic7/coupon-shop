package rs.raf.controllers;

import rs.raf.models.User;
import rs.raf.requests.LoginRequest;
import rs.raf.responses.LoginResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserController {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResponse login(@Valid LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return new LoginResponse("aa");
    }
}
