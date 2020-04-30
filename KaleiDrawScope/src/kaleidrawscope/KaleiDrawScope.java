package kaleidrawscope;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lucky
 */
public class KaleiDrawScope extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("KDSfxml.fxml"))));
        stage.setTitle("Kalei-draw-scope");

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
