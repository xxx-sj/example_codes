package java_ex.basic_class.interface_ex.interface_arguments;

public class DefaultDock implements Flyable{
    @Override
    public void fly() {
        System.out.println("일반 오리는 날개로 날아다닙니다.");
    }
}
