package fr.soat.pois.asked_features;

import fr.soat.pois.domain.model.World;
import fr.soat.pois.infrastructure.input.ParseCSV;
import fr.soat.pois.infrastructure.mapper.PoiMapper;
import fr.soat.pois.use_case.CreateWorld;
import fr.soat.pois.use_case.FindTheDensestArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FindTheDensestAreaTest {

        @Autowired
        private ParseCSV parseCSV;

        @Autowired
        private PoiMapper poiMapper;

        @Test
        void should_find_the_densest_area() {
            CreateWorld createWorld = new CreateWorld(parseCSV, poiMapper);
            World world = createWorld.execute("pois.csv");
            FindTheDensestArea findTheDensestArea = new FindTheDensestArea();
            assertThat(findTheDensestArea.execute(2, world))
                    .isEqualTo("[Zone{minLat:-2.5, maxLat:-2.0, minLong:38.0, maxLong:38.5}, Zone{minLat:6.5, maxLat:7.0, minLong:-7.0, maxLong:-6.5}]");
        }
}
