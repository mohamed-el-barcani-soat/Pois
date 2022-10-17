package fr.soat.pois.domain.model;

import java.util.HashMap;
import java.util.Map;

public class World {
    private final Map<String, Zone> zones;

    public World() {
        this.zones = new HashMap<>();
    }

    public World addZone(Zone zone) {
        this.zones.put(zone.getId(), zone);
        return this;
    }

    public World addPoi(Poi poi) {
        Zone poiZone = Zone.fromPoi(poi);
        if(!zones.containsKey(poiZone.getId())){
            poiZone.addPoi(poi);
            zones.put(poiZone.getId(), poiZone);
        } else {
            zones.get(poiZone.getId()).getPois().add(poi);
        }
        return this;
    }

    public Map<String, Zone> getZones() {
        return zones;
    }
}
