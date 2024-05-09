package lt.viko.eif.nychyporuk.toursrest.validator;

import lt.viko.eif.nychyporuk.toursrest.model.Sight;
import lt.viko.eif.nychyporuk.toursrest.model.Tour;
import lt.viko.eif.nychyporuk.toursrest.model.Visit;
import org.springframework.stereotype.Component;

@Component
public class TourValidator {

    public boolean isValid(Tour tour) {
        if (tour == null) return false;

        if (tour.getName() == null || tour.getName().isEmpty()) return false;

        if (tour.getDuration() < 1) return false;

        if (tour.getPrice() < 1) return false;

        if (tour.getGuide() == null) return false;

        if (tour.getGuide().getFirstName() == null
                || tour.getGuide().getFirstName().isEmpty()) return false;

        if (tour.getGuide().getLastName() == null
                || tour.getGuide().getLastName().isEmpty()) return false;

        if (tour.getGuide().getExperience() < 1) return false;

        if (!tour.getVisits().isEmpty()) {
            for (Visit visit : tour.getVisits()) {
                if (visit.getCountry() == null
                        || visit.getCountry().isEmpty()) return false;

                if (visit.getCity() == null
                        || visit.getCity().isEmpty()) return false;

                if (visit.getDuration() < 1) return false;

                if (visit.getHotel().getName() == null
                        || visit.getHotel().getName().isEmpty()) return false;

                if (visit.getHotel().getAddress() == null
                        || visit.getHotel().getAddress().isEmpty()) return false;

                if (visit.getHotel().getStars() < 1) return false;

                if (!visit.getSights().isEmpty()) {
                    for (Sight sight : visit.getSights()) {
                        if (sight.getName() == null
                                || sight.getName().isEmpty()) return false;

                        if (sight.getDuration() < 1) return false;
                    }
                }
            }
        }

        return true;
    }
}
