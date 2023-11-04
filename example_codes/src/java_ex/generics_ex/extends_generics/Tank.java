package java_ex.generics_ex.extends_generics;

public class Tank extends Mechanic implements Attackable {
    @Override
    public void attack() {
        System.out.println("대포를 쏜다.");
    }
}
