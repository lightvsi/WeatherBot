package ru.vsi.weatherbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class WeatherVSIBot extends TelegramLongPollingBot {
    private final String NOTFOUND = "City is not found. Enter correct name (such as London, Moscow, etc)";
    private final String ENTERNAME = "Enter a name of a city (such as London, Moscow, etc)";
    @Autowired
    private Keys keys;
    @Autowired
    private WeatherHttpClient weatherHttpClient;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            System.out.println(message);

            if (message.contains("/help") || message.contains("/start")) {
                sendMessage(ENTERNAME, chatId);
            } else if (message.contains("/get")) {
                try {
                    Weather weather = weatherHttpClient.weather(userRepository.findById(chatId).getCity());
                    sendMessage(weather.toString(), chatId);
                } catch (URISyntaxException | IOException e) {
                    sendMessage(NOTFOUND, chatId);
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Weather weather = weatherHttpClient.weather(message);
                    userRepository.save(new User(chatId, message));
                    sendMessage(weather.toString(), chatId);
                } catch (URISyntaxException | IOException e) {
                    sendMessage(NOTFOUND, chatId);
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void sendMessage(String text, long chatId) {
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
