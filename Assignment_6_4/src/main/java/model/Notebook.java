package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Notebook {
    private ObservableList<Note> notes;

    public Notebook() {
        this.notes = FXCollections.observableArrayList();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }

    public ObservableList<Note> getNotes() {
        return notes;
    }

    public Note getNote(int index) {
        if (index >= 0 && index < notes.size()) {
            return notes.get(index);
        }
        return null;
    }

    public int getNotesCount() {
        return notes.size();
    }
}