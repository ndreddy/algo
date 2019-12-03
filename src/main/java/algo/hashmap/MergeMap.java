package algo.hashmap;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeMap {

    /**
     * * <pre> {@code
     *      * V oldValue = map.get(key);
     *      * V newValue = (oldValue == null) ? value :
     *      *              remappingFunction.apply(oldValue, value);
     *      * if (newValue == null)
     *      *     map.remove(key);
     *      * else
     *      *     map.put(key, newValue);
     *      * }</pre>
     */
    public void merge() {
        ConcurrentMap<String, String> agentMap = new ConcurrentHashMap<>();
        ConcurrentMap<String, String> configMap = new ConcurrentHashMap<>();
        configMap.forEach(
                (key, value) ->
                        agentMap.merge(key, value, (oldValue, newValue) -> {
                                    // 0 = Add, 1 = Change, 2 = Delete
                                    if (value.equals("Add") || value.equals("Change")) {
                                        return value;
                                    } else { // means (value == 2)
                                        return null; // removes the record from the map.
                                    }
                                }

                        ));
    }

    /**
     * Merge two maps with Java 8 Stream API in a way that values for common keys should be
     * the maximum of the values.
     *
     * @throws Exception
     */
    @Test
    public void test14() throws Exception {
        Map<String, Integer> m1 = new HashMap<>(); //Guava - ImmutableMap.of("a", 2, "b", 3);
        Map<String, Integer> m2 = new HashMap<>(); //Guava - ImmutableMap.of("a", 3, "c", 4);

        Map<String, Integer> mx = Stream.of(m1, m2)
                .map(Map::entrySet)          // converts each map into an entry set
                .flatMap(Collection::stream) // converts each set into an entry stream, then
                // "concatenates" it in place of the original set
                .collect(
                        Collectors.toMap(        // collects into a map
                                Map.Entry::getKey,   // where each entry is based
                                Map.Entry::getValue, // on the entries in the stream
                                Integer::max         // such that if a value already exist for
                                // a given key, the max of the old
                                // and new value is taken
                        )
                );
    }
}
