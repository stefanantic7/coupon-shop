package rs.raf.exceptions;

import rs.raf.responses.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class CustomException extends Exception implements ExceptionMapper<CustomException> {

    private String message;
    private Response.Status status;

    public CustomException(String message, Response.Status status) {
        this.message = message;
        this.status = status;
    }

    public CustomException() {}

    @Override
    public Response toResponse(CustomException exception) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        errorResponses.add(new ErrorResponse(exception.getStatus().getStatusCode(), exception.getMessage()));
        return Response
                .status(exception.getStatus())
                .entity(errorResponses).build();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }
}
