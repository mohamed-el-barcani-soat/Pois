package fr.soat.pois.infrastructure.input;

import fr.soat.pois.infrastructure.input.ParseCSV;
import fr.soat.pois.exception.FileNotFoundException;
import fr.soat.pois.infrastructure.input.ParseData;
import fr.soat.pois.infrastructure.model.PoiInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParseCSVTest {

    private ParseData parseCSV;

    @BeforeEach
    void setUp() {
        parseCSV = new ParseCSV();
    }

    @Test
    void when_parse_bad_file_should_throw_URISyntaxException() {
        var exception = assertThrows(FileNotFoundException.class, () -> parseCSV.findAll("bad_input.csv"));

        assertThat(exception.getMessage()).isEqualTo("File not found");
    }

    @Test
    void when_parse_correct_file_should_return_list_of_pois() throws IOException, URISyntaxException {
        List<PoiInput> pois = parseCSV.findAll("input.csv");
        List<PoiInput> expectedPOIs = List.of(
                new PoiInput("id1", -48.6, -37.7),
                new PoiInput("id2", -27.1, 8.4),
                new PoiInput("id3", 6.6, -6.9),
                new PoiInput("id4", -2.3, 38.3),
                new PoiInput("id5", 6.8, -6.9),
                new PoiInput("id6", -2.5, 38.3),
                new PoiInput("id7", 0.1, -0.1),
                new PoiInput("id8", -2.1, 38.1)
        );
        assertThat(pois).hasSize(8);
        pois.forEach(poi -> assertThat(expectedPOIs).contains(poi));
    }
}
