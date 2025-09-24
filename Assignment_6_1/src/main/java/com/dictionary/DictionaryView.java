package com.dictionary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class DictionaryView extends Application {
    private TextField wordInput;
    private Button searchButton;
    private TextArea meaningDisplay;
    private Label statusLabel;
    private DictionaryController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new DictionaryController();

        wordInput = new TextField();
        wordInput.setPromptText("Enter a word to search");
        wordInput.setPrefWidth(200);
        wordInput.setOnAction(e -> handleSearch());

        searchButton = new Button("Search");
        searchButton.setOnAction(e -> handleSearch());

        meaningDisplay = new TextArea();
        meaningDisplay.setEditable(false);
        meaningDisplay.setPrefSize(350, 120);
        meaningDisplay.setWrapText(true);
        meaningDisplay.setStyle("-fx-font-size: 14px;");

        statusLabel = new Label("Dictionary loaded with " + controller.getDictionarySize() + " words. Enter a word to search.");
        statusLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 12px;");

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(15));
        root.setHgap(10);
        root.setVgap(15);
        root.getChildren().addAll(wordInput, searchButton, meaningDisplay, statusLabel);

        primaryStage.setTitle("Virtual Dictionary Application");
        primaryStage.setScene(new Scene(root, 450, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void handleSearch() {
        String word = wordInput.getText().trim();

        if (word.isEmpty()) {
            meaningDisplay.setText("Please enter a word to search.");
            statusLabel.setText("Error: Empty input field");
            return;
        }

        String result = controller.searchWord(word);
        meaningDisplay.setText(result);

        if (result.contains("not found")) {
            statusLabel.setText("Word not found in dictionary");
        } else {
            statusLabel.setText("Word found successfully");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}