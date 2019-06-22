package rs.raf.requests.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import rs.raf.annotations.AuthenticatedAsAdmin;
import rs.raf.annotations.AuthenticatedAsOperator;
import rs.raf.models.User;
import rs.raf.responses.ErrorResponse;
import rs.raf.services.UserService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHORIZATION)
@AuthenticatedAsOperator
public class OperatorOperationsRequestFilter implements ContainerRequestFilter {

    private UserService userService;

    @Inject
    OperatorOperationsRequestFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        try {
            String token = requestContext.getHeaderString("Authorization");
            if(token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            User user = this.userService.parseJwt(token);

            if (user == null || !user.isOperator()) {
                throw new JWTVerificationException("Permission denied!");
            }

            requestContext.setProperty("user", user);

        } catch (JWTVerificationException exception){
            // TODO: return more precise error to user
            ErrorResponse errorResponse = new ErrorResponse(401, exception.getLocalizedMessage());
            requestContext.abortWith(Response.status(401).entity(errorResponse).build());
        }
    }
}
