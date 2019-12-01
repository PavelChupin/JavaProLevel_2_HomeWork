package homework.lesson6.chat.server.auth;

import homework.lesson6.chat.server.data.User;

import javax.annotation.Nullable;
import java.sql.SQLException;
import java.util.List;

public class BaseAuthService implements IAuthService {
    private List<User> users;

    public BaseAuthService() {
        users = List.of(new User("login1", "pass1", "nick1"),
                new User("login2", "pass2", "nick2"),
                new User("login3", "pass3", "nick3"));
    }

    @Override
    public void start() {
        System.out.println("Auth service is running");
    }

    @Override
    public void stop() {
        System.out.println("Auth service has stopped");
    }

    @Nullable
    @Override
    public String getNickByLoginPass(String login, String pass) {
        //Проверяем обязательные параметры
        if (login == null || pass == null || login.isEmpty() || pass.isEmpty()) {
            return null;
        }

        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(pass)) {
                return u.getNick();
            }
        }
        return null;
    }

    @Override
    public void changeNick(String oldNick, String newNick) throws SQLException {

    }

}
