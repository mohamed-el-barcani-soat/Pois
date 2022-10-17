package fr.soat.pois.use_case;

import fr.soat.pois.domain.model.World;
import fr.soat.pois.domain.model.Zone;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ComputePOIsInZone {

    public Integer execute(Double minLat, Double minLong, World world) {
        var zone = new Zone(minLat, minLat + 0.5, minLong, minLong + 0.5);
        var poisNumber = world.getZones().get(zone.getId()).getPois().size();
        log.info("The number of POIs in zone {} is {}", zone.getId(), poisNumber);
        return poisNumber;
    }

}
