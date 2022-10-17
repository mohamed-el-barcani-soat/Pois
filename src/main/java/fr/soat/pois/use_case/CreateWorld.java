package fr.soat.pois.use_case;

import fr.soat.pois.domain.model.World;
import fr.soat.pois.infrastructure.input.ParseCSV;
import fr.soat.pois.infrastructure.mapper.PoiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@PropertySource("classpath:application.properties")
public class CreateWorld {

    private final ParseCSV parseCSV;
    private final PoiMapper poiMapper;

    public World execute(String fileName) {
        var poisInputList = parseCSV.findAll(fileName);
        var poisList = poisInputList.stream().map(poiMapper::map).toList();
        World world = new World();
        poisList.forEach(world::addPoi);
        return world;
    }
}
