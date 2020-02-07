package streams;

import java.util.HashMap;
import java.util.Map;

public class SumStream {

    public int getNumberInQueue(Integer skillNo) {
        Map<Integer, Integer> map = new HashMap();
        return map.entrySet().stream()
                .filter(entry -> entry.getKey().equals(skillNo))
                .mapToInt(entry -> entry.getValue())
                .sum();
    }
}
