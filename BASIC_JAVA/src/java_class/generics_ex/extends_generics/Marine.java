package java_class.generics_ex.extends_generics;

public class Marine extends Unit implements Attackable{
    @Override
    public void attack() {
        System.out.println("총을 쏜다.");
    }
}
