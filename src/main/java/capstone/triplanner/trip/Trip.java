package capstone.triplanner.trip;

import capstone.triplanner.member.Member;
import capstone.triplanner.tripPlace.TripPlace;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
public class Trip {
    @Id
    @GeneratedValue
    @Column(name = "TRIP_ID")
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TripPlace> tripPlaces;

}
