import java.io.*;
import java.util.Properties;

public class Keys {
    private static String TELEGRAM_KEY;
    private static String OPENWEATHER_KEY;
    public static void getKeys() {
        String file = "keys.properties";
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try{
            Properties keys = new Properties();
            if(classloader.getResourceAsStream(file) != null)
                keys.load(classloader.getResourceAsStream(file));
            TELEGRAM_KEY = keys.getProperty("TELEGRAM_KEY");
            OPENWEATHER_KEY = keys.getProperty("OPENWEATHER_KEY");
        }
        catch (IOException e){e.printStackTrace();}
    }

    public static String getTelegramKey() {
        return TELEGRAM_KEY;
    }

    public static String getOpenweatherKey() {
        return OPENWEATHER_KEY;
    }
}
