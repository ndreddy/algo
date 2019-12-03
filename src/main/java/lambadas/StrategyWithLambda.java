package lambadas;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@FunctionalInterface
interface Decoder {
    String decode(byte[] msgBody);
}

class AgentStateEvent implements Decoder {
    public String decode(byte[] msgBody) {
        return "Decode AgentStateEvent msg";
    }
}

class ConfigAgentEvent implements Decoder {
    public String decode(byte[] msgBody) {
        return "Decode ConfigAgentEvent msg";
    }
}

enum MessageType {

    AGENT_STATE_EVENT_TYPE(30, (byte[] msgBody) -> {
        return new AgentStateEvent().decode(msgBody);
    }),
    CONFIG_AGENT_EVENT_TYPE(237, (byte[] msgBody) -> {
        return new ConfigAgentEvent().decode(msgBody);
    });

    private static final ConcurrentMap<Integer, Decoder> map = new ConcurrentHashMap<>();

    static {
        //Create reverse hash map
        for (MessageType m : MessageType.values())
            map.put(m.getMsgId(), m.getDecoder());
    }


    private final int msgId;
    private final Decoder decoder;

    MessageType(int msgId, Decoder decoder) {
        this.msgId = msgId;
        this.decoder = decoder;
    }


    public int getMsgId() {
        return msgId;
    }

    public static Decoder getDecoder(int id) {
        return map.get(id);
    }

    public static boolean containsDecoder(int id) {
        return map.containsKey(id);
    }

    public Decoder getDecoder() {
        return decoder;
    }
}


public class StrategyWithLambda {

    public String  processEvent(int eventId){
        Decoder decoder = MessageType.getDecoder(eventId); // new instance is returned
        String msg = decoder.decode("EventBody".getBytes());
        return msg;
    }

    @Test
    public void testDecode(){
        String msg = new StrategyWithLambda().processEvent(30);
        System.out.println(msg);
        msg = new StrategyWithLambda().processEvent(237);
        System.out.println(msg);

    }
}
