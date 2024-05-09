package lt.viko.eif.nychyporuk.toursrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Tours REST Application.
 * This class initializes and starts the Spring Boot application.
 */
@SpringBootApplication
public class ToursrestApplication {

	/**
	 * Main method to start the Tours REST Application.
	 *
	 * @param args command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ToursrestApplication.class, args);
	}

}
