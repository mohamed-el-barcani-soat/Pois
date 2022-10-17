package fr.soat.pois.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    void should_return_world_instance() {
        World world = new World();
        assertNotNull(world);
    }

    @Test
    void should_return_world_instance_with_zone() {
        World world = new World().addZone(new Zone(1.0, 1.5, 5.0, 5.5));
        assertNotNull(world);
        assertNotNull(world.getZones());
        assertEquals(1, world.getZones().size());
    }

    @Test
    void should_return_world_instance_with_zone_with_poi() {
        Poi poi = Poi.Builder()
                .id("poi1")
                .latitude(1.1)
                .longitude(5.2)
                .build();
        World world = new World().addPoi(poi);
        assertNotNull(world);
        assertNotNull(world.getZones());
        assertEquals(1, world.getZones().size());
        assertEquals(1, world.getZones().get("1.0|1.5|5.0|5.5").getPois().size());
    }

    @Test
    void should_return_world_instance_with_zone_with_poi_added_to_olds_ones() {
        Poi poi1 = Poi.Builder()
                .id("poi1")
                .latitude(1.1)
                .longitude(5.2)
                .build();
        Poi poi2 = Poi.Builder()
                .id("poi1")
                .latitude(1.1)
                .longitude(5.2)
                .build();
        Zone zone = new Zone(1.0, 1.5, 5.0, 5.5);
        zone.getPois().add(poi1);
        World world = new World().addZone(zone);

        assertEquals(1, world.getZones().get(zone.getId()).getPois().size());

        world.addPoi(poi2);

        assertEquals(2, world.getZones().get(zone.getId()).getPois().size());
    }
}