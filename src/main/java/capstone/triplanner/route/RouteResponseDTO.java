package capstone.triplanner.route;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RouteResponseDTO {
    private List<String> attractions;
    private List<String> hotels;
    private List<String> restaurants;
}
