package capstone.triplanner.review;

import capstone.triplanner.member.Member;
import capstone.triplanner.place.Place;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="REVIEW_ID")
    private Long id;

    private String content;
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    @Builder
    public Review(String content, int rating, Member member, Place place) {
        this.content = content;
        this.rating = rating;
        this.member = member;
        this.place = place;
    }

    public void update(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }
}
