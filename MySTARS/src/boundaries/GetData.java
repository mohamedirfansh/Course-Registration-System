package boundaries;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GetData {

    // 1. Pull Data
    // Read data from a given location where each entity should have a FINAL filePath to its exclusive 'table',
    // which can be changed within the entity class.

    // GetData will read the datasource (txt file) and return a hashmap for easly lookup.
    // Data pertaining to each entity should have a unique PK - E.g. Users: user_id ; Staff: staff_id

    public HashMap pullData(String filePath) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null) {
            // Split by : to get Key: Value
            String[] entry = line.split(":", 2);
            if (entry.length >= 2) {
                String key = entry[0];
                String value = entry[1];
                map.put(key, value);
            } else {
                System.out.println("Key has no mapped value");
            }
        }
        // Return HashMap
        return map;
    }

    // 2. Update Data
    // After pulling data as a hashmap and editing selected entries based on user_id or staff_id
    // Pass updated hashmap<String, String> into saveData() below to update our text 'database'

    public void saveData(HashMap<String, String> newData, String filePath) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            for (Map.Entry<String, String> entry: newData.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            writer.flush(); // Flush stream to implement previous code and free up temp memory
        } catch (IOException e) {
            System.out.println("I/O Error when trying to write updated data to " + filePath);
        } finally {
            try {
                writer.close(); // Close writer
            } catch (Exception e) {
                System.out.println("Error when trying to close BufferedWriter: " + e.getMessage());
            }
        }
    }

}
