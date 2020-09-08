package json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class JacksonConverter {

    @Test
    public void test() throws Exception {

        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Car> cars = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {
        });
        assertNotNull(cars);
    }
}
