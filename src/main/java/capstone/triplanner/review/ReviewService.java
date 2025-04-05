package capstone.triplanner.review;

import capstone.triplanner.member.Member;
import capstone.triplanner.member.MemberRepository;
import capstone.triplanner.place.Place;
import capstone.triplanner.place.PlaceRepository;
import capstone.triplanner.review.DTO.ReviewDTO;
import capstone.triplanner.review.DTO.ReviewRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public void createReview(String username, ReviewRequestDTO reviewDTO) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
        Place place = placeRepository.findByName(reviewDTO.getPlaceName())
                .orElseGet(() -> {
                    Place newPlace = new Place(reviewDTO.getPlaceName(), "");
                    return placeRepository.save(newPlace);
                });

        Review review = Review.builder()
                .rating(reviewDTO.getRating())
                .content(reviewDTO.getContent())
                .createAt(reviewDTO.getCreatedAt())
                .member(member)
                .place(place)
                .build();

        member.getReviews().add(review);
        place.getReviews().add(review);
        reviewRepository.save(review);
    }

    public List<Review> getReviewsByMember(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return reviewRepository.findByMember(member);
    }

    public List<Review> getReviewsByPlace(String placeName) {
        Place place = placeRepository.findByName(placeName)
                .orElseThrow(() -> new RuntimeException("장소를 찾을 수 없습니다."));

        return reviewRepository.findByPlace(place);
    }

    @Transactional
    public void deleteReview(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("해당 리뷰를 찾을 수 없습니다."));

        if (!review.getMember().getUsername().equals(username)) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        reviewRepository.delete(review);
    }
}
