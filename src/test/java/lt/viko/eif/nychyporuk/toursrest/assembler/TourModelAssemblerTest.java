package lt.viko.eif.nychyporuk.toursrest.assembler;

import lt.viko.eif.nychyporuk.toursrest.controller.ToursController;
import lt.viko.eif.nychyporuk.toursrest.model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TourModelAssemblerTest {

    @Mock
    private ToursController toursController;

    @InjectMocks
    private TourModelAssembler tourModelAssembler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testToModel() throws Exception {
        // Create a sample tour
        Tour tour = new Tour();
        tour.setId(1);
        tour.setName("Test Tour");

        // Assemble the tour model
        EntityModel<Tour> tourModel = tourModelAssembler.toModel(tour);

        // Verify the self link
        tourModel.getLinks().forEach(link -> {
            if (link.getRel().value().equals("self")) {
                // MockMvc setup
                MockMvc mockMvc = MockMvcBuilders.standaloneSetup(toursController).build();
                try {
                    // Perform a GET request to the self link
                    mockMvc.perform(get(link.getHref()).accept(MediaType.APPLICATION_JSON))
                            // Expect status 200 OK
                            .andExpect(status().isOk());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

