package application;

import controller.CurrencyConverterController;
import view.CurrencyConverterView;
import datasource.MariaDbJpaConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create view
            CurrencyConverterView view = new CurrencyConverterView();

            // Create controller (pass primaryStage for dialog creation)
            CurrencyConverterController controller = new CurrencyConverterController(view, primaryStage);

            // Set up the scene and stage
            Scene scene = new Scene(view.getMainLayout(), 700, 600);

            primaryStage.setTitle("Currency Converter - JPA with Associations (Assignment 7.4)");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Handle application closing
            primaryStage.setOnCloseRequest(e -> {
                try {
                    MariaDbJpaConnection.close();
                    System.out.println("JPA EntityManager and EntityManagerFactory closed.");
                } catch (Exception ex) {
                    System.err.println("Error closing JPA connection: " + ex.getMessage());
                }
            });

            primaryStage.show();

            System.out.println("JPA Currency Converter with Transaction Storage started successfully");

        } catch (Exception e) {
            System.err.println("Failed to start JPA Currency Converter application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting JPA Currency Converter with Transaction Storage...");
        launch(args);
    }
}