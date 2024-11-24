package capstone.triplanner.place;

import capstone.triplanner.place.dto.PlaceDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;
    @Test
    @DisplayName("장소 생성 후 조회시 이름과 위치 비교")
    void createAndFindPlaceById() {
        //given
        Place testPlace = createTestPlace();

        //when
        PlaceDTO placeDTO = placeService.createPlace(PlaceDTO.from(testPlace));
        PlaceDTO foundPlaceDTO = placeService.findPlaceById(placeDTO.getId());

        //then
        assertThat(foundPlaceDTO.getName()).isEqualTo(testPlace.getName());
        assertThat(foundPlaceDTO.getLocation()).isEqualTo(testPlace.getLocation());

    }

    @Test
    @DisplayName("이름으로 조회 후 이름과 위치 비교")
    void findPlaceByName() {
        //given
        Place testPlace = createTestPlace();

        //when
        PlaceDTO placeDTO = placeService.createPlace(PlaceDTO.from(testPlace));
        PlaceDTO foundPlace = placeService.findPlaceByName(testPlace.getName());

        //then
        assertThat(foundPlace.getName()).isEqualTo(testPlace.getName());
        assertThat(foundPlace.getLocation()).isEqualTo(testPlace.getLocation());

    }

    @Test
    @DisplayName("삭제 후 조회시 예외 발생")
    void deletePlace() {
        //given
        Place testPlace = createTestPlace();

        //when
        PlaceDTO placeDTO = placeService.createPlace(PlaceDTO.from(testPlace));
        placeService.deletePlace(placeDTO.getId());

        //then
        assertThatThrownBy(() -> placeService.findPlaceById(placeDTO.getId())).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void updatePlace() {
    }
    private Place createTestPlace() {
        return Place.builder()
                .name("광운대학교")
                .location("서울시")
                .category("자연")
                .description("광운대")
                .build();
    }
}