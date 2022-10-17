package fr.soat.pois.infrastructure.input;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.soat.pois.exception.FileNotFoundException;
import fr.soat.pois.infrastructure.model.PoiInput;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParseCSV implements ParseData<String> {

    public List<PoiInput> findAll(String file) {
        Path filePath = getPath(file);
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        try (Reader reader = Files.newBufferedReader(filePath)) {
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(parser);

            try (CSVReader csvReader = csvReaderBuilder.build()) {
                return createPOIs(csvReader.readAll());
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
    }

    private List<PoiInput> createPOIs(List<String[]> allData) {
        List<PoiInput> pois = new ArrayList<>();
        allData.forEach(data -> pois.add(new PoiInput(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]))));
        return pois;
    }

    private static Path getPath(String file) {
        var resource = ClassLoader.getSystemResource(file);
        if (resource == null) {
            throw new FileNotFoundException("File not found");
        }
        try {
            return Paths.get(resource.toURI());
        } catch (URISyntaxException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}
