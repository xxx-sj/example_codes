package design_patterns.head_first_design_patterns.strategy.etc;

import design_patterns.head_first_design_patterns.strategy.Quack;
import design_patterns.head_first_design_patterns.strategy.QuackBehavior;

public class Hunter {
    private QuackBehavior quackBehavior;

    public Hunter() {
        quackBehavior = new Quack();
    }

    public void call() {
        this.quackBehavior.quack();
    }
}
