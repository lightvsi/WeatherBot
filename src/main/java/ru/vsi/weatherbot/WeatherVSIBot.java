package ru.vsi.weatherbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class WeatherVSIBot extends TelegramLongPollingBot {
    private Keys keys;
    private Parser parser;
    private Request request;
    private DatabaseOperations databaseOperations;

    @Autowired
    public WeatherVSIBot(Keys keys, Parser parser, Request request, DatabaseOperations databaseOperations){
        this.keys = keys;
        this.parser = parser;
        this.request = request;
        this.databaseOperations = databaseOperations;
        if(this.keys != null && this.parser != null && this.request != null && this.databaseOperations != null)
            System.out.println("dependency injection successful");
    }

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
                    Weather weather = parser.parse(request.getData(databaseOperations.city(chatId)));
                    sendMessage(weather != null ? weather.toString() : "City is not found. Enter correct name (such as London, Moscow, etc)", chatId);
                }
                catch (IOException e) {throw new RuntimeException(e);}
                catch(URISyntaxException e) {throw new RuntimeException(e);}
            }
            else {
                try {
                    Weather weather = parser.parse(request.getData(message));
                    if (weather != null) {
                        databaseOperations.addorUpdate(chatId, message);
                        sendMessage(weather.toString(), chatId);
                    } else {
                        sendMessage("City is not found. Enter correct name (such as London, Moscow, etc)", chatId);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                catch(URISyntaxException e){
                    throw new RuntimeException(e);
                }
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
                return this.keys.getTelegramKey();
    }
}
