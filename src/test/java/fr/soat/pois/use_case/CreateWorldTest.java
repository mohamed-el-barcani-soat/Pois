package fr.soat.pois.use_case;

import fr.soat.pois.domain.model.Poi;
import fr.soat.pois.domain.model.World;
import fr.soat.pois.infrastructure.input.ParseCSV;
import fr.soat.pois.infrastructure.mapper.PoiMapper;
import fr.soat.pois.infrastructure.model.PoiInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CreateWorldTest {

    @Mock
    private ParseCSV parseCSV;

    @Mock
    private PoiMapper poiMapper;


    @Test
    void should_add_pois_and_create_6_zones() {
        CreateWorld createWorld = new CreateWorld(parseCSV, poiMapper);
        List<PoiInput> poisInput = List.of(
                new PoiInput("id1", -48.6, -37.7),
                new PoiInput("id2", -27.1, 8.4),
                new PoiInput("id3", 6.6, -6.9),
                new PoiInput("id4", -2.3, 38.3),
                new PoiInput("id5", 6.8, -6.9),
                new PoiInput("id6", -2.5, 38.3),
                new PoiInput("id7", 0.1, -0.1),
                new PoiInput("id8", -2.1, 38.1)
        );

        List<Poi> pois = List.of(
                Poi.Builder().id("id1").latitude(1.0).longitude(2.0).build(),
                Poi.Builder().id("id2").latitude(-27.1).longitude(8.4).build(),
                Poi.Builder().id("id3").latitude(6.6).longitude(-6.9).build(),
                Poi.Builder().id("id4").latitude(-2.3).longitude(38.3).build(),
                Poi.Builder().id("id5").latitude(6.8).longitude(-6.9).build(),
                Poi.Builder().id("id6").latitude(-2.5).longitude(38.3).build(),
                Poi.Builder().id("id7").latitude(0.1).longitude(-0.1).build(),
                Poi.Builder().id("id8").latitude(-2.1).longitude(38.1).build()
        );
        when(parseCSV.findAll(anyString())).thenReturn(poisInput);
        for (int i = 0; i < poisInput.size(); i++) {
            when(poiMapper.map(poisInput.get(i))).thenReturn(pois.get(i));
        }

        World world = createWorld.execute("pois.csv");

        assertNotNull(world);
        assertThat(world.getZones()).hasSize(6);
    }
}