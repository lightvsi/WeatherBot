package ru.vsi.weatherbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.telegram.telegrambots.ApiContextInitializer;


@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class Main {
    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init();
        SpringApplication.run(Main.class, args);
    }
}