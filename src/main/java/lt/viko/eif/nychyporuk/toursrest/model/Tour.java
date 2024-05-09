package lt.viko.eif.nychyporuk.toursrest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tour entity with properties for the tour's name, list of visits,
 * duration, guide, and price. This class is annotated for persistence in a
 * relational database using JPA and for XML marshalling/unmarshalling using JAXB.
 * It extends the {@link BaseEntity} class, inheriting its ID field.
 */
@Entity
@Table(name = "tour")
public class Tour extends BaseEntity {
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = Visit.class,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "tour_visit",
            joinColumns = @JoinColumn(name = "Tour_id"),
            inverseJoinColumns = @JoinColumn(name = "visits_id")
    )
    private List<Visit> visits = new ArrayList<>();

    private int duration;

    @OneToOne(targetEntity = Guide.class, cascade = CascadeType.ALL)
    private Guide guide;

    private int price;


    /**
     * Default constructor.
     */
    public Tour() {
    }

    /**
     * Constructs a new Tour with specified name, duration, guide, and price.
     *
     * @param name     The name of the tour.
     * @param duration The duration of the tour in minutes.
     * @param guide    The guide leading the tour.
     * @param price    The price of the tour.
     */
    public Tour(String name, int duration, Guide guide, int price) {
        this.name = name;
        this.duration = duration;
        this.guide = guide;
        this.price = price;
    }

    /**
     * Returns a string representation of the tour, including its name, list of visits,
     * duration, guide, and price.
     *
     * @return A formatted string representing the tour.
     */
    @Override
    public String toString() {
        return String.format("Tour: \n" +
                "\tName: %s \n" +
                "\tVisits: %s" +
                "\tDuration: %d minutes\n" +
                "%s" +
                "\tPrice: %d \n", this.name, constructVisitsList(), this.duration, this.guide.toString(), this.price);
    }

    /**
     * Constructs a formatted string of the list of visits.
     *
     * @return A string representing the list of visits in the tour.
     */
    private String constructVisitsList() {
        StringBuilder result = new StringBuilder("\n");
        for (Visit v : this.visits) {
            result.append(v.toString());
        }
        return result.toString();
    }

    /**
     * Gets the name of the tour.
     *
     * @return The name of the tour.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tour.
     *
     * @param name The new name of the tour.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a visit to the list of visits.
     *
     * @param visit The visit to add.
     */
    public void addVisit(Visit visit) {
        this.visits.add(visit);
    }

    /**
     * Gets the list of visits included in the tour.
     *
     * @return The list of visits.
     */
    public List<Visit> getVisits() {
        return visits;
    }

    /**
     * Sets the list of visits included in the tour.
     * This method is annotated for JAXB to wrap the visits in a "visits" element,
     * with each visit represented as a "visit" element.
     *
     * @param visits The new list of visits.
     */

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    /**
     * Gets the duration of the tour in days.
     *
     * @return The duration of the tour.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the tour in days.
     *
     * @param duration The new duration of the tour.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the guide for the tour.
     *
     * @return The guide.
     */
    public Guide getGuide() {
        return guide;
    }

    /**
     * Sets the guide for the tour.
     *
     * @param guide The new guide for the tour.
     */
    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    /**
     * Gets the price of the tour.
     *
     * @return The price of the tour.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the tour.
     *
     * @param price The new price of the tour.
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
