package design_patterns.java_design_pattern_Introduction.prototype.exec;

import design_patterns.java_design_pattern_Introduction.prototype.anonymous.MessageBox;
import design_patterns.java_design_pattern_Introduction.prototype.anonymous.UnderlinePen;
import design_patterns.java_design_pattern_Introduction.prototype.framework.Manager;
import design_patterns.java_design_pattern_Introduction.prototype.framework.Product;

public class Main {
    public static void main(String[] args) {
        //prepare

        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('~');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        manager.register("strong message", upen);
        manager.register("warning box", mbox);
        manager.register("slash box", sbox);

        //create /clone
        Product p1 = manager.create("strong message");
        p1.use("hello, world.");

        Product p2 = manager.create("warning box");
        p2.use("Hello, world");

        Product p3 = manager.create("slash box");
        p3.use("hello, world");
    }
}
