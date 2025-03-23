package capstone.triplanner.route;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RouteController {
    private final RouteService routeService;

    @PostMapping("/plans")
    public ResponseEntity<?> saveRoute(@RequestBody Map<String, Object> plans) {
        String username = (String) plans.get("username");
        List<String> attractions = (List<String>) plans.getOrDefault("추천된관광지", List.of());
        List<String> hotels = (List<String>) plans.getOrDefault("추천된숙박", List.of());
        List<String> restaurants = (List<String>) plans.getOrDefault("추천된음식점", List.of());
        routeService.saveRoute(username, attractions, hotels, restaurants);
        return ResponseEntity.ok("Route saved successfully");
    }

    @GetMapping("/plans")
    public ResponseEntity<List<RouteResponseDTO>> getRoutes(@RequestParam("username") String username) {
        // 컨트롤러에서는 오직 DTO만 다룸
        return ResponseEntity.ok(routeService.getRoutesByUsername(username));
    }

    @DeleteMapping("/plans")
    public ResponseEntity<String> deleteRoute(@RequestParam("planId") Long planId) {
        routeService.deleteRoute(planId);
        return ResponseEntity.ok("route deleted");
    }
}
