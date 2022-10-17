package fr.soat.pois.use_case;

import fr.soat.pois.domain.model.Poi;
import fr.soat.pois.domain.model.World;
import fr.soat.pois.domain.model.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddPoiInZoneTest {

    private AddPoiInZone addPoiInZone;

    @BeforeEach
    void setUp() {
        addPoiInZone = new AddPoiInZone();
    }

    @Test
    void when_zone_exists_in_world_should_return_world_with_zone_with_new_poi_added_to_olds_ones() {
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

        assertThat(world.getZones().get(zone.getId()).getPois()).hasSize(1);

        world = addPoiInZone.execute(poi2, world);

        assertThat(world.getZones().get(zone.getId()).getPois()).hasSize(2);
    }

    @Test
    void when_add_poi_to_inexistent_zone_eshould_return_world_with_zone_with_new_poi_in_new_zone() {
        Poi poi1 = Poi.Builder()
                .id("poi1")
                .latitude(1.1)
                .longitude(5.2)
                .build();
        World world = new World();
        world = addPoiInZone.execute(poi1, world);

        assertThat(world.getZones()).hasSize(1);
        assertThat(world.getZones().get("1.0|1.5|5.0|5.5").getPois()).hasSize(1);
    }
}