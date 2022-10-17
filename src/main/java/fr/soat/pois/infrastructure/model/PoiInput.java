package fr.soat.pois.infrastructure.model;

public record PoiInput(
        String id,
        Double latitude,
        Double longitude
) {
}
