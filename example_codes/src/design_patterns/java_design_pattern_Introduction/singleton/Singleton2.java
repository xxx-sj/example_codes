package design_patterns.java_design_pattern_Introduction.singleton;

public class Singleton2 {

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final Singleton2 instance = new Singleton2();
    }
}
