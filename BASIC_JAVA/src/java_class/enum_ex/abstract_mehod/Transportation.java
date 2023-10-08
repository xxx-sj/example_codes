package java_class.enum_ex.abstract_mehod;

public enum Transportation {
    BUS(100), TRAIN(150), SHIP(100);

    private final int BASIC_FARE;
    Transportation(int fare) {
        this.BASIC_FARE = fare;
    }

    int fare() {
        return BASIC_FARE;
    }
}
