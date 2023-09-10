package java_class.basic_class.abstract_ex.job_ex;


import java_class.basic_class.abstract_ex.Character;

public abstract class Human extends Character {
    @Override
    public void attack() {
        System.out.println("사람은 주먹으로 때립니다.");
    }
}
