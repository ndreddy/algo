package lambadas;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructorReferences {
   public void start(Stage stage) {
      List<String> labels = Arrays.asList("Ok", "Cancel", "Yes", "No", "Maybe");
      List<Button> buttonList = labels.stream().map(Button::new).collect(Collectors.toList());

      Button[] buttonArray = labels.stream().map(Button::new).toArray(Button[]::new);

   }

   @FunctionalInterface
   public interface Decoder {
      String decode(byte [] msgBody);
   }


   static class OpenConf implements Decoder {

      @Override
      public String decode(byte[] msgBody) {
         System.out.println("OpenConf Message Decoded");
         return "OpenConf Message Decoded";
      }
   }

   public enum  MessageType {

      //Behind the scenes, the MessageType constructor receives an object of some class that
      //implements Decoder interface.
      // Invoking the decode method on that object executes
      //the body of the lambda expression.
      OPEN_CONF(4, (byte[] msgBody) -> {
         return new OpenConf().decode(msgBody);
      });

      private final int msgId;
      private final Decoder decoder;

      MessageType(int msgId, Decoder decoder) {
         this.msgId = msgId;
         this.decoder = decoder;
      }
   }


   public static void main(String[] args) {
      // Got openConf message, decode it.
      MessageType.OPEN_CONF.decoder.decode(new byte[1]);
   }
}

