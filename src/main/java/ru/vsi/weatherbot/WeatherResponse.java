package ru.vsi.weatherbot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @JsonProperty("weather")
    private List<Weather> list;
    @JsonProperty("main")
    private Temperature temperature;

    public List<Weather> getList() {
        return list;
    }

    public void setList(List<Weather> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        list.forEach(x -> description.append(x.toString()));
        return "Temperature: " + temperature.toString() + " \u00B0C\nDescription: " + description.toString();
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Weather {
    private JsonNode main;

    public JsonNode getMain() {
        return main;
    }

    public void setMain(JsonNode main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return main.asText();
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Temperature {
    private JsonNode temp;

    public JsonNode getTemp() {
        return temp;
    }

    public void setTemp(JsonNode temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return Integer.toString((int) Math.round(temp.asDouble() - 273));
    }
}
