package gutenbergproject4cbu.demo.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class Converter implements AttributeConverter<Object, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // Handle exception appropriately
            throw new RuntimeException("Error converting to JSON", e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (JsonMappingException e) {
            // Handle exception appropriately
            throw new RuntimeException("Error converting to Object", e);
        } catch (JsonProcessingException e) {
            // Handle exception appropriately
            throw new RuntimeException("Error converting to Object", e);
        }
    }
}
