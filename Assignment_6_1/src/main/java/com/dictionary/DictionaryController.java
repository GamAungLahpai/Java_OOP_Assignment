package com.dictionary;

public class DictionaryController {
    private Dictionary dictionary;

    public DictionaryController() {
        this.dictionary = new Dictionary();
        populateDictionary();
    }

    private void populateDictionary() {
        dictionary.addWord("apple", "A round fruit with red or green skin and crisp flesh");
        dictionary.addWord("book", "A written or printed work consisting of pages glued or sewn together");
        dictionary.addWord("computer", "An electronic device for storing and processing data");
        dictionary.addWord("dog", "A domesticated carnivorous mammal that typically has a long snout");
        dictionary.addWord("elephant", "A very large mammal with thick gray skin and a trunk");
        dictionary.addWord("flower", "The seed-bearing part of a plant, consisting of reproductive organs");
        dictionary.addWord("guitar", "A stringed musical instrument with a fretted fingerboard");
        dictionary.addWord("house", "A building for human habitation, especially one that is lived in");
        dictionary.addWord("internet", "A global computer network providing a variety of information");
        dictionary.addWord("jungle", "An area of land overgrown with dense forest and tangled vegetation");
    }

    public String searchWord(String word) {
        try {
            return dictionary.searchWord(word);
        } catch (WordNotFoundException e) {
            return e.getMessage();
        }
    }

    public void addNewWord(String word, String meaning) {
        dictionary.addWord(word, meaning);
    }

    public int getDictionarySize() {
        return dictionary.getSize();
    }
}