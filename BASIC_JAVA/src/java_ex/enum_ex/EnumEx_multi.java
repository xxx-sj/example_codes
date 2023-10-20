package java_ex.enum_ex;

public enum EnumEx_multi {
    ONE(1, "ONE"), TWO(2, "TWO"), THREE(3, "THREE");

    private final int value;
    private final String str;

    private EnumEx_multi(int value, String str) {
        this.value = value;
        this.str = str;
    }

    public int getValue() {
        return value;
    }

    public String getStr() {
        return str;
    }
}
