package homework.lesson2.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnToDataBase {
    private static final String DATABASE_URL = "database.url";
    private static final String DATABASE_USER = "database.username";
    private static final String DATABASE_PASSSWORD = "database.password";

    private Connection connection = null;

    public ConnToDataBase() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(getProperty(DATABASE_URL), getProperty(DATABASE_USER), getProperty(DATABASE_PASSSWORD));
            //connection = DriverManager.getConnection(getProperty(DATABASE_URL));
            if (!connection.isClosed()) {
                System.out.println("Connect to DB");
            }
            //Отключаем у соединения автокоммит
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
        }
        System.out.println("Database ConnToDataBase Established...");

    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private String getProperty(String property) {
        Properties serverProperties = new Properties();
        String value;
        try (InputStream inputStream = getClass().getResourceAsStream("/application.properties")) {
            serverProperties.load(inputStream);
            value = serverProperties.getProperty(property);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read application.properties file", e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid port value", e);
        }
        return value;
    }
}
