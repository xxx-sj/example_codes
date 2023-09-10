package java_class.basic_class.polymorphism_ex;

public class Child extends Parent{

    public void childMethod() {
        System.out.println("child method called");
    }

    @Override
    public void parentMethod() {
        System.out.println("child called parentMethod");
    }
}
