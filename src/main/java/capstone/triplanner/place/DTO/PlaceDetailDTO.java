package capstone.triplanner.place;

import capstone.triplanner.review.DTO.ReviewDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PlaceDetailDTO {
    private String placeName;
    private List<ReviewDTO> reviews;
}
