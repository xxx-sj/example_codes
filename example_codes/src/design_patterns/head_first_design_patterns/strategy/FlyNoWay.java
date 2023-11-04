package design_patterns.head_first_design_patterns.strategy;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("날지 못해요..");
    }
}
