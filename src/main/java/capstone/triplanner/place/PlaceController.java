package capstone.triplanner.place;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/{placeName}")
    public ResponseEntity<PlaceDetailDTO> getPlaceDetails(@PathVariable("placeName") String placeName) {
        PlaceDetailDTO placeDetail = placeService.getPlaceDetail(placeName);
        return ResponseEntity.ok(placeDetail);
    }
}
