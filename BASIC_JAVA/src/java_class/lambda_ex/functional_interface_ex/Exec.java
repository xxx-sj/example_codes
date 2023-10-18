package java_class.lambda_ex.functional_interface_ex;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Exec {

    public static <T> void method(Supplier<T> supplier) {
        T t = supplier.get();
        System.out.println(t);
    }

    public static <T> void method2(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "test";
        Exec.<String>method(supplier);

        Consumer<String> stringConsumer = (t) -> {
            System.out.println("consume : " + t);
        };

        method2(stringConsumer, " it is");
    }

}
