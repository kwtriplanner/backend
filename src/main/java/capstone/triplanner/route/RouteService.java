package capstone.triplanner.route;

import capstone.triplanner.member.Member;
import capstone.triplanner.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RouteService {

    private final MemberRepository memberRepository;
    private final RouteRepository routeRepository;

    @Transactional
    public void saveRoute(String username, List<String> attractions, List<String> hotels, List<String> restaurants) {
        // 회원 찾기
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username: " + username));

        // 경로 생성
        Route route = new Route(attractions, hotels, restaurants, member);

        routeRepository.save(route);
    }
    @Transactional(readOnly = true)
    public List<RouteResponseDTO> getRoutesByUsername(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username"));

        List<Route> routes = routeRepository.findByMemberId(member.getId());

        routes.forEach(route -> {
            route.getAttractions().size();
            route.getHotels().size();
            route.getRestaurants().size();
        });

        // 여기서 LAZY 필드 접근해서 DTO 변환 -> 세션 열려있음
        return routes.stream()
                .map(route -> RouteResponseDTO.builder()
                        .attractions(route.getAttractions())  // LAZY -> 트랜잭션 안이니까 OK
                        .hotels(route.getHotels())
                        .restaurants(route.getRestaurants())
                        .build())
                .toList();
    }

    public void deleteRoute(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new EntityNotFoundException("There is no route with id " + routeId));
        routeRepository.delete(route);
    }


}
