package java_class.generics_ex.gererics_default_ex;

public class Main {
    public static void main(String[] args) {
        Box<Integer> box1 = new Box<>();
        box1.set(10);
        System.out.println("box1.get = " + box1.get());
        System.out.println("box1.get().getClass().getSimpleName() = " + box1.get().getClass().getSimpleName());

        Box<String> box2 = new Box<>();
        box2.set("String");
        System.out.println("box2.get() = " + box2.get());
        System.out.println("box2.get().getClass().getSimpleName() = " + box2.get().getClass().getSimpleName());
        
    }
}
