package java_class.enum_ex.exam;

import java_class.enum_ex.EnumEx_multi;

public class Main {
    public static void main(String[] args) {
        EnumEx_multi[] values = EnumEx_multi.values();
        for (EnumEx_multi value : values) {
            System.out.println("value = " + value);
            System.out.println("value.getValue() = " + value.getValue());
        }
    }
}
