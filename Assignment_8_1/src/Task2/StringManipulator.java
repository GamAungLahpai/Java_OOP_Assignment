package Task2;

/**
 * Assignment 8.1 - Task 2: StringManipulator class
 * Implementation following TDD approach
 *
 * Provides various string manipulation methods
 */
public class StringManipulator {

    /**
     * Concatenates two input strings and returns the result
     * @param str1 the first string
     * @param str2 the second string
     * @return the concatenated string
     */
    public String concatenate(String str1, String str2) {
        return str1 + str2;
    }

    /**
     * Returns the length of the input string
     * @param str the input string
     * @return the length of the string
     */
    public int findLength(String str) {
        return str.length();
    }

    /**
     * Converts the input string to uppercase and returns the result
     * @param str the input string
     * @return the string in uppercase
     */
    public String convertToUpperCase(String str) {
        return str.toUpperCase();
    }

    /**
     * Converts the input string to lowercase and returns the result
     * @param str the input string
     * @return the string in lowercase
     */
    public String convertToLowerCase(String str) {
        return str.toLowerCase();
    }

    /**
     * Checks if the input string contains the given substring
     * @param str the main string to search in
     * @param subStr the substring to search for
     * @return true if the substring is found, false otherwise
     */
    public boolean containsSubstring(String str, String subStr) {
        return str.contains(subStr);
    }
}