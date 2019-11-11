package homework.lesson2.server.auth;


import javax.annotation.Nullable;
import java.sql.SQLException;

public interface IAuthService {
    void start() throws SQLException;

    void stop();


    @Nullable
    String getNickByLoginPass(String login, String pass) throws SQLException;


    void changeNickByLogin(String login, String newNick) throws SQLException;

}
