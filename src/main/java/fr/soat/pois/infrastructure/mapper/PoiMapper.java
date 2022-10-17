package fr.soat.pois.infrastructure.mapper;

import fr.soat.pois.domain.model.Poi;
import fr.soat.pois.infrastructure.model.PoiInput;
import org.springframework.stereotype.Component;

@Component
public class PoiMapper {
    public Poi map(PoiInput poiInput) {
        return Poi.Builder()
                .id(poiInput.id())
                .latitude(poiInput.latitude())
                .longitude(poiInput.longitude())
                .build();
    }
}
