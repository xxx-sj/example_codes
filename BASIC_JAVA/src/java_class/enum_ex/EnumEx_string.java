package java_class.enum_ex;

public enum EnumEx_string {
    ONE("ONE"), TWO("TWO"), THREE("THREE");

    private final String value;

    EnumEx_string(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
