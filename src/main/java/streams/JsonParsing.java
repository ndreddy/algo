package streams;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonParsing {


    @Test
    public void parse() throws Exception {
        String json = "{\"updatetime\":\"2019-06-18T07:14:51.946Z\",\n" +
                " \"type\":\"response\",\n" +
                " \"response\":\n" +
                "\n" +
                "{\"results\":\n" +
                "    {\"aesConnection\":\n" +
                "        [{\"id\":1,\"acd\":null,\"aesServer\":\"10.10.10.20:12345;10.10.20.20:1234\",\"cmLogin\":\"\",\"cmServer\":\"\",\"ctiLogin\":\"c2\",\"enabled\":true,\"lastUpdate\":\"2019-06-18T12:24:56.101Z\",\"name\":\"PG1A\",\"service\":\"5003\",\"status\":\"Out of Service\"}\n" +
                "        ]},\n" +
                "    \"resultInfo\":{\"aesConnection\":{\"totalElements\":1}},\"status\":true}\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode aesConn = root.findValue("aesConnection");

        if (aesConn.isArray()) {
            ArrayNode arrayNode = (ArrayNode) aesConn;

            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode n = arrayNode.get(i);

                String val = n.findValue("service").asText();
                Iterator<Map.Entry<String, JsonNode>> itr = n.fields();
                Iterable<Map.Entry<String, JsonNode>> it = () -> itr;
                Stream<Map.Entry<String, JsonNode>> targetStream = StreamSupport.stream(it.spliterator(), false);
                Map map = targetStream.collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue().asText()));
//                map.forEach(System.out::println); // will not work
                map.forEach((k, v) -> System.out.println(k + " : " + v));
            }

        }


    }
}
