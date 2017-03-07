package uk.co.boots.columbus.cmdb.model.core.rest.support;

public class RestExceptionResponse {
    private String message;

    public RestExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
