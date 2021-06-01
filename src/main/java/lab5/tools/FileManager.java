package lab5.tools;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import lab5.types.SpaceMarine;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Save/load collection
 */
public class FileManager {
    private final ObjectMapper mapper = new ObjectMapper();
    private String env;
    public FileManager(String env){
        this.env = env;
    }
    /**
     * Read file JSON
     * @return Collection based on the read JSON file
     */
    public Deque<SpaceMarine> read() {
        if (System.getenv(env) != null) {
            try {
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
                mapper.findAndRegisterModules();
                mapper.enableDefaultTyping();
                InputStream in = new FileInputStream(new File(System.getenv().get(env)));
                Reader reader = new InputStreamReader(in);
                int symbol = reader.read();
                String data = "";
                while(symbol != -1) {
                    data = data.concat(String.valueOf((char) symbol));
                    symbol = reader.read();
                }
                return mapper.readValue(data, new TypeReference<ArrayDeque<SpaceMarine>>() {});
            } catch (FileNotFoundException e) {
                ConsoleManager.printError("File with name " + System.getenv().get(env) + " not found");
            } catch (IOException e) {
                ConsoleManager.printError("Unexpected exception");
            }
        } else {
            ConsoleManager.printError("Environment variable with name " + env + " not found");
        }
        return new ArrayDeque<>();
    }

    /**
     * Write collection to file JSON
     * @param collection Some Collection
     */
    public void write(Deque<SpaceMarine> collection) {
        if (System.getenv().get(env) != null) {
            try {
                Writer writer = new BufferedWriter(new FileWriter(new File(System.getenv().get(env))));
                String jsonCollection = "[";
                for (SpaceMarine marine: collection) {
                    jsonCollection = jsonCollection.concat(mapper.writeValueAsString(marine) + ",");
                }
                jsonCollection = jsonCollection.substring(0, jsonCollection.length()-1) + "]";
                writer.write(jsonCollection);
                writer.close();
            } catch (FileNotFoundException e) {
                ConsoleManager.printError("File with name" + System.getenv().get(env) + "not found");
            } catch (JsonGenerationException | JsonMappingException e) {
                ConsoleManager.printError("Wrong parsing to JSON");
            } catch (IOException e) {
                ConsoleManager.printError("Unexpected exception");
            }
        } else {
            ConsoleManager.printError("Environment variable with name " + env + " not found");
        }
    }
}
