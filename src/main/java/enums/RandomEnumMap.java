package enums;

import java.util.Map;

public enum RandomEnumMap {

    StartHere("Start Here"),
    StopHere("Stop Here");

    private final String strVal;
    private static  Map<String, RandomEnumMap> strValMap;

     RandomEnumMap(String strVal) {
        this.strVal = strVal;
    }

    public static RandomEnumMap getEnum(String strVal) {
        if(!strValMap.containsKey(strVal)) {
            throw new IllegalArgumentException("Unknown String Value: " + strVal);
        }
        return strValMap.get(strVal);
    }


    static {
       /* final Map<String, RandomEnumMap> tmpMap = Maps.newHashMap();
        for(final RandomEnum en : RandomEnum.values()) {
            tmpMap.put(en.strVal, en);
        }*/
//        strValMap = ImmutableMap.copyOf(tmpMap);
    }

    @Override
    public String toString() {
        return strVal;
    }
}
