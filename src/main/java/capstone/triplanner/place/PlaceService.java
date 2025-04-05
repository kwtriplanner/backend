package capstone.triplanner.place;

import capstone.triplanner.review.DTO.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public PlaceDetailDTO getPlaceDetail(String placeName) {
        Place place = placeRepository.findByName(placeName)
                .orElseGet(() -> {
                    Place newPlace = new Place(placeName, "");
                    return placeRepository.save(newPlace);
                });
        List<ReviewDTO> reviewDTOs = place.getReviews().stream()
                .map(review -> ReviewDTO.builder()
                        .rating(review.getRating())
                        .content(review.getContent())
                        .createdAt(review.getCreateAt())
                        .build())
                .toList();
        return PlaceDetailDTO.builder()
                .placeName(placeName)
                .reviews(reviewDTOs)
                .build();
    }

}
