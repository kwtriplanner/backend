package capstone.triplanner.review.DTO;

import capstone.triplanner.member.Member;
import capstone.triplanner.place.Place;
import capstone.triplanner.review.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDTO {
    private Long id;
    private Member member;
    private Place place;
    private String content;
    private int rating;

    public static ReviewDTO from(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .member(review.getMember())
                .place(review.getPlace())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }
}