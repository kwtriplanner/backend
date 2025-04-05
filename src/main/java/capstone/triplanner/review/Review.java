package capstone.triplanner.review;

import capstone.triplanner.member.Member;
import capstone.triplanner.place.Place;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long id;

    private int rating;

    @Column(length = 1000)
    private String content;

    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACE_ID", nullable = false)
    private Place place;

    @Builder
    public Review(int rating, String content, LocalDateTime createAt, Member member, Place place) {
        this.rating = rating;
        this.content = content;
        this.createAt = createAt;
        this.member = member;
        this.place = place;
    }
}
