package design_patterns.head_first_design_patterns.observer.exec;

import design_patterns.head_first_design_patterns.observer.CurrentConditionsDisplay;
import design_patterns.head_first_design_patterns.observer.WeatherData;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(70, 45, 10.4f);
        weatherData.setMeasurements(80, 35, 20.4f);
    }
}
