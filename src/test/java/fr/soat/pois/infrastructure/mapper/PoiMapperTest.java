package fr.soat.pois.infrastructure.mapper;

import fr.soat.pois.exception.IncorrectPoiException;
import fr.soat.pois.infrastructure.model.PoiInput;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PoiMapperTest {

    @Test
    void map_from_input_to_domain_should_success() {
        var poiInput = new PoiInput("id", 1.0, 2.0);
        var poi = new PoiMapper().map(poiInput);
        assertThat(poi.getId()).isEqualTo("id");
        assertThat(poi.getLatitude()).isEqualTo(1.0);
        assertThat(poi.getLongitude()).isEqualTo(2.0);
        assertThat(poi.getLatitude()).isBetween(-90.0, 90.0);
    }

    @Test
    void when_lat_is_less_than_minus_90_or_bigger_than_90_should_throw_exception(){
        var poiInput = new PoiInput("id", 181.0, 2.0);
        Exception exception = assertThrows(IncorrectPoiException.class, () -> new PoiMapper().map(poiInput));
        assertThat(exception.getMessage()).isEqualTo("Latitude must be between -90 and 90");
        var poiInput2 = new PoiInput("id", -181.0, 2.0);
        exception = assertThrows(IncorrectPoiException.class, () -> new PoiMapper().map(poiInput2));
        assertThat(exception.getMessage()).isEqualTo("Latitude must be between -90 and 90");
    }
}