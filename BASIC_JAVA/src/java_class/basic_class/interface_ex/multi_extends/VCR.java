package java_class.basic_class.interface_ex.multi_extends;

public class VCR {
    protected int counter;
    public void play() {
        System.out.println("PLAY VCR");
    }

    public void stop() {
        System.out.println("STOP VCR");
    }

    public void reset() {
        System.out.println("RESET VCR");
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
