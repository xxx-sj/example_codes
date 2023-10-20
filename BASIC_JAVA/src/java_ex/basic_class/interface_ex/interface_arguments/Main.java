package java_ex.basic_class.interface_ex.interface_arguments;

public class Main {
    public static void main(String[] args) {
        DefaultDock dock = new DefaultDock();
        RocketDock dock2 = new RocketDock();

        printFly(dock);
        printFly(dock2);
    }

    public static void printFly(Flyable flyable) {

        flyable.fly();
    }
}
