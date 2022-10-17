package fr.soat.pois.asked_features;

import fr.soat.pois.domain.model.World;
import fr.soat.pois.infrastructure.input.ParseCSV;
import fr.soat.pois.infrastructure.mapper.PoiMapper;
import fr.soat.pois.use_case.ComputePOIsInZone;
import fr.soat.pois.use_case.CreateWorld;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ComputePOIsInZoneTest {

    @Autowired
    private ParseCSV parseCSV;

    @Autowired
    private PoiMapper poiMapper;

    @Test
    void compute_pois_number_in_zone_with_min_lat_6_and_half_and_min_lon_7_from_pois_csv_should_return_2() {
        CreateWorld createWorld = new CreateWorld(parseCSV, poiMapper);
        World world = createWorld.execute("pois.csv");
        ComputePOIsInZone computePOIsInZone = new ComputePOIsInZone();
        Integer result = computePOIsInZone.execute(6.5, -7.0, world);
        assertEquals(2, result);
    }
}