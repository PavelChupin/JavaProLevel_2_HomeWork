package homework.lesson3.javafx.controller.message;

import homework.lesson3.javafx.historymessfromchat.FileHelper;
import javafx.scene.control.TextArea;
import homework.lesson3.javafx.controller.Network;
import homework.lesson3.javafx.controller.PrimaryController;
import homework.lesson3.messageconvert.Message;
import homework.lesson3.messageconvert.message.PrivateMessage;
import homework.lesson3.messageconvert.message.PublicMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServerMessageService implements IMessageService {

    private static final String HOST_ADDRESS_PROP = "server.address";
    private static final String HOST_PORT_PROP = "server.port";
    private static final String COUNT_LAST_MESSAGE_SHOW = "user.count.last.message.show";

    private String hostAddress;
    private int hostPort;
    private int countLastMessage;

    private final TextArea chatTextArea;
    private FileHelper fileHelper;
    private PrimaryController primaryController;
    private boolean needStopServerOnClosed;
    private Network network;

    public ServerMessageService(PrimaryController primaryController, boolean needStopServerOnClosed) {
        this.chatTextArea = primaryController.chatTextArea;
        this.primaryController = primaryController;
        this.needStopServerOnClosed = needStopServerOnClosed;
        initialize();
    }

    private void initialize() {
        readProperties();
        startConnectionToServer();
    }

    private void startConnectionToServer() {
        try {
            this.network = new Network(hostAddress, hostPort, this);
        } catch (IOException e) {
            throw new ServerConnectionException("Failed to connect to server", e);
        }
    }

    private void readProperties() {
        Properties serverProperties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream("/application.properties")) {
            serverProperties.load(inputStream);
            hostAddress = serverProperties.getProperty(HOST_ADDRESS_PROP);
            hostPort = Integer.parseInt(serverProperties.getProperty(HOST_PORT_PROP));
            countLastMessage = Integer.parseInt(serverProperties.getProperty(COUNT_LAST_MESSAGE_SHOW));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read application.properties file", e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid port value", e);
        }
    }

    @Override
    public void sendMessage(Message message) {
        network.send(message.toJson());
        //Записали сообщение в файл
        fileHelper.writeToFileHistory(primaryController.messageText.getText());
    }

    @Override
    public void processRetrievedMessage(Message message) {
        switch (message.command) {
            case AUTH_OK: {
                processAuthOk(message);
                break;
            }
            case AUTH_ERROR: {
                primaryController.showAuthError(message.authErrorMessage.errorMsg);
                break;
            }

            case PRIVATE_MESSAGE: {
                processPrivateMessage(message);
                break;
            }
            case PUBLIC_MESSAGE: {
                processPublicMessage(message);
                break;
            }

            case CHANGE_NICK_OK: {
                processChangeNickOk(message);
                break;
            }
            case CHANGE_NICK_ERR: {
                processChangeNickErr(message);
                break;
            }

            case CLIENT_LIST:
                List<String> onlineUserNicknames = message.clientListMessage.online;
                primaryController.refreshUsersList(onlineUserNicknames);
                break;
            default:
                throw new IllegalArgumentException("Unknown command type: " + message.command);
        }
    }


    private void processPublicMessage(Message message) {
        PublicMessage publicMessage = message.publicMessage;
        String from = publicMessage.from;
        String msg = publicMessage.message;
        String msgToChar;
        if (from != null) {
            msgToChar = String.format("%s: %s%n", from, msg);// chatTextArea.appendText(String.format("%s: %s%n", from, msg));
        } else {
            msgToChar = String.format("%s%n", msg); //chatTextArea.appendText(String.format("%s%n", msg));
        }
        printMessageToChat(msgToChar);
    }

    private void processPrivateMessage(Message message) {
        PrivateMessage privateMessage = message.privateMessage;
        String from = privateMessage.from;
        String msg = privateMessage.message;
        String msgToView = String.format("%s (private): %s%n", from, msg);
        //chatTextArea.appendText(msgToView);
        printMessageToChat(msgToView);
    }

    private void processChangeNickOk(Message message) {
        String s = message.changeNickOkMessage.message;
        primaryController.setNickName(message.changeNickOkMessage.newNick);
        if (s != null) {
            printMessageToChat(String.format("%s%n", s));//chatTextArea.appendText(String.format("%s%n", s));
        }
    }

    private void processChangeNickErr(Message message) {
        String s = message.changeNickErrMessage.message;
        if (s != null) {
            printMessageToChat(String.format("%s%n", s));chatTextArea.appendText(String.format("%s%n", s));
        }
    }

    private void printMessageToChat(String message){
        chatTextArea.appendText(message);
        fileHelper.writeToFileHistory(message);
    }

    private void processAuthOk(Message message) {
        primaryController.setNickName(message.authOkMessage.nickName);
        primaryController.showChatPanel();
        openFileHistory();
    }

    @Override
    public void close() throws IOException {
        if (needStopServerOnClosed) {
            sendMessage(Message.serverEndMessage());
        }
        network.close();
    }

    public void openFileHistory() {
        //Инициализируем класс для сохранения файлов после авторизации
        FileHelper fileHelper = new FileHelper(primaryController.loginField.getText());
        List<String> messFromFile = fileHelper.readFromFileHistory();
        List<String> showMessToNick = new ArrayList<>();
        if (messFromFile.size() > countLastMessage) {
           /* for (int i = messFromFile.size() - 100; i > 0; i--) {
                messFromFile.remove(0);
            }
        }*/
            for (int i = messFromFile.size() - (countLastMessage + 1); i < messFromFile.size(); i++) {
                showMessToNick.add(messFromFile.get(i));
            }
        } else {
            showMessToNick.addAll(messFromFile);
        }

        showMessToNick.forEach((str) -> {
            chatTextArea.appendText(str + "\n");
        });
        /*messFromFile.forEach((str) -> {
            chatTextArea.appendText(str + "\n");
        });*/

    }

}
