package design_patterns.head_first_design_patterns.strategy;

public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("로켓을 이용해 날아갑니다.");
    }
}
