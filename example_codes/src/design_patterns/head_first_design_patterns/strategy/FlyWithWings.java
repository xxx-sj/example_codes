package design_patterns.head_first_design_patterns.strategy;


public class FlyWithWings implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("날개를 사용해서 날아요~");
    }
}
