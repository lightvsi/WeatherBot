import org.apache.commons.io.IOUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Keys {
    private static String TELEGRAM_KEY;
    private static String OPENWEATHER_KEY;
    public static void getKeys() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try(InputStream is = classloader.getResourceAsStream("keys.txt")) {
            String contents = IOUtils.toString(is, StandardCharsets.UTF_8);
            String[] keys = contents.split("\\r?\\n");
            TELEGRAM_KEY = keys[0];
            OPENWEATHER_KEY = keys[1];
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
