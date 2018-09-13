package hw7;

public interface AuthService {
    String authByLoginAndPassword(String login, String password);
    void regNewUser(String name, String login, String password);
}
