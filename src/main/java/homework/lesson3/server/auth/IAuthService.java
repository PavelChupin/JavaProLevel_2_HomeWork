package homework.lesson3.server.auth;


import javax.annotation.Nullable;
import java.sql.SQLException;

public interface IAuthService {
    void start() throws SQLException, ClassNotFoundException;

    void stop();


    @Nullable
    String getNickByLoginPass(String login, String pass) throws SQLException;


    void changeNick(String oldNick, String newNick) throws SQLException, RuntimeException;

}
