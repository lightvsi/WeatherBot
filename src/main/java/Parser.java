import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser {
    public static Weather parse(String input){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(input).getAsJsonObject();
        if(obj.get("cod").getAsInt() == 200) {
            double temp = obj.get("main").getAsJsonObject().get("temp").getAsDouble();
            String description = obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
            return new Weather(temp, description);
        }
        return null;
    }
}
