import java.io.*;
import java.util.Properties;

public class Keys {
    private static final String FILE = "keys.properties";
    private final static String TELEGRAM_KEY;
    private final static String OPENWEATHER_KEY;
    static {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try{
            Properties keys = new Properties();
            if(classloader.getResourceAsStream(FILE) != null)
                keys.load(classloader.getResourceAsStream(FILE));
            TELEGRAM_KEY = keys.getProperty("TELEGRAM_KEY");
            OPENWEATHER_KEY = keys.getProperty("OPENWEATHER_KEY");
        }
        catch (IOException e){throw new RuntimeException(e);}
    }

    public static String getTelegramKey() {
        return TELEGRAM_KEY;
    }

    public static String getOpenweatherKey() {
        return OPENWEATHER_KEY;
    }
}
