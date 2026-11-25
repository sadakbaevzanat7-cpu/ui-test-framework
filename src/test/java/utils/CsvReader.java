package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    /** testdata ішіндегі CSV файлды (';' бөлгіш, бірінші жол – тақырып) оқиды. */
    public static Object[][] read(String resourceName) {
        List<Object[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                CsvReader.class.getClassLoader().getResourceAsStream(resourceName), StandardCharsets.UTF_8))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) rows.add(line.split(";", -1));
            }
        } catch (Exception e) {
            throw new RuntimeException("CSV оқылмады: " + resourceName, e);
        }
        return rows.toArray(new Object[0][]);
    }
}
