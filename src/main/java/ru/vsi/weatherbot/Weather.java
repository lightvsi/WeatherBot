package ru.vsi.weatherbot;

public class Weather {
    private int temp;
    private String description;
    public Weather(double temp, String description){
        this.temp = (int) Math.round(temp-273);
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Temperature: " + temp + " \u00B0C\nDescription: " + description;
    }
}
