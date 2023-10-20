package java_ex.enum_ex;

public enum Enum_Ex {
    EAST, SOUTH, WEST, NORTH;

    static class Direction {
        static final Direction EAST = new Direction("EAST");
        static final Direction SOUTH = new Direction("SOUTH");
        static final Direction WEST = new Direction("WEST");
        static final Direction NORTH = new Direction("NORTH");

        private String name;

        public Direction(String name) {
            this.name = name;
        }
    }

    static abstract class Direction2 {

        static final Direction2 EAST = new Direction2("EAST") {
            @Override
            int dir() {
                return 0;
            }
        };
        static final Direction2 SOUTH = new Direction2("SOUTH") {
            @Override
            int dir() {
                return 1;
            }
        };
        static final Direction2 WEST = new Direction2("WEST") {
            @Override
            int dir() {
                return 2;
            }
        };
        static final Direction2 NORTH = new Direction2("NORTH") {
            @Override
            int dir() {
                return 3;
            }
        };

        private String name;

        public Direction2(String name) {
            this.name = name;
        }
        abstract int dir();

    }
}
