package InputValidation;

import java.util.ArrayList;
import java.util.List;

import CustomException.ValidationException;

/**
 * Class for validating inputs like phone number, height, and weight.
 */
public class Validation {

    /**
     * Validates the phone number.
     * 
     * @param phone Phone number as a string.
     * @throws ValidationException If the phone number is not 10 digits or contains non-numbers.
     */
    public static void validatePhone(String phone) throws ValidationException {
        if (phone.length() != 10 || !phone.matches("\\d+")) {
            throw new ValidationException("Invalid phone number.");
        }
    }

    /**
     * Validates the height.
     * 
     * @param height Height as an integer.
     * @throws ValidationException If the height is less than or equal to zero.
     */
    public static void validateHeight(int height) throws ValidationException {
        if (height <= 0) {
            throw new ValidationException("Height must be positive.");
        }
    }

    /**
     * Validates the weight.
     * 
     * @param weight Weight as an integer.
     * @throws ValidationException If the weight is less than or equal to zero.
     */
    public static void validateWeight(int weight) throws ValidationException {
        if (weight <= 0) {
            throw new ValidationException("Weight must be positive.");
        }
    }

    /**
     * Validates all inputs: phone number, height, and weight.
     * 
     * @param phone  Phone number as a string.
     * @param height Height as an integer.
     * @param weight Weight as an integer.
     * @throws ValidationException If any validation fails, it collects all errors and throws them together.
     */
    public static void validateAll(String phone, int height, int weight) throws ValidationException {
        List<String> errorMessages = new ArrayList<>();

        try {
            validatePhone(phone);
        } catch (ValidationException e) {
            errorMessages.add(e.getMessage());
        }

        try {
            validateHeight(height);
        } catch (ValidationException e) {
            errorMessages.add(e.getMessage());
        }

        try {
            validateWeight(weight);
        } catch (ValidationException e) {
            errorMessages.add(e.getMessage());
        }

        // If there are any errors, throw one exception with all messages.
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(String.join(", ", errorMessages));
        }
    }
}
