package lt.viko.eif.nychyporuk.toursrest.db;

import lt.viko.eif.nychyporuk.toursrest.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing the Tour data in the database.
 * This interface extends JpaRepository, leveraging Spring Data JPA to provide
 * CRUD operations on the Tour entity.
 *
 * @see JpaRepository
 */
public interface ToursRepository extends JpaRepository<Tour, Integer> {

    /**
     * Retrieves a Tour by its name.
     * This method will return an {@link Optional} of Tour which encapsulates the possibility
     * that a Tour with the specified name may not exist. This provides a safer way to handle
     * the absence of values as opposed to returning null.
     *
     * @param name the name of the tour to retrieve
     * @return an {@link Optional} containing the Tour if found, or an empty Optional if no tour
     *         with the specified name exists
     */
    Optional<Tour> findByName(String name);
}
