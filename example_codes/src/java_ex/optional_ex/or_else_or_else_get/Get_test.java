package java_ex.optional_ex.or_else_or_else_get;

import java.util.Optional;

public class Get_test {

    private static final String BASE_NAME = "name";
    public static void main(String[] args) {
        String username = "username";

        String s1 = Optional.of(username).orElse(Get_test.BASE_NAME);
        System.out.println("s1 = " + s1);
        String s2 = Optional.of(username).orElseGet(() -> Get_test.BASE_NAME);
        System.out.println("s2 = " + s2);

        System.out.println();

        String s3 = Optional.of(username).orElse(getBaseName());
        System.out.println("s3 = " + s3);
        String s4 = Optional.of(username).orElseGet(() -> getBaseName());
        System.out.println("s4 = " + s4);

        System.out.println();

        Object s5 = Optional.ofNullable(null).orElse(getBaseName());
        System.out.println("s5 = " + s5);
        Object s6 = Optional.ofNullable(null).orElseGet(() -> getBaseName());
        System.out.println("s6 = " + s6);

        System.out.println();

        String baseName = getBaseName();
        Object s7 = Optional.ofNullable(null).orElse(baseName);
        System.out.println("s7 = " + s7);
        Object s8 = Optional.ofNullable(null).orElseGet(() -> getBaseName());
        System.out.println("s8 = " + s8);


    }

    public static String getBaseName() {
        System.out.println("getBaseName called");
        return BASE_NAME;
    }
}
