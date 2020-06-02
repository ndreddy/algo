package enums;

public class EnumExample {
    enum Suit {SPADES, HEARTS, CLUBS, DIAMONDS}

//    Why can't I create an enum in an inner class in Java?
//    The Java compiler would synthetically generate the following class for you:

    /*final class Suit extends java.lang.Enum<Suit> {
        public static final Suit SPADES;
        public static final Suit HEARTS;
        public static final Suit CLUBS;
        public static final Suit DIAMONDS;
        private static final Suit[] $VALUES;
        public static Suit[] values();
        public static Suit valueOf(java.lang.String);
        private Suit();
    }*/


    public enum MessageType {

    }
}
