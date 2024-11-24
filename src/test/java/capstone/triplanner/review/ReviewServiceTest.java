package capstone.triplanner.review;

import capstone.triplanner.member.Member;
import capstone.triplanner.member.MemberService;
import capstone.triplanner.member.dto.MemberDTO;
import capstone.triplanner.place.Place;
import capstone.triplanner.place.PlaceService;
import capstone.triplanner.place.dto.PlaceDTO;
import capstone.triplanner.review.DTO.ReviewDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PlaceService placeService;

    @Test
    @DisplayName("리뷰 ID를 이용하여 리뷰 조회")
    void findReviewById() {
        //given
        String content = "good";
        int rating = 5;
        Member member = createTestMember("first member");
        Place place = createTestPlace("first place");

        //when
        MemberDTO memberDTO = memberService.createMember(MemberDTO.from(member));
        PlaceDTO placeDTO = placeService.createPlace(PlaceDTO.from(place));
        ReviewDTO reviewDTO = reviewService.createReview(memberDTO.getId(), placeDTO.getId(), content, rating);
        ReviewDTO foundReviewDTO = reviewService.findReviewById(reviewDTO.getId());

        //then
        assertThat(content).isEqualTo(foundReviewDTO.getContent());
        assertThat(rating).isEqualTo(foundReviewDTO.getRating());
    }

    @Test
    @DisplayName("회원 id로 조회시 해당 회원의 리뷰 전부 출력")
    void findAllByMember_Id() {
        //given
        Member member = createTestMember("first member");
        Place firstPlace = createTestPlace("first place");
        Place secondPlace = createTestPlace("second place");
        Place thirdPlace = createTestPlace("third place");

        //when
        MemberDTO memberDTO = memberService.createMember(MemberDTO.from(member));

        PlaceDTO firstPlaceDTO = placeService.createPlace(PlaceDTO.from(firstPlace));
        PlaceDTO secondPlaceDTO = placeService.createPlace(PlaceDTO.from(secondPlace));
        PlaceDTO thirdPlaceDTO = placeService.createPlace(PlaceDTO.from(thirdPlace));

        reviewService.createReview(memberDTO.getId(), firstPlaceDTO.getId(), "first review", 1);
        reviewService.createReview(memberDTO.getId(), secondPlaceDTO.getId(), "second review", 2);
        reviewService.createReview(memberDTO.getId(), thirdPlaceDTO.getId(), "third review", 3);

        List<ReviewDTO> reviewsByMemberId = reviewService.findAllByMemberId(memberDTO.getId());

        //then
        assertThat(reviewsByMemberId.size()).isEqualTo(3);

    }

    @Test
    @DisplayName("장소 id로 조회시 해당 장소의 리뷰 전부 출력")
    void findAllByPlace_Id() {
        //given
        Member firstMember = createTestMember("first member");
        Member secondMember = createTestMember("second member");
        Member thirdMember = createTestMember("third member");
        Place place = createTestPlace("first place");

        //when
        MemberDTO firstMemberDTO = memberService.createMember(MemberDTO.from(firstMember));
        MemberDTO secondMemberDTO = memberService.createMember(MemberDTO.from(secondMember));
        MemberDTO thirdMemberDTO = memberService.createMember(MemberDTO.from(thirdMember));

        PlaceDTO placeDTO = placeService.createPlace(PlaceDTO.from(place));

        reviewService.createReview(firstMemberDTO.getId(), placeDTO.getId(), "first review", 1);
        reviewService.createReview(secondMemberDTO.getId(), placeDTO.getId(), "second review", 2);
        reviewService.createReview(thirdMemberDTO.getId(), placeDTO.getId(), "third review", 3);

        List<ReviewDTO> allByPlaceId = reviewService.findAllByPlaceId(placeDTO.getId());

        //then
        assertThat(allByPlaceId.size()).isEqualTo(3);

    }


    @Test
    void updateReview() {
    }

    @Test
    void deleteReview() {
    }

    Member createTestMember(String name) {
        return Member.builder()
                .name(name)
                .username("kim")
                .password("1234")
                .build();
    }

    Place createTestPlace(String name) {
        return Place.builder()
                .name(name)
                .category("nature")
                .build();
    }
}