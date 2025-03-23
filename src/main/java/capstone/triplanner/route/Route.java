package capstone.triplanner.route;

import capstone.triplanner.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "attractions", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "attraction_title")
    private List<String> attractions;

    @ElementCollection
    @CollectionTable(name = "hotels", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "hotel_title")
    private List<String> hotels;

    @ElementCollection
    @CollectionTable(name = "restaurants", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "restaurant_title")
    private List<String> restaurants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Route(List<String> attractions, List<String> hotels, List<String> restaurants, Member member) {
        this.attractions = attractions;
        this.hotels = hotels;
        this.restaurants = restaurants;
        this.member = member;
    }
}
