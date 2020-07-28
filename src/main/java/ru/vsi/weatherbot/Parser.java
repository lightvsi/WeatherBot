package ru.vsi.weatherbot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

@Component
public class Parser {
    private final JsonParser parser;
    public Parser(){
        this.parser = new JsonParser();
    }
    public Weather parse(String input){
        JsonObject obj = parser.parse(input).getAsJsonObject();
        if(obj.get("cod").getAsInt() == 200) {
            double temp = obj.get("main").getAsJsonObject().get("temp").getAsDouble();
            String description = obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
            return new Weather(temp, description);
        }
        return null;
    }
}
