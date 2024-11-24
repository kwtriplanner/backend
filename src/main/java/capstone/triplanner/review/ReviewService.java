package capstone.triplanner.review;

import capstone.triplanner.member.Member;
import capstone.triplanner.member.MemberRepository;
import capstone.triplanner.place.Place;
import capstone.triplanner.place.PlaceRepository;
import capstone.triplanner.review.DTO.ReviewDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;


    @Transactional
    public ReviewDTO createReview(Long memberId, Long placeId, String content, int rating) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("Member with id " + memberId + " not found")
        );
        Place place = placeRepository.findById(placeId).orElseThrow(
                () -> new EntityNotFoundException("Place with id " + placeId + " not found")
        );
        Review review = Review.builder()
                .member(member)
                .place(place)
                .content(content)
                .rating(rating)
                .build();
        Review savedReview = reviewRepository.save(review);
        return ReviewDTO.from(savedReview);
    }

    public ReviewDTO findReviewById(Long id) {
        Review review = getReviewById(id);
        return ReviewDTO.from(review);
    }

    public List<ReviewDTO> findAllByMemberId(Long id) {
        List<Review> reviews = reviewRepository.findAllByMember_Id(id);
        return reviews.stream()
                .map(ReviewDTO::from)
                .toList();
    }

    public List<ReviewDTO> findAllByPlaceId(Long id) {
        List<Review> reviews = reviewRepository.findAllByPlace_Id(id);
        return reviews.stream()
                .map(ReviewDTO::from)
                .toList();
    }

    @Transactional
    public ReviewDTO updateReview(ReviewDTO reviewDTO) {
        Review review = getReviewById(reviewDTO.getId());
        review.update(reviewDTO.getContent(), reviewDTO.getRating());
        return ReviewDTO.from(review);
    }

    @Transactional
    public void deleteReviewById(Long id) {
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }
    private Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("review with id " + id + " not found"));
    }



}
