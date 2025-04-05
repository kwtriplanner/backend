package capstone.triplanner.review;

import capstone.triplanner.review.DTO.ReviewRequestDTO;
import capstone.triplanner.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final TokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<String> saveReview(@RequestHeader("Authorization") String authHeader, @RequestBody ReviewRequestDTO reviewDTO) {
        String username = tokenProvider.getUsernameByBearer(authHeader);

        reviewService.createReview(username, reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("review created");
    }
}
