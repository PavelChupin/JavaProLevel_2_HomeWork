package homework.lesson4.chat.server;

import homework.lesson4.chat.messageconvert.Message;
import homework.lesson4.chat.server.auth.DataBaseAuthService;
import homework.lesson4.chat.server.auth.IAuthService;
import homework.lesson4.chat.server.client.ClientHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final String HOST_PORT_PROP = "server.port";
    private static final String WAIT_TIMEOUT_AUTH = "server.wait.timeout.auth";
    private final IAuthService authService = new DataBaseAuthService();//new BaseAuthService();
    private Properties serverProperties = new Properties();

    private List<ClientHandler> clients = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public Server() throws SQLException, ClassNotFoundException {
        System.out.println("Server is running");

        try (ServerSocket serverSocket = new ServerSocket(getProperty(HOST_PORT_PROP))) {

            authService.start();

            //Бесконечный цикл ожидания подключения пользователей
            while (true) {
                System.out.println("Awaiting client connection...");
                //Ждем подключений
                Socket socket = serverSocket.accept();
                System.out.println("Client has connected");
                //Подключения получено, запускаем сервис авторизации
                new ClientHandler(socket, this, getProperty(WAIT_TIMEOUT_AUTH));
            }

        } catch (IOException e) {
            System.err.println("Ошибка работы сервера. Причина: " + e.getMessage());
            e.printStackTrace();
        } finally {
            authService.stop();
            executorService.shutdownNow();
        }
    }

    //Метод подписки клиентов на работу с сервером
    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientList();
    }

    //Метод обновления ника пользователя
    public synchronized void changesubscribe(ClientHandler clientHandler) {
        broadcastClientList();
    }

    //Метод отписки пользователя от сервера
    public synchronized void unSubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClientList();
    }

    //Добавления и обновления списка пользователей в чате
    private void broadcastClientList() {
        List<String> nickNames = new ArrayList<>();
        for (ClientHandler client : clients) {
            nickNames.add(client.getClientName());
        }
        Message message = Message.createClientList(nickNames);
        broadcastMessage(message.toJson(), null);
    }



    public IAuthService getAuthService() {
        return authService;
    }

    //Метод проверки на занятость/ранее авторизированного пользователя
    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    //Метод отправки сообщения всем пользователям чата
    public synchronized void broadcastMessage(String s, ClientHandler unfilteredClients) {
        List<ClientHandler> unfiltered = Arrays.asList(unfilteredClients);
        for (ClientHandler client : clients) {
            if (!unfiltered.contains(client)) {
                client.sendMessage(s);
            }
        }
    }

    public void broadcastMessage(Message message, ClientHandler unfilteredClients) {
        broadcastMessage(message.toJson(), unfilteredClients);
    }

    public synchronized void messageToPrivateLogin(Message message) {
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(message.privateMessage.to)) {
                client.sendMessage(message.toJson());
                break;
            }
        }
    }

    public synchronized void messageChangeNickOk(Message message) {
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(message.changeNickOkMessage.newNick)) {
                client.sendMessage(message.toJson());
                break;
            }
        }
    }

    public synchronized void messageChangeNickErr(Message message, ClientHandler clientHandler) {
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(clientHandler.getClientName())) {
                client.sendMessage(message.toJson());
                break;
            }
        }
    }

    private int getProperty(String property) {
        int value;
        try (InputStream inputStream = getClass().getResourceAsStream("/application.properties")) {
            serverProperties.load(inputStream);
            value = Integer.parseInt(serverProperties.getProperty(property));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read application.properties file", e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid port value", e);
        }
        return value;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
