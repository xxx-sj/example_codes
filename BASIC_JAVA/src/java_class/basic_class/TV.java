package java_class.basic_class;

public class TV {
    String color;
    boolean power;
    int channel;

    public TV() {
        color = "BLACK";
        power = false;
        channel = 0;
    }

    void powerOn() {
        this.power = true;
    }

    void powerOff() {
        this.power = false;
    }

    void channelUp() {
        channel++;
    }

    void channelDown() {
        channel--;
    }
}
