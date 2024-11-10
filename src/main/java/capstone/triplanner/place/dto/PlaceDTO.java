package capstone.triplanner.place.dto;

import capstone.triplanner.place.Place;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceDTO {
    private final Long id;
    private final String name;
    private final String location;
    private final String category;
    private final String description;

    @Builder
    public PlaceDTO(Long id, String name, String location, String category, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.category = category;
        this.description = description;
    }

    public static PlaceDTO from(Place place) {
        return PlaceDTO.builder()
                .id(place.getId())
                .name(place.getName())
                .category(place.getCategory())
                .description(place.getDescription())
                .location(place.getLocation())
                .build();
    }

}
