package java_ex.optional_ex.all_ex;

import java.util.Optional;
import java.util.OptionalInt;

public class All_ex {
    public static void main(String[] args) {
        Optional<String> optStr = Optional.of("strd");
        Optional<Integer> optInt = optStr.map(String::length);
        System.out.println("optStr.get() = " + optStr.get());
        System.out.println("optInt.get() = " + optInt.get());

        Integer result1 = Optional.of("123")
                .filter(x -> x.length() > 0)
                .map(Integer::parseInt).get();

        Integer result2 = Optional.of("")
                .filter(x -> x.length() > 0)
                .map(Integer::parseInt).orElse(-1);

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);

        Optional.of("456").map(Integer::parseInt)
                .ifPresent(x -> System.out.printf("result3 = %d%n", x));

        OptionalInt optInt1 = OptionalInt.of(0);
        OptionalInt optInt2 = OptionalInt.empty();
        System.out.println(optInt1.isPresent());
        System.out.println("optInt2.isPresent() = " + optInt2.isPresent());

        System.out.println(optInt1.getAsInt());
        System.out.println(optInt2.getAsInt());

        System.out.println("optInt1 = " + optInt1);
        System.out.println("optInt2 = " + optInt2);

        System.out.println("optInt1.equals(optInt2) = " + optInt1.equals(optInt2));

        Optional<String> opt = Optional.ofNullable(null);
        Optional<String> opt2 = Optional.<String>empty();
        System.out.println("opt = " + opt);
        System.out.println("opt2 = " + opt2);

        System.out.println("opt.equals(opt2) = " + opt.equals(opt2));
        int result3 = optStrToInt(Optional.of("123"), 0);
        int result4 = optStrToInt(Optional.of(""), 0);

        System.out.println("result3 = " + result3);
        System.out.println("result4 = " + result4);
    }

    static int optStrToInt(Optional<String> optStr, int defaultValue) {
        try {
            return optStr.map(Integer::parseInt).get();
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
