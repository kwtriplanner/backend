package capstone.triplanner.tripPlace;

import capstone.triplanner.place.Place;
import capstone.triplanner.trip.Trip;
import jakarta.persistence.*;

@Entity
public class TripPlace {
    @Id
    @GeneratedValue
    @Column(name = "TRIP_PLACE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRIP_ID")
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    @Column(name = "PLACE_ORDER")
    private int order;
}
