package java_class.basic_class.abstract_ex.job_ex;

import java_class.basic_class.abstract_ex.Character;

public abstract class Warrior extends Character {

    @Override
    public void attack() {
        System.out.println("전사는 도끼로 때립니다.");
    }
}
