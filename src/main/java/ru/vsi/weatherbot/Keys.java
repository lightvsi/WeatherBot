package ru.vsi.weatherbot;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Properties;

@Component
public class  Keys {
    private final String FILE;
    private final String TELEGRAM_KEY;
    private final String OPENWEATHER_KEY;
    public Keys() {
        FILE = "keys.properties";
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try{
            Properties keys = new Properties();
            if(classloader.getResourceAsStream(FILE) != null)
                keys.load(classloader.getResourceAsStream(FILE));
            TELEGRAM_KEY = keys.getProperty("TELEGRAM_KEY");
            OPENWEATHER_KEY = keys.getProperty("OPENWEATHER_KEY");
            System.out.println("keys created");
        }
        catch (IOException e){throw new RuntimeException(e);}
    }

    public String getTelegramKey() {
        return TELEGRAM_KEY;
    }

    public String getOpenweatherKey() {
        return OPENWEATHER_KEY;
    }
}
