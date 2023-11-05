package design_patterns.head_first_design_patterns.observer;

public interface Observer {
    public void update(float temp, float humidity, float pressure);

    public void update();
}
