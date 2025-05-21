package capstone.triplanner.plan.DTO;

import capstone.triplanner.place.DTO.PlaceDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PlanRequestDTO {
    private List<PlaceDTO> places;
}
