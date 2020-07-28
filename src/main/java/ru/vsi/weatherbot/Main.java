package ru.vsi.weatherbot;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Main {

    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TelegramLongPollingBot weatherbot = context.getBean("weatherVSIBot", TelegramLongPollingBot.class);
        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(weatherbot);
        context.close();
    }
}