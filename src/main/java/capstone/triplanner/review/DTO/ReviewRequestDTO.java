package capstone.triplanner.review.DTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDTO {
    private String placeName;
    private int rating;
    private String content;
    private LocalDateTime createdAt;
}
