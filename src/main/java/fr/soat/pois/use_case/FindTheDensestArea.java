package fr.soat.pois.use_case;

import fr.soat.pois.domain.model.World;
import fr.soat.pois.domain.model.Zone;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Log4j2
@Component
public class FindTheDensestArea {
    public String execute(Integer N, World world) {
        List<Zone> zoneList = new ArrayList<>(world.getZones().values());
        List<Zone> sortedList = zoneList
                .stream()
                .sorted(
                        Comparator.nullsLast(
                            Comparator.comparing((zone -> zone.getPois().size())))
                ).toList();
        log.info("The {} densest zones are: {}", N, sortedList.subList(zoneList.size() - N, zoneList.size()));
        return Arrays.toString(sortedList.subList(zoneList.size() - N, zoneList.size()).toArray());
    }
}
