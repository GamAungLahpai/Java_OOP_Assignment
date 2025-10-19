package application;

import controller.CurrencyConverterController;
import view.CurrencyConverterView;
import datasource.MariaDbConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create view and controller
            CurrencyConverterView view = new CurrencyConverterView();
            CurrencyConverterController controller = new CurrencyConverterController(view);

            // Set up the scene and stage
            Scene scene = new Scene(view.getMainLayout(), 600, 500);

            primaryStage.setTitle("Currency Converter - Database Enhanced (Assignment 7.2)");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Handle window closing
            primaryStage.setOnCloseRequest(e -> {
                MariaDbConnection.terminate();
                System.out.println("Database connection closed.");
            });

            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}