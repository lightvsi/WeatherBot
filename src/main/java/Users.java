import java.util.HashSet;

public class Users {
    private static HashSet<User> users = new HashSet<>();
    public static boolean addUser(long chatId){
        return users.add(new User(chatId));
    }
    public static void printUsers(){
        if (!users.isEmpty()) {
            users.forEach(x -> System.out.println(x.toString()));
        }
        else {
            System.out.println("there are no users");
        }
    }
}
