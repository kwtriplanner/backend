package capstone.triplanner.place;

import capstone.triplanner.place.dto.PlaceDTO;
import capstone.triplanner.review.Review;
import capstone.triplanner.tripPlace.TripPlace;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_ID")
    private Long id;

    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TripPlace> tripPlaces = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    private String name;
    private String category;

    @Builder
    public Place(String name, String location, String category, String description) {
        this.name = name;
        this.category = category;
    }

    public void update(PlaceDTO placeDTO) {
        this.name = placeDTO.getName();
        this.category = placeDTO.getCategory();
    }
}
