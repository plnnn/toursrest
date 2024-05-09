package lt.viko.eif.nychyporuk.toursrest.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Pavlo Nychyporuk",
                        email = "pavlo.nychyporuk@stud.viko.lt",
                        url = "https://eif.viko.lt"
                ),
                description = "OpenAPI documentation for tours management application",
                title = "Tours application",
                version = "1.0",
                license = @License(
                        name = "Free to use",
                        url = "https://eif.viko.lt"
                )
        ),
        servers = {
                @Server(
                        description = "Local DEV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "TEST server",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {

}
