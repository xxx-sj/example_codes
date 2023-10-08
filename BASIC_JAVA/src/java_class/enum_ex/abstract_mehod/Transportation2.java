package java_class.enum_ex.abstract_mehod;

public enum Transportation2 {
    BUS(100) {
        @Override
        int fare() {
            return BASIC_FARE * 100;
        }
    },
    TRAIN(150) {
        @Override
        int fare() {
            return BASIC_FARE * 1000;
        }
    },
    SHIP(100) {
        @Override
        int fare() {
            return BASIC_FARE * 10000;
        }
    };

    //abstract 메서드가 있다면 상수들이 field에 접근하기 위해
    // private -> protected 변경해야 한다.
    protected final int BASIC_FARE;
    Transportation2(int fare) {
        this.BASIC_FARE = fare;
    }

    abstract int fare();
}
