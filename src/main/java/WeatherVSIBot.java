import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.io.IOException;

public class WeatherVSIBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            System.out.println(message);
            if (message.contains("/help") || message.contains("/start")) {
                sendMessage("Enter a name of a city (such as London, Moscow, etc)",chatId);
            }
            else if(message.contains("/get"))
            {
                try {
                    Weather weather = Parser.parse(Request.getData(DatabaseOperations.city(chatId)));
                    sendMessage(weather != null ? weather.toString() : "City is not found. Enter correct name (such as London, Moscow, etc)", chatId);
                }
                catch (IOException e) {throw new RuntimeException(e);}
            }
            else{
                try {
                    //sendMessage("requestProcessed:", chatId);

                    Weather weather = Parser.parse(Request.getData(message));
                    if(weather != null) {
                        DatabaseOperations.addorUpdate(chatId,message);
                        sendMessage(weather.toString(), chatId);
                    }
                    else
                    {
                        sendMessage("City is not found. Enter correct name (such as London, Moscow, etc)", chatId);
                    }
                }
                catch (IOException e) {throw new RuntimeException(e);}
            }
        }
    }
    private void sendMessage(String text,long chatId){
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(text);
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "WeatherVSIBot";
    }

    @Override
    public String getBotToken() {
        return Keys.getTelegramKey();
    }
}
