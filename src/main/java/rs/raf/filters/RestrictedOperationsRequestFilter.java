package rs.raf.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import rs.raf.annotations.Authenticated;
import rs.raf.annotations.AuthenticatedAsAdmin;
import rs.raf.models.User;
import rs.raf.repositories.users.UserRepository;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHORIZATION)
@AuthenticatedAsAdmin
public class RestrictedOperationsRequestFilter implements ContainerRequestFilter {

    private UserRepository userRepository;

    @Inject
    RestrictedOperationsRequestFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        System.out.println("PreRequest");

        try {
            //TODO: get token from request!
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTU2MTI0MDgwMCwidXNlcklkIjoxfQ.ID4lQjS_rmEtzvUMA6dbGjW1CRlvccnerpMW4o7LKb4";
            Algorithm algorithm = Algorithm.HMAC256("secrett");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            //TODO: Assert user is not null!
            User user = userRepository.find(jwt.getClaim("userId").asInt());
            if (!user.isAdmin()) {
                throw new JWTVerificationException("Permission denied!");
            }
            requestContext.setProperty("user", user);

        } catch (JWTVerificationException exception){
            // TODO: return error to user
            requestContext.abortWith(Response.status(401).entity(exception.getLocalizedMessage()).build());
        }
    }
}
