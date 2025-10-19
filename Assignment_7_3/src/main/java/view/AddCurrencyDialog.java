package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import entity.Currency;

public class AddCurrencyDialog {
    private Stage dialogStage;
    private TextField abbreviationField;
    private TextField nameField;
    private TextField rateField;
    private boolean okClicked = false;
    private Currency result = null;

    public AddCurrencyDialog(Stage parentStage) {
        dialogStage = new Stage();
        dialogStage.setTitle("Add New Currency");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(parentStage);
        dialogStage.setResizable(false);

        createUI();
    }

    private void createUI() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f8f9fa;");

        // Title
        Label titleLabel = new Label("Add New Currency");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Form fields
        GridPane formGrid = createFormGrid();

        // Buttons
        HBox buttonBox = createButtonBox();

        root.getChildren().addAll(titleLabel, formGrid, buttonBox);

        Scene scene = new Scene(root, 400, 250);
        dialogStage.setScene(scene);
    }

    private GridPane createFormGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Abbreviation field
        Label abbrevLabel = new Label("Abbreviation:");
        abbrevLabel.setStyle("-fx-font-weight: bold;");
        abbreviationField = new TextField();
        abbreviationField.setPromptText("e.g., USD");
        abbreviationField.setPrefWidth(200);

        // Restrict to 3 uppercase letters
        abbreviationField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 3) {
                abbreviationField.setText(oldValue);
            } else {
                abbreviationField.setText(newValue.toUpperCase());
            }
        });

        // Name field
        Label nameLabel = new Label("Currency Name:");
        nameLabel.setStyle("-fx-font-weight: bold;");
        nameField = new TextField();
        nameField.setPromptText("e.g., US Dollar");
        nameField.setPrefWidth(200);

        // Rate field
        Label rateLabel = new Label("Rate to USD:");
        rateLabel.setStyle("-fx-font-weight: bold;");
        rateField = new TextField();
        rateField.setPromptText("e.g., 1.0");
        rateField.setPrefWidth(200);

        // Restrict to decimal numbers
        rateField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                rateField.setText(oldValue);
            }
        });

        grid.add(abbrevLabel, 0, 0);
        grid.add(abbreviationField, 1, 0);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(rateLabel, 0, 2);
        grid.add(rateField, 1, 2);

        return grid;
    }

    private HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(15, 0, 0, 0));

        Button okButton = new Button("Add Currency");
        okButton.setStyle(
                "-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                        "-fx-padding: 10px 20px; -fx-font-size: 14px; -fx-cursor: hand;"
        );
        okButton.setOnAction(e -> handleOk());

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(
                "-fx-background-color: #f44336; -fx-text-fill: white; " +
                        "-fx-padding: 10px 20px; -fx-font-size: 14px; -fx-cursor: hand;"
        );
        cancelButton.setOnAction(e -> handleCancel());

        buttonBox.getChildren().addAll(okButton, cancelButton);
        return buttonBox;
    }

    private void handleOk() {
        if (validateInput()) {
            String abbreviation = abbreviationField.getText().trim();
            String name = nameField.getText().trim();
            double rate = Double.parseDouble(rateField.getText().trim());

            result = new Currency(abbreviation, name, rate);
            okClicked = true;
            dialogStage.close();
        }
    }

    private void handleCancel() {
        dialogStage.close();
    }

    private boolean validateInput() {
        StringBuilder errorMessage = new StringBuilder();

        if (abbreviationField.getText() == null || abbreviationField.getText().trim().isEmpty()) {
            errorMessage.append("Currency abbreviation is required!\n");
        } else if (abbreviationField.getText().trim().length() != 3) {
            errorMessage.append("Currency abbreviation must be exactly 3 characters!\n");
        }

        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            errorMessage.append("Currency name is required!\n");
        }

        if (rateField.getText() == null || rateField.getText().trim().isEmpty()) {
            errorMessage.append("Exchange rate is required!\n");
        } else {
            try {
                double rate = Double.parseDouble(rateField.getText().trim());
                if (rate <= 0) {
                    errorMessage.append("Exchange rate must be positive!\n");
                }
            } catch (NumberFormatException e) {
                errorMessage.append("Exchange rate must be a valid number!\n");
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please correct the following errors:");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return false;
        }
    }

    public boolean showAndWait() {
        dialogStage.showAndWait();
        return okClicked;
    }

    public Currency getResult() {
        return result;
    }
}