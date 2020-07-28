package ru.vsi.weatherbot;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
@Component
public class Request {
    private final HttpClient client;
    private Keys keys;
    private URIBuilder builder;
    private HttpGet request;
    @Autowired
    public Request(Keys keys) throws java.net.URISyntaxException {
        System.out.println("trying to get key");
        client = HttpClients.createDefault();
        this.keys = keys;
        builder = new URIBuilder("http://api.openweathermap.org/data/2.5/weather");
    }

    public String getData(String city) throws IOException, URISyntaxException {
        builder.setParameter("q", city).setParameter("appid", keys.getOpenweatherKey());
        System.out.println(builder.build());
        request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            return IOUtils.toString(instream, "UTF-8");
        } else return null;
    }
}