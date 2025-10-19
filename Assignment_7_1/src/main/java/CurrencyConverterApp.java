import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create view
        CurrencyConverterView view = new CurrencyConverterView();

        // Create controller (which initializes the model)
        CurrencyConverterController controller = new CurrencyConverterController(view);

        // Set up the scene and stage
        Scene scene = new Scene(view.getMainLayout(), 600, 500);

        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}