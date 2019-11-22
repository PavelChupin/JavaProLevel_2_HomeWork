package homework.lesson4.chat.server;

import java.sql.SQLException;

public class MainServer {

    public static void main(String[] args) {
        try {
            new Server();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
