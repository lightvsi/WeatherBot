import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {
        Keys.getKeys();
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new WeatherVSIBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}