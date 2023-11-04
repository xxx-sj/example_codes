package design_patterns.head_first_design_patterns.strategy.classes;

import design_patterns.head_first_design_patterns.strategy.FlyNoWay;
import design_patterns.head_first_design_patterns.strategy.Quack;

public class ModelDuck extends Duck {

    public ModelDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Quack();
    }
    @Override
    public void display() {
        System.out.println("모형 오리입니다.");
    }
}
