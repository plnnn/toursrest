package lt.viko.eif.nychyporuk.toursrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler for handling {@link TourNotFoundException}.
 * This class provides a centralized exception handling across all {@code @RequestMapping} methods
 * through {@code @ControllerAdvice}.
 * <p>
 * It is annotated with {@code @ControllerAdvice} which makes it a controller advice allowing it to
 * handle exceptions across the whole application. It does not target any specific controller.
 */
@ControllerAdvice
public class TourNotFoundAdvice {

    /**
     * Handles the {@link TourNotFoundException} by converting it into a human-readable message.
     * <p>
     * This method is marked with {@code @ExceptionHandler}, indicating it is an exception handler for
     * {@link TourNotFoundException}. It responds with the message from the exception when such an exception
     * is thrown from any controller method.
     * <p>
     * The response body is annotated with {@code @ResponseBody} to indicate that the return type should be
     * written directly to the HTTP response body (and not placed in a Model, or interpreted as a view name).
     * <p>
     * It also uses {@code @ResponseStatus} to set the HTTP status code to {@code HttpStatus.NOT_FOUND} (404),
     * indicating that the requested resource was not found.
     *
     * @param ex the caught {@link TourNotFoundException}
     * @return a String that contains the exception message, which will be returned as the body of the response.
     */
    @ResponseBody
    @ExceptionHandler(TourNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String tourNotFoundHandler(TourNotFoundException ex) {
        return ex.getMessage();
    }
}
