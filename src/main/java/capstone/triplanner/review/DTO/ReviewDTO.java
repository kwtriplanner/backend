package capstone.triplanner.review.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewDTO {
    private int rating;
    private String content;
    private LocalDateTime createdAt;
}
