package java_ex.basic_class.abstract_ex;


public abstract class Human extends Character {
    @Override
    public void attack() {
        System.out.println("사람은 주먹으로 때립니다.");
    }
}
