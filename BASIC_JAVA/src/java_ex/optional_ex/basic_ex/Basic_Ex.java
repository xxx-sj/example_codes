package java_ex.optional_ex.basic_ex;

import java.util.Optional;

public class Basic_Ex {
    public static void main(String[] args) {
        Basic_Ex.createOptional();
        Basic_Ex.getOptional();
        Basic_Ex.methods();
        Basic_Ex.methods_2();
    }

    public static void createOptional() {
        Optional<String> opt1 = Optional.of("abc");
        Optional<String> opt2 = Optional.of(new String("abc"));

        //Optional<String> opt3 = Optional.of(null);
        Optional<String> opt3 = Optional.ofNullable(null);

//        Optional<String> opt4 = null;
        Optional<String> opt4 = Optional.<String>empty();
    }

    public static void getOptional() {
        Optional<String> optVal = Optional.of("abc");
        String s1 = optVal.get();
        String s = optVal.orElse("");

//        String s2 = optVal.orElseGet(() -> new String());
        String s2 = optVal.orElseGet(String::new);
        String s3 = optVal.orElseThrow(() -> new NullPointerException());
    }

    public static void methods() {
        Integer i = Optional.of("123")
                .filter(x -> x.length() > 0)
                .map(Integer::parseInt)
                .orElse(-1);
    }

    public static void methods_2() {
        boolean present = Optional.ofNullable(null).isPresent();

        Optional.ofNullable("null").ifPresent((i) -> System.out.println(i));
    }

}
