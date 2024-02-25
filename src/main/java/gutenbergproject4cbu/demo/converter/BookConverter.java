package gutenbergproject4cbu.demo.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BookConverter implements AttributeConverter<List<Map<String, Object>>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Map<String, Object>> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting list of maps to JSON string", e);
        }
    }

    @Override
    public List<Map<String, Object>> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) {
                return null;
            }
            return objectMapper.readValue(dbData, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to list of maps", e);
        }
    }
}
