import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    private final String filePath = "src/input.txt";

    public String[] readLines() {
        EArrayList<String> lines = new EArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        return lines.toArray(new String[0]);
    }
}
