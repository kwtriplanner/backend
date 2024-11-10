package capstone.triplanner.place;

import capstone.triplanner.place.dto.PlaceDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public PlaceDTO createPlace(PlaceDTO placeDTO) {
        Place place = Place.builder()
                .name(placeDTO.getName())
                .category(placeDTO.getCategory())
                .description(placeDTO.getDescription())
                .location(placeDTO.getLocation())
                .build();
        Place savedPlace = placeRepository.save(place);
        return PlaceDTO.from(savedPlace);
    }

    public PlaceDTO findPlaceById(Long id) {
        Place foundPlace = getPlaceById(id);
        return PlaceDTO.from(foundPlace);
    }

    public PlaceDTO findPlaceByName(String name) {
        Place foundPlace = placeRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("place with name " + name + " not found"));
        return PlaceDTO.from(foundPlace);
    }

    @Transactional
    public void deletePlace(Long id) {
        placeRepository.delete(getPlaceById(id));
    }

    @Transactional
    public void updatePlace(Long id, PlaceDTO placeDTO) {
        Place foundPlace = getPlaceById(id);
        foundPlace.update(placeDTO);
    }
    private Place getPlaceById(Long id) {
        return placeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("place with ID " + id + " not found"));
    }
}
