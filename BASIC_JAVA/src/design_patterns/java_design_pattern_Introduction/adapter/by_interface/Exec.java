package design_patterns.java_design_pattern_Introduction.adapter.by_interface;

public class Exec {
    public static void main(String[] args) {
        Print p = new PrintBanner("Hello");
        p.printWeak();
        p.printStrong();
    }
}
