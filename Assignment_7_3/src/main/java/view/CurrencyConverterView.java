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
    private Button addCurrencyButton;
    private Label errorLabel;
    private Label statusLabel;

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

        VBox bottomSection = createBottomSection();
        mainLayout.setBottom(bottomSection);
    }

    private VBox createHeader() {
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));

        Label titleLabel = new Label("Currency Converter - JPA Enhanced");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Arial', sans-serif; -fx-text-fill: #2c3e50;");

        Label instructionLabel = new Label("Exchange rates managed with JPA/Hibernate ORM");
        instructionLabel.setStyle("-fx-font-size: 13px; -fx-font-family: 'Arial', sans-serif; -fx-text-fill: #555555;");

        statusLabel = new Label("Ready");
        statusLabel.setStyle("-fx-font-size: 12px; -fx-font-family: 'Arial', sans-serif; -fx-text-fill: #27ae60;");

        header.getChildren().addAll(titleLabel, instructionLabel, statusLabel);
        return header;
    }

    private VBox createCenterContent() {
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setMaxWidth(450);

        VBox amountSection = createAmountSection();
        HBox currencySection = createCurrencySection();
        VBox resultSection = createResultSection();
        HBox buttonSection = createButtonSection();

        centerContent.getChildren().addAll(amountSection, currencySection, resultSection, buttonSection);
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

    private HBox createButtonSection() {
        HBox section = new HBox(15);
        section.setAlignment(Pos.CENTER);

        convertButton = new Button("Convert");
        convertButton.setStyle(
                "-fx-font-size: 16px; -fx-font-family: 'Arial', sans-serif; " +
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                        "-fx-padding: 12px 40px; -fx-cursor: hand; -fx-background-radius: 5px;"
        );

        addCurrencyButton = new Button("Add New Currency");
        addCurrencyButton.setStyle(
                "-fx-font-size: 14px; -fx-font-family: 'Arial', sans-serif; " +
                        "-fx-background-color: #2196F3; -fx-text-fill: white; " +
                        "-fx-padding: 10px 20px; -fx-cursor: hand; -fx-background-radius: 5px;"
        );

        section.getChildren().addAll(convertButton, addCurrencyButton);
        return section;
    }

    private VBox createBottomSection() {
        VBox section = new VBox(5);
        section.setPadding(new Insets(10, 0, 0, 0));

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #d32f2f; -fx-font-size: 13px; -fx-font-family: 'Arial', sans-serif;");
        errorLabel.setWrapText(true);
        errorLabel.setVisible(false);

        section.getChildren().add(errorLabel);
        return section;
    }

    // Getters
    public BorderPane getMainLayout() { return mainLayout; }
    public TextField getAmountField() { return amountField; }
    public TextField getResultField() { return resultField; }
    public ComboBox<Currency> getFromCurrencyBox() { return fromCurrencyBox; }
    public ComboBox<Currency> getToCurrencyBox() { return toCurrencyBox; }
    public Button getConvertButton() { return convertButton; }
    public Button getAddCurrencyButton() { return addCurrencyButton; }

    public void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public void hideError() {
        errorLabel.setVisible(false);
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }
}