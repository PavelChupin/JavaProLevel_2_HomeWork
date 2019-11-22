package homework.lesson3.javafx.controller;

import homework.lesson3.javafx.controller.message.IMessageService;
import homework.lesson3.javafx.controller.message.ServerMessageService;
import homework.lesson3.messageconvert.Message;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    public static final String ALL_ITEM = "All";

    public @FXML
    TextArea chatTextArea;
    public @FXML
    TextField messageText;
    public @FXML
    Button sendMessageButton;

    public @FXML
    Button connectionButton;

    @FXML
    Button changeNickButton;

    @FXML
    Button changeNickButtonOk;

    @FXML
    TextField textChangeNick;

    public @FXML
    TextField loginField;
    public @FXML
    PasswordField passField;

    public @FXML
    HBox authPanel;

    public @FXML
    HBox changeNickPanel;

    public @FXML
    VBox chatPanel;

    public @FXML
    ListView<String> clientList;

    private String nickName;


    private IMessageService messageService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.messageService = new MockMessageService(chatTextArea);
        try {
            this.messageService = new ServerMessageService(this, true);
        } catch (Exception e) {
            showError(e);
        }
    }

    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("oops! Something went wrong!");
        alert.setHeaderText(e.getMessage());

        VBox dialogPaneContent = new VBox();

        Label label = new Label("Stack Trace:");

        String stackTrace = ExceptionUtils.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);

        dialogPaneContent.getChildren().addAll(label, textArea);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.setResizable(true);
        alert.showAndWait();

        e.printStackTrace();
    }

    @FXML
    public void sendText(ActionEvent actionEvent) {
        sendMessage();
    }

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
        sendMessage();
    }



    private void sendMessage() {
        String message = messageText.getText();
        if (StringUtils.isNotBlank(message)) {
            chatTextArea.appendText(String.format("Я: %s%n", message));
            Message msg = buildMessage(message);
            messageService.sendMessage(msg);
            messageText.clear();
        }
    }

    private Message buildMessage(String message) {
        String selectedNickname = clientList.getSelectionModel().getSelectedItem();
        if (selectedNickname != null && !selectedNickname.equals(ALL_ITEM)) {
            return Message.createPrivate(nickName, selectedNickname, message);
        }

        return Message.createPublic(nickName, message);
    }

    public void shutdown() {
        try {
            messageService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passField.getText();
        messageService.sendMessage(Message.createAuth(login, password));
    }


    public void showChatPanel() {
        authPanel.setVisible(false);
        chatPanel.setVisible(true);
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        refreshWindowTitle(nickName);
    }

    private void refreshWindowTitle(String nickName) {
        Stage stage = (Stage) chatPanel.getScene().getWindow();
        stage.setTitle(nickName);
    }

    public void showAuthError(String errorMsg) {
        if (authPanel.isVisible()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Authentication is failed");
            alert.setContentText(errorMsg);
            alert.showAndWait();
        }
    }

    public void refreshUsersList(List<String> onlineUserNicknames) {
        onlineUserNicknames.add(ALL_ITEM);
        clientList.setItems(FXCollections.observableArrayList(onlineUserNicknames));
    }

    @FXML
    public void changeNickOk(ActionEvent event) {
        Message msg = Message.createChangeNick(textChangeNick.getText());
        messageService.sendMessage(msg);
        changeNickPanel.setVisible(false);
        chatPanel.setVisible(true);
    }

    @FXML
    public void changeNick(ActionEvent event) {
        changeNickPanel.setVisible(true);
        chatPanel.setVisible(false);
    }
}