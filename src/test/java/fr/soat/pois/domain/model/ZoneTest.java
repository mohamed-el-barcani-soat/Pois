package fr.soat.pois.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZoneTest {

    @Test
    void when_add_poi_should_compute_correct_zone_frontier() {
        Zone zone = Zone.fromPoi(Poi.Builder()
                .id("poi1")
                .latitude(1.1)
                .longitude(5.2)
                .build());
        assertEquals(1.0, zone.getMinLat());
        assertEquals(1.5, zone.getMaxLat());
        assertEquals(5.0, zone.getMinLong());
        assertEquals(5.5, zone.getMaxLong());
    }

}