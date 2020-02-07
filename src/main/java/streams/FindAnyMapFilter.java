package streams;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FindAnyMapFilter {

    ConcurrentHashMap<Integer, SkillGroupListener> skillGroupListeners;


    public SkillGroupListener getSkillGroupListener(int skillGroupNumber) {

        Optional<SkillGroupListener> result = skillGroupListeners.entrySet().parallelStream()
                .filter(x -> x.getKey().equals(skillGroupNumber))
                .map(Map.Entry::getValue)
                .findAny();
        return result.orElse(null);
    }

    private class SkillGroupListener {

    }


}
