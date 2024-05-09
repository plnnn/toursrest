package lt.viko.eif.nychyporuk.toursrest.exception;

/**
 * Exception thrown to indicate that a tour could not be found.
 * This exception is typically thrown when attempting to retrieve a tour by its ID or name,
 * but no tour matching the specified criteria is found in the system.
 */
public class TourNotFoundException extends RuntimeException {

    /**
     * Constructs a new TourNotFoundException with the specified ID.
     *
     * @param id the ID of the tour that could not be found
     */
    public TourNotFoundException(int id) {
        super("Could not find tour with id " + id);
    }

    /**
     * Constructs a new TourNotFoundException with the specified name.
     *
     * @param name the name of the tour that could not be found
     */
    public TourNotFoundException(String name) {
        super("Could not find tour with name " + name);
    }
}
