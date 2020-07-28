package ru.vsi.weatherbot;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<Long, User> users = new HashMap<>();
    public static void addUser(long chatId){
        users.put(chatId, new User(chatId));
    }
    public static void printUsers(){
        if (!users.isEmpty()) {
            users.values().forEach(System.out::println);
        }
        else {
            System.out.println("there are no users");
        }

    }
}
