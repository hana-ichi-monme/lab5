package lab5.parsers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lab5.types.Chapter;
import lab5.types.Coordinates;
import lab5.types.SpaceMarine;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Custom serializer
 */
public class MarineSerializer extends StdSerializer<SpaceMarine> {

    public MarineSerializer() {
        this(null);
    }

    public MarineSerializer(Class<SpaceMarine> t) {
        super(t);
    }
    /**
     * Serialize Space Marine to json file (string)
     * @param value Space Marine, which need to parsing
     * @param jgen Generator json file (string)
     * @param provider Serializer Provider
     * @throws IOException Input/Output Exception
     * @throws JsonProcessingException Exception thrown when trying to read the json tree
     */
    @Override
    public void serialize(
            SpaceMarine value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("name", value.getName());
        Coordinates coordinates = value.getCoordinates();
        jgen.writeNumberField("coordinateX", coordinates.getX());
        jgen.writeNumberField("coordinateY", coordinates.getY());
        jgen.writeStringField("creationDate", value.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        jgen.writeNumberField("health", value.getHealth());
        jgen.writeNumberField("height", value.getHeight());
        jgen.writeStringField("category", value.getCategory().toString());
        jgen.writeStringField("meleeWeapon", value.getMeleeWeapon().toString());
        Chapter chapter = value.getChapter();
        jgen.writeStringField("chapterName", chapter.getName());
        jgen.writeStringField("chapterParentLegion", chapter.getParentLegion());
        jgen.writeEndObject();
    }
}