package Task1;

/**
 * PalindromeChecker class
 * Implementation following TDD approach
 *
 * A palindrome reads the same forward and backward,
 * ignoring spaces, punctuation, and capitalization.
 */
public class PalindromeChecker {

    /**
     * Checks if a given string is a palindrome
     * @param str the string to check
     * @return true if the string is a palindrome, false otherwise
     */
    public boolean isPalindrome(String str) {
        // Handle null input
        if (str == null) {
            return false;
        }

        // Remove all non-alphanumeric characters and convert to lowercase
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Check if the cleaned string is equal to its reverse
        String reversed = new StringBuilder(cleaned).reverse().toString();

        return cleaned.equals(reversed);
    }
}