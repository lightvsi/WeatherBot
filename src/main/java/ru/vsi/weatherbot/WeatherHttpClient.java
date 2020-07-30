package ru.vsi.weatherbot;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class WeatherHttpClient {
    private final HttpClient client;
    private final Keys keys;
    private final JsonParser parser;

    @Autowired
    public WeatherHttpClient(Keys keys) {
        System.out.println("trying to get key");
        client = HttpClients.createDefault();
        this.parser = new JsonParser();
        this.keys = keys;
    }

    public Weather weather(String city) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(keys.getADDRESS());
        builder.setParameter("q", city).setParameter("appid", keys.getOpenweatherKey());
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            return parse(IOUtils.toString(instream, "UTF-8"));
        } else throw new IOException();
    }

    private Weather parse(String input) throws IOException {
        JsonObject obj = parser.parse(input).getAsJsonObject();
        if (obj.get("cod").getAsInt() == 200) {
            double temp = obj.get("main").getAsJsonObject().get("temp").getAsDouble();
            String description = obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
            return new Weather(temp, description);
        } else throw new IOException();
    }
}