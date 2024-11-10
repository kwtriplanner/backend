package capstone.triplanner.review;

import capstone.triplanner.member.Member;
import capstone.triplanner.place.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
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
}
