package design_patterns.head_first_design_patterns.strategy.classes;

import design_patterns.head_first_design_patterns.strategy.FlyWithWings;
import design_patterns.head_first_design_patterns.strategy.Quack;
import design_patterns.head_first_design_patterns.strategy.classes.Duck;

public class MallardDuck extends Duck {

    public MallardDuck() {
        this.quackBehavior = new Quack();
        this.flyBehavior = new FlyWithWings();
    }
    @Override
    public void display() {
        System.out.println("저는 물 오리입니다~");
    }
}
