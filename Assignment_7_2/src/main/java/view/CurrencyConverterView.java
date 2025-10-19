package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import entity.Currency;

public class CurrencyConverterView {
    private BorderPane mainLayout;
    private TextField amountField;
    private TextField resultField;
    private ComboBox<Currency> fromCurrencyBox;
    private ComboBox<Currency> toCurrencyBox;
    private Button convertButton;
    private Label errorLabel;

    public CurrencyConverterView() {
        createUI();
    }

    private void createUI() {
        mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #f5f5f5;");

        VBox header = createHeader();
        mainLayout.setTop(header);

        VBox centerContent = createCenterContent();
        mainLayout.setCenter(centerContent);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #d32f2f; -fx-font-size: 13px; -fx-font-family: 'Arial', sans-serif;");
        errorLabel.setWrapText(true);
        errorLabel.setVisible(false);
        BorderPane.setMargin(errorLabel, new Insets(10, 0, 0, 0));
        mainLayout.setBottom(errorLabel);
    }

    private VBox createHeader() {
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));

        Label titleLabel = new Label("Currency Converter - Database Enhanced");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Arial', sans-serif; -fx-text-fill: #2c3e50;");

        Label instructionLabel = new Label("Exchange rates loaded from MariaDB database");
        instructionLabel.setStyle("-fx-font-size: 13px; -fx-font-family: 'Arial', sans-serif; -fx-text-fill: #555555;");

        header.getChildren().addAll(titleLabel, instructionLabel);
        return header;
    }

    private VBox createCenterContent() {
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setMaxWidth(450);

        VBox amountSection = createAmountSection();
        HBox currencySection = createCurrencySection();
        VBox resultSection = createResultSection();

        convertButton = new Button("Convert");
        convertButton.setStyle(
                "-fx-font-size: 16px; -fx-font-family: 'Arial', sans-serif; " +
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                        "-fx-padding: 12px 40px; -fx-cursor: hand; -fx-background-radius: 5px;"
        );

        centerContent.getChildren().addAll(amountSection, currencySection, resultSection, convertButton);
        return centerContent;
    }

    private VBox createAmountSection() {
        VBox section = new VBox(8);
        Label label = new Label("Amount to Convert:");
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Arial', sans-serif;");

        amountField = new TextField();
        amountField.setPromptText("Enter amount (e.g., 100)");
        amountField.setPrefWidth(300);
        amountField.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial', sans-serif; -fx-padding: 10px;");

        // Restrict input to numbers
        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                amountField.setText(oldValue);
            }
        });

        section.getChildren().addAll(label, amountField);
        return section;
    }

    private HBox createCurrencySection() {
        HBox section = new HBox(20);
        section.setAlignment(Pos.CENTER);

        VBox fromBox = new VBox(8);
        Label fromLabel = new Label("From Currency:");
        fromLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Arial', sans-serif;");
        fromCurrencyBox = new ComboBox<>();
        fromCurrencyBox.setPrefWidth(200);
        fromBox.getChildren().addAll(fromLabel, fromCurrencyBox);

        VBox toBox = new VBox(8);
        Label toLabel = new Label("To Currency:");
        toLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Arial', sans-serif;");
        toCurrencyBox = new ComboBox<>();
        toCurrencyBox.setPrefWidth(200);
        toBox.getChildren().addAll(toLabel, toCurrencyBox);

        section.getChildren().addAll(fromBox, toBox);
        return section;
    }

    private VBox createResultSection() {
        VBox section = new VBox(8);
        Label label = new Label("Converted Amount:");
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Arial', sans-serif;");

        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setPromptText("Result will appear here");
        resultField.setPrefWidth(300);
        resultField.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial', sans-serif; -fx-padding: 10px; -fx-background-color: #f9f9f9;");

        section.getChildren().addAll(label, resultField);
        return section;
    }

    // Getters
    public BorderPane getMainLayout() { return mainLayout; }
    public TextField getAmountField() { return amountField; }
    public TextField getResultField() { return resultField; }
    public ComboBox<Currency> getFromCurrencyBox() { return fromCurrencyBox; }
    public ComboBox<Currency> getToCurrencyBox() { return toCurrencyBox; }
    public Button getConvertButton() { return convertButton; }

    public void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public void hideError() {
        errorLabel.setVisible(false);
    }
}