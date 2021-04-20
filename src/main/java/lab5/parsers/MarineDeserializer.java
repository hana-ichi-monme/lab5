package lab5.parsers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lab5.types.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom deserializer
 */
public class MarineDeserializer extends StdDeserializer<SpaceMarine> {
    public MarineDeserializer() {
        this(null);
    }

    public MarineDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserialize json file (string) to Space Marine
     * @param jp Parser
     * @param ctxt Context
     * @return deserialized Space Marine
     * @throws IOException Input/Output Exception
     * @throws JsonProcessingException Exception thrown when trying to read the json tree
     */
    @Override
    public SpaceMarine deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String marineName = node.get("name").asText();
        double x = (double) (/*(DoubleNode) */node.get("coordinateX")).asDouble();
        long y = (long) (/*(LongNode) */node.get("coordinateY")).asLong();
        LocalDateTime creationDate = LocalDateTime.parse(node.get("creationDate").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Long health = (Long) (/*(LongNode) */node.get("health")).asLong();
        float height = (float) (/*(FloatNode) */node.get("height")).asDouble();
        String strCategory = node.get("category").asText();
        AstartesCategory category = null;
        for (AstartesCategory category1 : AstartesCategory.values()) {
            if (category1.toString().toLowerCase().equals(strCategory.toLowerCase()))
                category = category1;
        }
        String strMeleeWeapon = node.get("meleeWeapon").asText();
        MeleeWeapon meleeWeapon = null;
        for (MeleeWeapon meleeWeapon1 : MeleeWeapon.values()) {
            if (meleeWeapon1.toString().toLowerCase().equals(strMeleeWeapon.toLowerCase()))
                meleeWeapon = meleeWeapon1;
        }
        String chapterName = node.get("chapterName").asText();
        String chapterParLeg = node.get("chapterParentLegion").asText();

        return new SpaceMarine(marineName, new Coordinates(x, y), creationDate, health, height, category, meleeWeapon, new Chapter(chapterName, chapterParLeg));
    }
}

