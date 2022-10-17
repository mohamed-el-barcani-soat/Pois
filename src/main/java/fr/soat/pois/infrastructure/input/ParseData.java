package fr.soat.pois.infrastructure.input;

import fr.soat.pois.infrastructure.model.PoiInput;

import java.util.List;

public interface ParseData<String> {
    List<PoiInput> findAll(String file);
}
