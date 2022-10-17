package fr.soat.pois.domain.model;

import fr.soat.pois.exception.IncorrectPoiException;

public class Poi{
    private String id;
    private Double latitude;
    private Double longitude;

    public String getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public static final PoiBuilder Builder(){
        return new PoiBuilder();
    }


    public static final class PoiBuilder {
        private String id;
        private Double latitude;
        private Double longitude;

        private PoiBuilder() {
        }

        public PoiBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PoiBuilder latitude(Double latitude) {
            if (latitude < Constant.POI_MIN_LATITUDE || latitude > Constant.POI_MAX_LATITUDE) {
                throw new IncorrectPoiException("Latitude must be between -90 and 90");
            }
            this.latitude = latitude;
            return this;
        }

        public PoiBuilder longitude(Double longitude) {
            if(longitude < Constant.POI_MIN_LONGITUDE || longitude > Constant.POI_MAX_LONGITUDE){
                throw new IncorrectPoiException("Longitude must be between -180 and 180");
            }
            this.longitude = longitude;
            return this;
        }

        public Poi build() {
            Poi poi = new Poi();
            poi.id = this.id;
            poi.latitude = this.latitude;
            poi.longitude = this.longitude;
            return poi;
        }
    }
}
