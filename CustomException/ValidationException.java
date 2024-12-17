package CustomException;

//This class represents a custom exception for validation errors.
public class ValidationException extends Exception {
    // Constructor for the ValidationException class.
    public ValidationException(String message) {
        super(message);
    }
}
