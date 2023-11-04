package design_patterns.java_design_pattern_Introduction.adapter.by_interface;

import design_patterns.java_design_pattern_Introduction.adapter.Banner;

public class PrintBanner extends Banner implements Print {
    public PrintBanner(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
