package fr.soat.pois.use_case;

import fr.soat.pois.domain.model.Poi;
import fr.soat.pois.domain.model.World;

public class AddPoiInZone {
    public World execute(Poi poi, World world) {
        return world.addPoi(poi);
    }
}
