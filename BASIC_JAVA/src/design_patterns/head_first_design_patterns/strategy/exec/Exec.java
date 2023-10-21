package design_patterns.head_first_design_patterns.strategy.exec;

import design_patterns.head_first_design_patterns.strategy.FlyRocketPowered;
import design_patterns.head_first_design_patterns.strategy.classes.Duck;
import design_patterns.head_first_design_patterns.strategy.classes.MallardDuck;
import design_patterns.head_first_design_patterns.strategy.classes.ModelDuck;

public class Exec {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
