package com.dictionary;

import java.util.HashMap;
import java.util.Map;

class WordNotFoundException extends Exception {
    public WordNotFoundException(String message) {
        super(message);
    }
}

public class Dictionary {
    private Map<String, String> words;

    public Dictionary() {
        this.words = new HashMap<>();
    }

    public void addWord(String word, String meaning) {
        if (word != null && meaning != null) {
            words.put(word.toLowerCase().trim(), meaning.trim());
        }
    }

    public String searchWord(String word) throws WordNotFoundException {
        if (word == null || word.trim().isEmpty()) {
            throw new WordNotFoundException("Please enter a word to search.");
        }

        String meaning = words.get(word.toLowerCase().trim());
        if (meaning == null) {
            throw new WordNotFoundException("Word '" + word + "' not found in dictionary.");
        }
        return meaning;
    }

    public int getSize() {
        return words.size();
    }
}