package io.getarrays.securecapita.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;

    private String fieldName;

    private Object fieldValue;

    // Constructor with resourceName and fieldName to provide more detailed information
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    // Constructor with a custom message
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Getters for additional context if needed
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
