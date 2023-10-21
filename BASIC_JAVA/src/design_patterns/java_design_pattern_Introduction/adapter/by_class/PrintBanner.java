package design_patterns.java_design_pattern_Introduction.adapter.by_class;

import design_patterns.java_design_pattern_Introduction.adapter.Banner;

public class PrintBanner extends Print{
    private Banner banner;

    public PrintBanner(Banner banner) {
        this.banner = banner;
    }
    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
