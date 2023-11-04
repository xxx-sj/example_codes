package design_patterns.java_design_pattern_Introduction.adapter;

import design_patterns.java_design_pattern_Introduction.adapter.by_interface.Print;
import design_patterns.java_design_pattern_Introduction.adapter.by_interface.PrintBanner;

public class Exec {
    public static void main(String[] args) {
        Print p = new PrintBanner("Hello");
        p.printWeak();
        p.printStrong();
    }
}
