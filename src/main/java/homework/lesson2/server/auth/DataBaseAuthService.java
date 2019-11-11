package homework.lesson2.server.auth;

import homework.lesson2.database.ConnToDataBase;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseAuthService implements IAuthService {
    ConnToDataBase connToDataBase;

    public DataBaseAuthService() throws SQLException, ClassNotFoundException {
        //Установим соединение
        checkAndCreateConn();

        //Почистим таблицу от паролей заведенных ранее, так чисто для тестов
        try (Statement statement = connToDataBase.getConnection().createStatement()) {
            int rowcount = statement.executeUpdate("delete from User");
            connToDataBase.getConnection().commit();
        } catch (SQLException e) {
            connToDataBase.getConnection().rollback();
        }
    }

    private void checkAndCreateConn() throws SQLException, ClassNotFoundException {
        if (connToDataBase == null) {
            connToDataBase = new ConnToDataBase();
        }
    }

    @Override
    public void start() throws SQLException, ClassNotFoundException {
        checkAndCreateConn();

        //Заполним таблицу логинами и паролями, для практики делать это будем каждый раз, при старте сервера
        //Создаем пакетный запрос
        try (PreparedStatement preparedStatement = connToDataBase.getConnection()
                .prepareStatement("insert into User(Login,Password,Nick) values (?,?,?);")) {
            for (int i = 1; i < 4; i++) {
                preparedStatement.setString(1, "login" + i);
                preparedStatement.setString(2, "pass" + i);
                preparedStatement.setString(3, "nick" + i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connToDataBase.getConnection().commit();
        } catch (SQLException e) {
            connToDataBase.getConnection().rollback();
        }
        System.out.println("Auth service is running");
    }

    @Override
    public void stop() {
        try {
            connToDataBase.closeConnection();
        } catch (SQLException e) {
            System.out.println("Auth service has not stopped");
        }
        System.out.println("Auth service has stopped");
    }

    @Nullable
    @Override
    public String getNickByLoginPass(String login, String pass) throws SQLException {
        String nick = null;
        //Проверяем обязательные параметры
        if (login == null || pass == null || login.isEmpty() || pass.isEmpty()) {
            return null;
        }

        ResultSet rs = null;
        //Лезим в базу, и проверяем наличие логина и пароля.
        try (Statement statement = connToDataBase.getConnection().createStatement()) {
            rs = statement.executeQuery(String.format("select Nick from User where Login = '%s' and Password = '%s'", login, pass));
            while (rs.next()) {
                nick = rs.getString("Nick");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return nick;
    }

    @Override
    public void changeNick(String oldNick, String newNick) throws SQLException, RuntimeException {
        //Проверяем обязательные параметры
        if (oldNick == null || oldNick.isEmpty() || newNick == null || newNick.isEmpty()) {
            throw new RuntimeException("Не переданны обязательные параметры");
        }

        //проверим а не занят ли новый ник
        if (!checkNickByBusy(newNick)) {
            try (Statement statement = connToDataBase.getConnection().createStatement()) {
                int rowcount = statement.executeUpdate(String.format("update User set Nick = '%s' where Nick = '%s'", newNick, oldNick));
                connToDataBase.getConnection().commit();
            } catch (SQLException e) {
                connToDataBase.getConnection().rollback();
            }
        } else {
            throw new RuntimeException("NickName " + newNick + " is busy");
        }
    }

    private boolean checkNickByBusy(String newNick) throws SQLException {
        ResultSet rs = null;
        int count = 0;
        try (Statement statement = connToDataBase.getConnection().createStatement()) {
            rs = statement.executeQuery(String.format("select 1 from User where Nick = '%s'", newNick));
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return count > 0;
    }

}
