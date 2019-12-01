package homework.lesson6.chat.javafx;

import homework.lesson6.chat.javafx.controller.PrimaryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX MainGUI
 */
public class MainGUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Сетевой чат");
        stage.getIcons().add(new Image("/images/stage_icon.png"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/primary_new.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        PrimaryController controller = loader.getController();
        stage.setOnHidden(e -> controller.shutdown());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}