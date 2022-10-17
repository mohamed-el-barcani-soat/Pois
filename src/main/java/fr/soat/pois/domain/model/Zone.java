package fr.soat.pois.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Zone {
    private final Double minLat;
    private final Double maxLat;
    private final Double minLong;
    private final Double maxLong;
    private final String id;

    private final List<Poi> pois;

    public Zone(Double minLat, Double maxLat, Double minLong, Double maxLong) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLong = minLong;
        this.maxLong = maxLong;
        this.id= minLat + "|" + maxLat + "|" + minLong + "|" + maxLong;
        this.pois = new ArrayList<>();
    }

    public List<Poi> getPois() {
        return pois;
    }

    public static Zone fromPoi(Poi poi) {
        var zoneMaxLat = Math.ceil(poi.getLatitude() * 2) / 2;
        var zoneMinLat = zoneMaxLat - 0.5;
        var zoneMaxLong = Math.ceil(poi.getLongitude() * 2) / 2;
        var zoneMinLong = zoneMaxLong - 0.5;
        return new Zone(zoneMinLat, zoneMaxLat, zoneMinLong, zoneMaxLong);
    }

    public void addPoi(Poi poi) {
        this.pois.add(poi);
    }

    public String getId() {
        return id;
    }

    public Double getMinLat() {
        return minLat;
    }

    public Double getMaxLat() {
        return maxLat;
    }

    public Double getMinLong() {
        return minLong;
    }

    public Double getMaxLong() {
        return maxLong;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "minLat:" + minLat +
                ", maxLat:" + maxLat +
                ", minLong:" + minLong +
                ", maxLong:" + maxLong +
                '}';
    }
}
