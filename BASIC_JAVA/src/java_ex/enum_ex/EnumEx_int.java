package java_ex.enum_ex;

public enum EnumEx_int {
    ONE(1), TWO(2), THREE(3);

    private final int value;

    //접근 제어자 private 생략
    EnumEx_int(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
