package capstone.triplanner.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

}
