package hw7;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    private List<User> users = new ArrayList<>();

    public BaseAuthService() {
        users.add(new User("login1", "pass1", "nick1"));
        users.add(new User("login2", "pass2", "nick2"));
        users.add(new User("login3", "pass3", "nick3"));
    }

    @Override
    public String authByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (login.equals(user.getLogin())
                    && password.equals(user.getPassword()))
                return user.getNickname();
        }
        return null;
    }

    @Override
    public void regNewUser(String name, String login, String password){
        users.add(new User(name, login, password));
    }
}