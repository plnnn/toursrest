package lt.viko.eif.nychyporuk.toursrest.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import lt.viko.eif.nychyporuk.toursrest.controller.ToursController;
import lt.viko.eif.nychyporuk.toursrest.model.Tour;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Assembler for building {@link EntityModel} of {@link Tour} with HATEOAS links.
 * This class is responsible for converting a {@link Tour} model into an {@link EntityModel<Tour>}
 * and enriching it with HATEOAS links that allow clients to discover other resources and actions.
 *
 */
@Component
public class TourModelAssembler implements
        RepresentationModelAssembler<Tour, EntityModel<Tour>> {

    /**
     * Converts a {@link Tour} object into an {@link EntityModel<Tour>} enriched with HATEOAS links.
     * The method adds links to access the tour by ID, by name, to delete the tour,
     * to replace (update) the tour, and to access the list of all tours.
     *
     * @param tour The {@link Tour} instance to convert.
     * @return An {@link EntityModel<Tour>} that contains the {@link Tour} and its associated HATEOAS links.
     */
    @Override
    public EntityModel<Tour> toModel(Tour tour) {

        EntityModel<Tour> model = EntityModel.of(tour,
                linkTo(methodOn(ToursController.class)
                        .findTourById(tour.getId()))
                        .withSelfRel().withType("GET"));

        model.add(linkTo(methodOn(ToursController.class)
                        .findTours(tour.getName()))
                        .withRel("findByName").withType("GET").expand(tour.getName()));

        model.add(linkTo(methodOn(ToursController.class)
                        .deleteTour(tour.getId()))
                        .withRel("delete").withType("DELETE"));

        model.add(linkTo(methodOn(ToursController.class)
                        .replaceTour(tour.getId(), tour))
                        .withRel("replace").withType("PUT"));

        model.add(linkTo(methodOn(ToursController.class)
                        .findTours(null))
                        .withRel("tours").withType("GET"));

        return model;
    }
}
