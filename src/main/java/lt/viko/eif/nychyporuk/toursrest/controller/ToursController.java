package lt.viko.eif.nychyporuk.toursrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.nychyporuk.toursrest.assembler.TourModelAssembler;
import lt.viko.eif.nychyporuk.toursrest.db.ToursRepository;
import lt.viko.eif.nychyporuk.toursrest.exception.TourNotFoundException;
import lt.viko.eif.nychyporuk.toursrest.model.Tour;
import lt.viko.eif.nychyporuk.toursrest.validator.TourValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tours")
@Tag(name = "Tours API", description = "API for managing tours")
public class ToursController {

    private final ToursRepository toursRepository;

    private final TourModelAssembler tourModelAssembler;

    private final TourValidator tourValidator;

    @Autowired
    public ToursController(ToursRepository toursRepository,
                           TourModelAssembler tourModelAssembler,
                           TourValidator tourValidator) {
        this.toursRepository = toursRepository;
        this.tourModelAssembler = tourModelAssembler;
        this.tourValidator = tourValidator;
    }



    @Operation(summary = "Retrieve all tours or search by name",
            description = "Returns all tours or filters by a given name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Successfully retrieved list or no matches found")
    })
    @GetMapping
    public ResponseEntity<?> findTours(
            @RequestParam(required = false) String name) {

        if (name != null) {

            Tour tour = toursRepository.findByName(name)
                    .orElseThrow(() -> new TourNotFoundException(name));
            return ResponseEntity.ok(tourModelAssembler.toModel(tour));
        } else {

            List<EntityModel<Tour>> tours = toursRepository.findAll()
                    .stream()
                    .map(tourModelAssembler::toModel)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(CollectionModel.of(tours,
                    linkTo(methodOn(ToursController.class).findTours(null)).withSelfRel()));
        }
    }

    @Operation(summary = "Create a new tour",
            description = "Creates a new tour and returns it with HATEOAS links")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created the tour"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input"),
            @ApiResponse(responseCode = "404",
                    description = "Link not found")
    })
    @PostMapping
    public ResponseEntity<?> addTour(@RequestBody Tour newTour) {

        if (tourValidator.isValid(newTour)) {
            toursRepository.save(newTour);
            EntityModel<Tour> entityModel = tourModelAssembler.toModel(newTour);

            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }


    @Operation(summary = "Find a tour by ID",
            description = "Returns a single tour by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the tour"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - The tour was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findTourById(@PathVariable int id) {

        Tour tour = toursRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException(id));

        EntityModel<Tour> model = tourModelAssembler.toModel(tour);
        return ResponseEntity.ok(model);
    }



    @Operation(summary = "Replace a tour by ID",
            description = "Replaces the tour data for the specified ID or creates a new tour if it does not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully replaced the tour"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - The tour was not found"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> replaceTour(@PathVariable int id,
                                      @RequestBody Tour newTour) {

        Tour tour = toursRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException(id));

        if (tourValidator.isValid(newTour)) {
            tour.setName(newTour.getName());
            tour.setPrice(newTour.getPrice());
            tour.setDuration(newTour.getDuration());
            tour.setVisits(newTour.getVisits());
            tour.setGuide(newTour.getGuide());
            toursRepository.save(tour);

            return ResponseEntity.ok(tourModelAssembler.toModel(tour));
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @Operation(summary = "Delete a tour by ID",
            description = "Deletes a tour by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully deleted the tour"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - The tour to delete was not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTour(@PathVariable int id) {

        Tour tour = toursRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException(id));

        toursRepository.deleteById(id);

        return ResponseEntity.ok("Successfully deleted the tour with id " + id);
    }
}
