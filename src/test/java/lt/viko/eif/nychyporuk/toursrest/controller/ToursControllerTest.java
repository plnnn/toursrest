package lt.viko.eif.nychyporuk.toursrest.controller;

import lt.viko.eif.nychyporuk.toursrest.assembler.TourModelAssembler;
import lt.viko.eif.nychyporuk.toursrest.db.ToursRepository;
import lt.viko.eif.nychyporuk.toursrest.exception.TourNotFoundException;
import lt.viko.eif.nychyporuk.toursrest.model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ToursControllerTest {

//    @Mock
//    private ToursRepository toursRepository;
//
//    @Mock
//    private TourModelAssembler tourModelAssembler;
//
//    @InjectMocks
//    private ToursController toursController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testFindTourById_ExistingTour() {
//        int tourId = 1;
//        Tour tour = new Tour();
//        tour.setId(tourId);
//        when(toursRepository.findById(tourId)).thenReturn(Optional.of(tour));
//
//        EntityModel<Tour> entity = toursController.findTourById(tourId);
//
//        verify(tourModelAssembler, times(1)).toModel(tour);
//    }
//
//    @Test
//    public void testFindTourById_NonExistingTour() {
//        int tourId = 1;
//        when(toursRepository.findById(tourId)).thenReturn(Optional.empty());
//
//        try {
//            toursController.findTourById(tourId);
//        } catch (TourNotFoundException e) {
//            assertEquals("Could not find tour with id " + tourId, e.getMessage());
//        }
//    }
}
