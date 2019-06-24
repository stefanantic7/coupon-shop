package rs.raf.exceptions;

import rs.raf.responses.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class ModelNotFoundException extends Exception implements ExceptionMapper<ModelNotFoundException> {

    @Override
    public Response toResponse(ModelNotFoundException exception) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        errorResponses.add(new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "Model not found"));
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponses)
                .build();
    }

}
