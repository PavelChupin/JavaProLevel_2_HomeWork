package homework.lesson6.chat.javafx.controller.message;

import homework.lesson6.chat.messageconvert.Message;

import java.io.Closeable;
import java.io.IOException;

public interface IMessageService extends Closeable {

    void sendMessage(Message message);

    //void processRetrievedMessage(String message);
    void processRetrievedMessage(Message message);

    @Override
    default void close() throws IOException {
        //Do nothing
    }
}
