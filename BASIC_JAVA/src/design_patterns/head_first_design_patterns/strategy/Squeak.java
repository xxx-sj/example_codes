package design_patterns.head_first_design_patterns.strategy;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("삑");
    }
}
