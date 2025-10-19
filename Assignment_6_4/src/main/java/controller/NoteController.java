package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Note;
import model.Notebook;

public class NoteController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private ListView<Note> notesListView;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    private Notebook notebook;
    private Note selectedNote;

    public NoteController() {
        notebook = new Notebook();
    }

    @FXML
    public void initialize() {
        // Bind the ListView to the notebook's notes
        notesListView.setItems(notebook.getNotes());

        // Set up cell factory to display note titles
        notesListView.setCellFactory(lv -> new ListCell<Note>() {
            @Override
            protected void updateItem(Note note, boolean empty) {
                super.updateItem(note, empty);
                if (empty || note == null) {
                    setText(null);
                } else {
                    setText(note.getTitle());
                }
            }
        });

        // Add listener for note selection
        notesListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedNote = newValue;
                        titleField.setText(newValue.getTitle());
                        contentArea.setText(newValue.getContent());
                        editButton.setDisable(false);
                        deleteButton.setDisable(false);
                    } else {
                        selectedNote = null;
                        editButton.setDisable(true);
                        deleteButton.setDisable(true);
                    }
                }
        );

        // Initially disable edit and delete buttons
        editButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @FXML
    public void handleAddNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty()) {
            showAlert("Error", "Title cannot be empty!", Alert.AlertType.ERROR);
            return;
        }

        if (content.isEmpty()) {
            showAlert("Error", "Content cannot be empty!", Alert.AlertType.ERROR);
            return;
        }

        Note newNote = new Note(title, content);
        notebook.addNote(newNote);

        // Clear input fields
        clearFields();

        showAlert("Success", "Note added successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    public void handleEditNote() {
        if (selectedNote == null) {
            showAlert("Error", "No note selected!", Alert.AlertType.ERROR);
            return;
        }

        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty() || content.isEmpty()) {
            showAlert("Error", "Title and content cannot be empty!", Alert.AlertType.ERROR);
            return;
        }

        selectedNote.setTitle(title);
        selectedNote.setContent(content);

        // Refresh the ListView
        notesListView.refresh();

        clearFields();
        notesListView.getSelectionModel().clearSelection();

        showAlert("Success", "Note updated successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    public void handleDeleteNote() {
        if (selectedNote == null) {
            showAlert("Error", "No note selected!", Alert.AlertType.ERROR);
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText("Delete Note");
        confirmAlert.setContentText("Are you sure you want to delete this note?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                notebook.removeNote(selectedNote);
                clearFields();
                notesListView.getSelectionModel().clearSelection();
                showAlert("Success", "Note deleted successfully!", Alert.AlertType.INFORMATION);
            }
        });
    }

    @FXML
    public void handleClearFields() {
        clearFields();
        notesListView.getSelectionModel().clearSelection();
    }

    private void clearFields() {
        titleField.clear();
        contentArea.clear();
        selectedNote = null;
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}