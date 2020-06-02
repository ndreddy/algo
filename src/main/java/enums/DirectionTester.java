package enums;

public class DirectionTester {
    public enum Direction {
        NORTH(0, -1),
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0);

        private int dx, dy;

        // 'constructor'
        private Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        private Direction opposite;

        static {
            NORTH.opposite = SOUTH;
            EAST.opposite = WEST;
            SOUTH.opposite = NORTH;
            WEST.opposite = EAST;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }

        public Direction getOpposite() {
            return opposite;
        }
    }

    static class Bot {
        int x;
        int y;

        public Bot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(Direction dir) {
            x += dir.getDx();
            y += dir.getDy();
        }

        @Override
        public String toString() {
            return "Bot{" + "x=" + x + ", y=" + y + '}';
        }
    }

    public static void main(String[] args) {
        Bot robby = new Bot(0, 0);

        Direction myDir = Direction.WEST;

        System.out.println(myDir.name());
        System.out.println(myDir.ordinal());

        robby.move(Direction.NORTH);
        robby.move(Direction.NORTH);
        robby.move(Direction.EAST);
        robby.move(Direction.EAST);
        robby.move(Direction.SOUTH);

        System.out.println(robby); // to 2, -1

        String[] moves = {"north", "east", "south", "south", "west", "west", "west"};

        for (String each : moves) {
            robby.move(Direction.valueOf(each.toUpperCase()));
        }

        System.out.println(robby); // back to 0,0


        robby.move(myDir);
        if (robby.x < 0) robby.move(myDir.getOpposite());

        System.out.println(robby); // did not move


    }

}
