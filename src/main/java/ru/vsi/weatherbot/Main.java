package ru.vsi.weatherbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class Main {
    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init();
        ApplicationContext context = SpringApplication.run(Main.class, args);
        TelegramLongPollingBot weatherbot = context.getBean("weatherVSIBot", TelegramLongPollingBot.class);
        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(weatherbot);
    }
}