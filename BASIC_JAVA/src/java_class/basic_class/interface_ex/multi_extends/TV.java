package java_class.basic_class.interface_ex.multi_extends;

public class TV {

    protected boolean power;
    protected int channel;
    protected int volume;

    public void power() {power = !power;}
    public void channelUp() {channel++;}
    public void channelDown() {channel--;}

    public void volumeUp() {volume++;}
    public void volumeDown() {volume--;}

}
