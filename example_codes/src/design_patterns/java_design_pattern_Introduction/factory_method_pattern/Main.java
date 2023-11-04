package design_patterns.java_design_pattern_Introduction.factory_method_pattern;

import design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework.Factory;
import design_patterns.java_design_pattern_Introduction.factory_method_pattern.framework.Product;
import design_patterns.java_design_pattern_Introduction.factory_method_pattern.idcard.IDCardFactory;
import design_patterns.java_design_pattern_Introduction.factory_method_pattern.television.TelevisionFactory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("홍길동");
        Product card2 = factory.create("이순신");
        Product card3 = factory.create("강감찬");
        card1.use();
        card2.use();
        card3.use();

        Factory factory1 = new TelevisionFactory();

        Product tv1 = factory1.create("LG");
        Product tv2 = factory1.create("SAMSUNG");
        Product tv3 = factory1.create("APPLE");
        tv1.use();
        tv2.use();
        tv3.use();
    }
}
