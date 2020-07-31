package ru.vsi.weatherbot;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.URISyntaxException;;

@Service
public class WeatherHttpClient {
    private final HttpClient client;
    private final Keys keys;

    @Autowired
    public WeatherHttpClient(Keys keys) {
        System.out.println("trying to get key");
        client = HttpClients.createDefault();
        this.keys = keys;
    }

    public WeatherResponse weather(String city) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(keys.getADDRESS());
        builder.setParameter("q", city).setParameter("appid", keys.getOpenweatherKey());
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        ObjectMapper mapper = new ObjectMapper();
        if (entity != null) {
            InputStream instream = entity.getContent();
            String json = IOUtils.toString(instream, "UTF-8");
            WeatherResponse weatherResponse = mapper.readValue(json, WeatherResponse.class);
            return weatherResponse;
        } else throw new IOException();
    }
}